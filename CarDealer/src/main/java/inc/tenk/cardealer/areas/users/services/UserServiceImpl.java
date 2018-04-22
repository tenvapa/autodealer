package inc.tenk.cardealer.areas.users.services;

import inc.tenk.cardealer.areas.products.cars.entities.Car;
import inc.tenk.cardealer.areas.products.cars.models.CarDTO;
import inc.tenk.cardealer.areas.products.parts.entities.Part;
import inc.tenk.cardealer.areas.products.parts.models.PartDTO;
import inc.tenk.cardealer.areas.users.entities.Cart;
import inc.tenk.cardealer.areas.users.models.UserDTO;
import inc.tenk.cardealer.areas.users.repositories.CartRepository;
import inc.tenk.cardealer.config.BeanConfig;
import inc.tenk.cardealer.areas.users.models.UserEditDTO;
import inc.tenk.cardealer.areas.users.entities.User;
import inc.tenk.cardealer.areas.users.repositories.UserRepository;
import inc.tenk.cardealer.areas.users.models.UserRegisterDTO;
import inc.tenk.cardealer.areas.users.entities.Role;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserServiceImpl implements UserService, UserDetailsService {
    private final UserRepository userRepository;
    private final RoleService roleService;
    private final CartService cartService;
    private ModelMapper mapper;
    private final BeanConfig passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleService roleService, CartService cartService, BeanConfig passwordEncoder) {
        this.userRepository = userRepository;
        this.roleService = roleService;
        this.cartService = cartService;
        this.passwordEncoder = passwordEncoder;
        this.mapper = new ModelMapper();
    }

    @Override
    public boolean exist(String username) {
        return this.userRepository.findUserByUsername(username)!=null;
    }

    @Override
    public boolean exist(String username, String password) {
        return this.userRepository.findUserByUsernameAndPassword(username,password)!=null;
    }

    @Override
    public void updateRole(Long id, String roleName) {
        Role role = this.roleService.get(roleName);
        this.userRepository.updateRole(id,role);
    }

    @Override
    public boolean addToCart(Long productId,String productType, String username) {
        User user = this.userRepository.findByUsername(username);
        if(productType.equals("part")) {
            user.setCart(this.cartService.addPart(productId,user.getCart()));
        }
        if(productType.equals("car")) {
            user.setCart(this.cartService.addCar(productId,user.getCart()));
        }
        return false;
    }

    @Override
    public boolean removeFromCart(Long productId,String productType, String username) {
        User user = this.userRepository.findByUsername(username);
        if(productType.equals("part")) {
            this.cartService.removePart(productId,user.getCart());
        }
        if(productType.equals("car")) {
            this.cartService.removeCar(productId,user.getCart());
        }
        return false;
    }

    @Override
    public Set<PartDTO> partsInCart(String username) {
        User user = this.userRepository.findUserByUsername(username);
        Set<Part> parts = user.getCart().getParts();
        Set<PartDTO> partDTOS = new LinkedHashSet<>();
        parts.forEach(part -> partDTOS.add(this.mapper.map(part, PartDTO.class)));

        return partDTOS;
    }

    @Override
    public Set<CarDTO> carsInCart(String username) {
        User user = this.userRepository.findUserByUsername(username);
        Set<Car> cars = user.getCart().getCars();
        Set<CarDTO> carDTOS = new LinkedHashSet<>();
        cars.forEach(car -> carDTOS.add(this.mapper.map(car, CarDTO.class)));

        return carDTOS;
    }

    @Override
    public BigDecimal totalCartValue(String username) {
        User user = this.userRepository.findUserByUsername(username);
        return this.cartService.totalAmount(user.getCart());
    }

    @Override
    public void register(UserRegisterDTO userDto) {
        User user = new User(userDto.getFirstName(),userDto.getLastName(),
                userDto.getUsername(),userDto.getPassword(),
                userDto.getPhoneNumber(),userDto.getAddress(),userDto.getPostCode());
        Role role;
        Cart cart;
        if(this.userRepository.findAll().size()==0) {
            this.roleService.insert(new Role("SUPER_ADMIN"));
            this.roleService.insert(new Role("ADMIN"));
            this.roleService.insert(new Role("MODERATOR"));
            this.roleService.insert(new Role("USER"));
            role = this.roleService.get("SUPER_ADMIN");
        } else {
            role = this.roleService.get("USER");
            cart = new Cart();
            this.cartService.insert(cart);
            user.setCart(cart);
            cart.setUser(user);
        }
        role.getUsers().add(user);
        user.setRole(role);
        this.userRepository.save(user);
    }

    @Override
    public void edit(UserEditDTO user) {
        //editing user but not changing the password
        if(user.getPassword().isEmpty() || user.getConfirmPassword().isEmpty()) {
            this.userRepository.updateUser(user.getUsername(),user.getAddress(),
                    user.getFirstName(),user.getLastName(),
                    user.getPostCode(),user.getPhoneNumber());
        }
        //editing user with changing the password
        else {
            this.userRepository.updateUser(user.getUsername(), user.getAddress(),
                    user.getFirstName(),user.getLastName(),
                    user.getPostCode(), user.getPassword(),user.getPhoneNumber());
        }
    }

    @Override
    public UserDTO get(String username) {
        return this.mapper.map(this.userRepository.findUserByUsername(username),UserDTO.class);
    }

    @Override
    public List<UserDTO> all() {
        return Arrays.asList(this.mapper.map(this.userRepository.findAll(),UserDTO[].class));
    }

    @Override
    public void delete(Long id) {
        this.userRepository.delete(id);
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.userRepository.findUserByUsername(username);
        if(user==null) {
            throw new UsernameNotFoundException("User not found");
        }
        Set<GrantedAuthority> roles = new HashSet<>();
        roles.add(new SimpleGrantedAuthority("ROLE_"+user.getRole().getName()));
        UserDetails userDetails = new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword(),
                roles
        );
        return userDetails;
    }
}

package inc.tenk.cardealer.areas.users.services;

import inc.tenk.cardealer.areas.users.models.UserDTO;
import inc.tenk.cardealer.config.BeanConfig;
import inc.tenk.cardealer.areas.users.models.UserEditDTO;
import inc.tenk.cardealer.areas.users.entities.User;
import inc.tenk.cardealer.areas.users.repositories.RoleRepository;
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

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class UserServiceImpl implements UserService, UserDetailsService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private ModelMapper mapper;
    private final BeanConfig passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, BeanConfig passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
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
        Role role = this.roleRepository.findFirstByName(roleName);
        this.userRepository.updateRole(id,role);
    }

    @Override
    public void register(UserRegisterDTO userDto) {
        User user = new User();
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setUsername(userDto.getUsername());
        user.setPhoneNumber(userDto.getPhoneNumber());
        user.setAddress(userDto.getAddress());
        user.setPostCode(userDto.getPostCode());

        Role role;
        if(this.userRepository.findAll().size()==0) {
            this.roleRepository.save(new Role("SUPER_ADMIN"));
            this.roleRepository.save(new Role("ADMIN"));
            this.roleRepository.save(new Role("MODERATOR"));
            this.roleRepository.save(new Role("USER"));
            role = this.roleRepository.findFirstByName("SUPER_ADMIN");
        } else {
            role = this.roleRepository.findFirstByName("USER");
        }
        user.setPassword(userDto.getPassword());
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
    public UserEditDTO get(String username) {
        return this.mapper.map(this.userRepository.findUserByUsername(username),UserEditDTO.class);
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

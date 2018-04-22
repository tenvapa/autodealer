package inc.tenk.cardealer.areas.users.services;

import inc.tenk.cardealer.areas.products.cars.models.CarDTO;
import inc.tenk.cardealer.areas.products.parts.models.PartDTO;
import inc.tenk.cardealer.areas.users.entities.Cart;
import inc.tenk.cardealer.areas.users.models.UserDTO;
import inc.tenk.cardealer.areas.users.models.UserEditDTO;
import inc.tenk.cardealer.areas.users.entities.User;
import inc.tenk.cardealer.areas.users.models.UserRegisterDTO;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;


public interface UserService {
    boolean exist(String username);
    boolean exist(String username, String password);
    void updateRole(Long id, String role);
    boolean addToCart(Long id,String productType, String username);
    boolean removeFromCart(Long id,String productType, String username);
    Set<PartDTO> partsInCart(String username);
    Set<CarDTO> carsInCart(String username);
    BigDecimal totalCartValue(String username);
    void register(UserRegisterDTO user);
    void edit(UserEditDTO user);
    UserDTO get(String username);
    List<UserDTO> all();
    void delete(Long id);
}

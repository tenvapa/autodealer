package inc.tenk.cardealer.areas.users.services;

import inc.tenk.cardealer.areas.users.models.UserDTO;
import inc.tenk.cardealer.areas.users.models.UserEditDTO;
import inc.tenk.cardealer.areas.users.entities.User;
import inc.tenk.cardealer.areas.users.models.UserRegisterDTO;

import java.util.List;


public interface UserService {
    boolean exist(String username);
    boolean exist(String username, String password);
    void updateRole(Long id, String role);
    void register(UserRegisterDTO user);
    void edit(UserEditDTO user);
    UserEditDTO get(String username);
    List<UserDTO> all();
    void delete(Long id);
}

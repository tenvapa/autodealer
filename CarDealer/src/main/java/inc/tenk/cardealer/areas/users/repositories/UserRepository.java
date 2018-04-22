package inc.tenk.cardealer.areas.users.repositories;

import inc.tenk.cardealer.areas.users.entities.Cart;
import inc.tenk.cardealer.areas.users.entities.Role;
import inc.tenk.cardealer.areas.users.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Transactional
public interface UserRepository extends JpaRepository<User, Long> {
    User findUserByUsername(String username);
    User findUserByUsernameAndPassword(String username,String password);
    @Query("update User u set u.cart=:cart where u.id=:id")
    @Modifying
    void updateUserCart(@Param("id") Long id,@Param("cart") Cart cart);
    @Query("update User u set u.address=:address,u.firstName=:firstName,u.lastName=:lastName," +
            "u.postCode=:postCode,u.password=:password,u.phoneNumber=:phoneNumber where u.username=:username")
    @Modifying
    void updateUser(@Param("username") String username, @Param("address") String address, @Param("firstName") String firstName,
                    @Param("lastName") String lastName,@Param("postCode") String postCode,
                    @Param("password") String password,@Param("phoneNumber") String phoneNumber);
    @Query("update User u set u.address=:address,u.firstName=:firstName,u.lastName=:lastName," +
            "u.postCode=:postCode,u.phoneNumber=:phoneNumber where u.username=:username")
    @Modifying
    void updateUser(@Param("username") String username, @Param("address") String address, @Param("firstName") String firstName,
                    @Param("lastName") String lastName,@Param("postCode") String postCode,
                    @Param("phoneNumber") String phoneNumber);
    User findByUsername(String username);
    @Modifying
    @Query("update User u set u.role=:role where u.id=:id")
    void updateRole(@Param("id") Long id, @Param("role") Role role);
}

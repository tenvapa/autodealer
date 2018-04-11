package inc.tenk.cardealer.areas.users.repositories;

import inc.tenk.cardealer.areas.users.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long>{
    Role findFirstByName(String name);
}

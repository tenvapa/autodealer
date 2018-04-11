package inc.tenk.cardealer.areas.users.services;

import inc.tenk.cardealer.areas.users.entities.Role;
import inc.tenk.cardealer.areas.users.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {
    private RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public List<Role> all() {
        return this.roleRepository.findAll();
    }
}

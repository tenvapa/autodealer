package inc.tenk.cardealer.areas.users.entities;

import inc.tenk.cardealer.utils.HTMLEncoder;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    @OneToMany(fetch = FetchType.EAGER, mappedBy = "role")
    private Set<User> users;

    public Role() {
        this.users = new HashSet<>();
    }

    public Role(String name) {
        this.setName(name);
        this.users = new HashSet<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = HTMLEncoder.escapeHTML(name);
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }
}

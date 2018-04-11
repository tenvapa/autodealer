package inc.tenk.cardealer.areas.users.entities;

import inc.tenk.cardealer.areas.sales.entities.Sale;
import inc.tenk.cardealer.utils.HTMLEncoder;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String firstName;
    private String lastName;
    @Column(unique = true)
    private String username;
    private String password;
    private String phoneNumber;
    private String address;
    private String postCode;

    @Enumerated(EnumType.STRING)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name = "FK_user_role"))
    private Role role;
    @OneToMany(mappedBy = "user")
    private Set<Sale> sales;

    public User() {
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = HTMLEncoder.escapeHTML(firstName);
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = HTMLEncoder.escapeHTML(lastName);
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = HTMLEncoder.escapeHTML(username);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = HTMLEncoder.escapeHTML(password);
    }

    public Role getRole() {
        return this.role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = HTMLEncoder.escapeHTML(phoneNumber);
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = HTMLEncoder.escapeHTML(address);
    }

    public String getPostCode() {
        return postCode;
    }

    public void setPostCode(String postCode) {
        this.postCode = HTMLEncoder.escapeHTML(postCode);
    }

    public Set<Sale> getSales() {
        return sales;
    }

    public void setSales(Set<Sale> sales) {
        this.sales = sales;
    }
}

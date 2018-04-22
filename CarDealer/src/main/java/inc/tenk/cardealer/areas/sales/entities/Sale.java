package inc.tenk.cardealer.areas.sales.entities;

import inc.tenk.cardealer.areas.products.cars.entities.Car;
import inc.tenk.cardealer.areas.products.parts.entities.Part;
import inc.tenk.cardealer.areas.users.entities.User;

import javax.persistence.*;

@Entity
@Table(name = "sales")
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne(targetEntity = Car.class)
    @JoinColumn(foreignKey = @ForeignKey(name = "FK_sales_cars"))
    private Car car;
    @ManyToOne(targetEntity = Part.class)
    @JoinColumn(foreignKey = @ForeignKey(name = "FK_sales_parts"))
    private Part part;
    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "FK_sales_users"))
    private User user;

    public Sale() {

    }

    public Sale(Car car, User user) {
        this.car = car;
        this.part=null;
        this.user = user;
    }

    public Sale(Part part, User user) {
        this.part = part;
        this.car=null;
        this.user = user;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Part getPart() {
        return part;
    }

    public void setPart(Part part) {
        this.part = part;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User customer) {
        this.user = customer;
    }
}

package inc.tenk.cardealer.areas.sales.entities;

import inc.tenk.cardealer.areas.cars.entities.Car;
import inc.tenk.cardealer.areas.users.entities.User;

import javax.persistence.*;

@Entity
@Table(name = "sales")
public class Sale {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private double discount;
    @OneToOne(targetEntity = Car.class)
    @JoinColumn(foreignKey = @ForeignKey(name = "FK_sales_cars"))
    private Car car;
    @ManyToOne
    @JoinColumn(foreignKey = @ForeignKey(name = "FK_sales_users"))
    private User user;

    public Sale() {

    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getDiscount() {
        return this.discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public Car getCar() {
        return this.car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public User getUser() {
        return this.user;
    }

    public void setUser(User customer) {
        this.user = customer;
    }
}

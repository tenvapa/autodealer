package inc.tenk.cardealer.areas.products.cars.entities;

import inc.tenk.cardealer.areas.sales.entities.Sale;
import inc.tenk.cardealer.areas.users.entities.Cart;
import inc.tenk.cardealer.utils.HTMLEncoder;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "cars")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private BigDecimal price;
    private Date publicationDate;
    @ManyToMany(mappedBy = "cars",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Cart> carts;
    @OneToMany(mappedBy = "car",fetch = FetchType.EAGER)
    private Set<Sale> sales;
    private String make;
    private String model;
    private int year;
    private String description;
    private boolean inStock;
    public Car() {
        this.carts = new HashSet<>();
        this.sales = new HashSet<>();
        this.inStock=true;
    }
    public Car(String make, String model, int year,BigDecimal price, String description) {
        this.carts = new HashSet<>();
        this.sales = new HashSet<>();
        this.make = make;
        this.model = model;
        this.year = year;
        this.price=price;
        this.setDescription(description);
        this.setPublicationDate(Calendar.getInstance().getTime());
        this.inStock=true;

    }

    public Car(String make, String model, int year) {
        this.make = make;
        this.model = model;
        this.year = year;
    }

    public Long getId() {
        return id;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Date getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
    }

    public Set<Cart> getCarts() {
        return carts;
    }

    public void setCarts(Set<Cart> carts) {
        this.carts = carts;
    }

    public Set<Sale> getSales() {
        return sales;
    }

    public void setSales(Set<Sale> sales) {
        this.sales = sales;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = HTMLEncoder.escapeHTML(make);
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = HTMLEncoder.escapeHTML(model);
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = HTMLEncoder.escapeHTML(description);
    }

    public boolean isInStock() {
        return inStock;
    }

    public void setInStock(boolean inStock) {
        this.inStock = inStock;
    }
}

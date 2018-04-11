package inc.tenk.cardealer.areas.cars.entities;

import inc.tenk.cardealer.areas.sales.entities.Sale;
import inc.tenk.cardealer.utils.HTMLEncoder;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;

@Entity
@Table(name = "cars")
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String make;
    private String model;
    private int year;
    private String description;
    private BigDecimal price;
    private Date publicationDate;
    @OneToOne(mappedBy = "car")
    private Sale sale;

    protected Car() {
    }
    public Car(String make, String model, int year, BigDecimal price, String description) {
        this.make = make;
        this.model = model;
        this.year = year;
        this.setPrice(price);
        this.setDescription(description);
        this.setPublicationDate(Calendar.getInstance().getTime());
    }

    public Car(String make, String model, int year) {
        this.make = make;
        this.model = model;
        this.year = year;
    }

    public Long getId() {
        return id;
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


    public Sale getSale() {
        return this.sale;
    }

    public void setSale(Sale sale) {
        this.sale = sale;
    }

}

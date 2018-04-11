package inc.tenk.cardealer.areas.parts.entities;

import inc.tenk.cardealer.utils.HTMLEncoder;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "parts")
public class Part {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String description;
    private BigDecimal price;
    private int quantity;
    private Date publicationDate;

    public Part() {

    }

    public Part(String description, BigDecimal price, int quantity) {
        this.setDescription(description);
        this.setPrice(price);
        this.setQuantity(quantity);
        this.setPublicationDate(Calendar.getInstance().getTime());

    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = HTMLEncoder.escapeHTML(description);
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Date getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
    }


}

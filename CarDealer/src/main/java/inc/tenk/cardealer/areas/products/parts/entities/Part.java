package inc.tenk.cardealer.areas.products.parts.entities;


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
@Table(name = "parts")
public class Part {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private BigDecimal price;
    private Date publicationDate;
    @ManyToMany(mappedBy = "parts",cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Cart> carts;
    @OneToMany(mappedBy = "part")
    private Set<Sale> sales;
    private String description;
    private int quantity;
    private boolean inStock;

    public Part() {
        this.carts = new HashSet<>();
        this.sales = new HashSet<>();
        this.inStock = true;
    }

    public Part(String description, BigDecimal price, int quantity) {
        this.setDescription(description);
        this.setPrice(price);
        this.setQuantity(quantity);
        this.setPublicationDate(Calendar.getInstance().getTime());
        this.carts = new HashSet<>();
        this.sales = new HashSet<>();
        this.inStock=true;
    }

    public Long getId() {
        return id;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = HTMLEncoder.escapeHTML(description);
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
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

    public boolean isInStock() {
        return inStock;
    }

    public void setInStock(boolean inStock) {
        this.inStock = inStock;
    }
}

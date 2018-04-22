package inc.tenk.cardealer.areas.products.parts.models;

import inc.tenk.cardealer.utils.HTMLEncoder;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.*;
import java.math.BigDecimal;

public class PublishPartDTO {
    private static final String EMPTY_FIELD_ERROR = "You cannot submit an empty field";

    @NotEmpty(message = EMPTY_FIELD_ERROR)
    private String description;
    @NotNull
    @DecimalMin(value = "0.1")
    private BigDecimal price;
    @NotNull
    @Min(value = 1)
    private Integer quantity;
    private boolean inStock;

    public PublishPartDTO() {
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String name) {
        this.description = HTMLEncoder.escapeHTML(name);
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public boolean isInStock() {
        return inStock;
    }

    public void setInStock(boolean inStock) {
        this.inStock = inStock;
    }
}

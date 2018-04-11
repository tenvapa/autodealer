package inc.tenk.cardealer.areas.parts.models;

import inc.tenk.cardealer.utils.HTMLEncoder;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class PartEditDTO {
    private static final String EMPTY_FIELD_ERROR = "You cannot submit an empty field";

    @NotEmpty(message = EMPTY_FIELD_ERROR)
    private String name;
    @NotNull
    @DecimalMin(value = "0.1")
    private BigDecimal price;
    @NotNull
    @Min(value = 1)
    private Integer quantity;

    public PartEditDTO() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = HTMLEncoder.escapeHTML(name);
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

}

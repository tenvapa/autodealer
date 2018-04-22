package inc.tenk.cardealer.areas.products.cars.models;

import inc.tenk.cardealer.utils.HTMLEncoder;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.*;
import java.math.BigDecimal;

public class PublishCarDTO {
    private static final String DESCRIPTION_MAX_LENGTH_ERROR = "You have made more than maximum amount of symbols";

    @NotEmpty
    private String make;
    @NotEmpty
    private String model;
    @Min(1900)
    @Max(2018)
    @NotNull
    private Integer year;
    @NotNull
    @DecimalMin(value = "0.1")
    private BigDecimal price;
    @Length(max = 255, message = DESCRIPTION_MAX_LENGTH_ERROR)
    private String description;

    private boolean inStock;

    public PublishCarDTO() {
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

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
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

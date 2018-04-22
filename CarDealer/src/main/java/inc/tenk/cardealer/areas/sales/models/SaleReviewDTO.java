package inc.tenk.cardealer.areas.sales.models;

import inc.tenk.cardealer.areas.products.cars.entities.Car;
import inc.tenk.cardealer.areas.users.entities.User;

public class SaleReviewDTO {
    private User customer;
    private Car car;
    private int discount;
    private Double carPrice;
    private Double finalCarPrice;

    public SaleReviewDTO() {
    }

    public User getCustomer() {
        return customer;
    }

    public void setCustomer(User customer) {
        this.customer = customer;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public Double getCarPrice() {
        return carPrice;
    }

    public void setCarPrice(Double carPrice) {
        this.carPrice = carPrice;
    }

    public Double getFinalCarPrice() {
        return finalCarPrice;
    }

    public void setFinalCarPrice(Double finalCarPrice) {
        this.finalCarPrice = finalCarPrice;
    }
}

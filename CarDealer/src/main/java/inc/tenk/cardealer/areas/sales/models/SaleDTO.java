package inc.tenk.cardealer.areas.sales.models;

import inc.tenk.cardealer.areas.products.cars.entities.Car;
import inc.tenk.cardealer.areas.products.cars.models.CarDTO;

import inc.tenk.cardealer.areas.products.parts.models.PartDTO;
import inc.tenk.cardealer.areas.users.entities.User;
import inc.tenk.cardealer.areas.users.models.UserDTO;

public class SaleDTO {
   private PartDTO part;
   private CarDTO car;
   private UserDTO user;

    public SaleDTO() {
    }

    public PartDTO getPart() {
        return part;
    }

    public void setPart(PartDTO part) {
        this.part = part;
    }

    public CarDTO getCar() {
        return car;
    }

    public void setCar(CarDTO car) {
        this.car = car;
    }

    public UserDTO getUser() {
        return user;
    }

    public void setUser(UserDTO user) {
        this.user = user;
    }
}

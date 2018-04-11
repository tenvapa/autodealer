package inc.tenk.cardealer.areas.cars.services;


import inc.tenk.cardealer.areas.cars.entities.Car;
import inc.tenk.cardealer.areas.cars.models.CarDTO;
import inc.tenk.cardealer.areas.cars.models.PublishCarDTO;

import java.util.List;

public interface CarService {
    List<CarDTO> all();
    boolean edit(Long id, PublishCarDTO carDTO);
    boolean delete(Long id);
    boolean publish(PublishCarDTO carDTO);
    PublishCarDTO get(String make, String model, int year);
    PublishCarDTO get(Long id);
}

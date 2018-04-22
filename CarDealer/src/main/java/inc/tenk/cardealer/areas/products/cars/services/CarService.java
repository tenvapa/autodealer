package inc.tenk.cardealer.areas.products.cars.services;


import inc.tenk.cardealer.areas.products.cars.entities.Car;
import inc.tenk.cardealer.areas.products.cars.models.CarDTO;
import inc.tenk.cardealer.areas.products.cars.models.PublishCarDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CarService {
    List<CarDTO> all();
    boolean edit(Long id, PublishCarDTO carDTO);
    boolean delete(Long id);
    boolean publish(PublishCarDTO carDTO);
    PublishCarDTO get(String make, String model, int year);
    CarDTO get(Long id);
    Page<CarDTO> listAllByPage(Pageable pageable);
    Page<CarDTO> search(Pageable pageable,String searchValue);
}

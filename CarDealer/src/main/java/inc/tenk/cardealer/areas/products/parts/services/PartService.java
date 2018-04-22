package inc.tenk.cardealer.areas.products.parts.services;

import inc.tenk.cardealer.areas.products.cars.entities.Car;
import inc.tenk.cardealer.areas.products.cars.models.CarDTO;
import inc.tenk.cardealer.areas.products.parts.entities.Part;
import inc.tenk.cardealer.areas.products.parts.models.PartDTO;
import inc.tenk.cardealer.areas.products.parts.models.PublishPartDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PartService {
    boolean publish(PublishPartDTO partDTO);
    List<PartDTO> all();
    boolean delete(Long id);
    boolean edit(Long id, PublishPartDTO partEditDTO);
    PartDTO get(Long id);
    void insert(Part part);
    Page<PartDTO> listAllByPage(Pageable pageable);
    Page<PartDTO> search(Pageable pageable, String searchValue);
}

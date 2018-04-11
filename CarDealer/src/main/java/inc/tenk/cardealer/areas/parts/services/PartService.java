package inc.tenk.cardealer.areas.parts.services;

import inc.tenk.cardealer.areas.parts.models.PartDTO;
import inc.tenk.cardealer.areas.parts.models.PublishPartDTO;
import inc.tenk.cardealer.areas.parts.entities.Part;

import java.util.List;

public interface PartService {
    boolean publish(PublishPartDTO partDTO);
    List<PartDTO> all();
    boolean delete(Long id);
    boolean edit(Long id, PublishPartDTO partEditDTO);
    PublishPartDTO get(Long id);
}

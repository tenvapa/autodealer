package inc.tenk.cardealer.areas.parts.services;

import inc.tenk.cardealer.areas.parts.models.PartDTO;
import inc.tenk.cardealer.areas.parts.models.PublishPartDTO;
import inc.tenk.cardealer.areas.parts.entities.Part;

import inc.tenk.cardealer.areas.parts.repositories.PartRepository;
import inc.tenk.cardealer.exceptions.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class PartServiceImpl implements PartService{
    private PartRepository partRepository;
    private ModelMapper mapper;

    @Autowired
    public PartServiceImpl(PartRepository partRepository) {
        this.partRepository = partRepository;
        this.mapper = new ModelMapper();

    }

    @Override
    public boolean publish(PublishPartDTO partDTO) {
        Part part=partRepository.findFirstByDescription(partDTO.getDescription());
        if(part!=null) {
             return false;
        }
        part = new Part(partDTO.getDescription(), partDTO.getPrice(), partDTO.getQuantity());
        this.partRepository.saveAndFlush(part);
        return true;
    }

    @Override
    public List<PartDTO> all() {
        return Arrays.asList(this.mapper.map(this.partRepository.all(),PartDTO[].class));
    }

    @Override
    public boolean delete(Long id) {
        if(this.partRepository.getOne(id)==null) {
            return false;
        }
        this.partRepository.delete(id);
        return true;
    }

    @Override
    public boolean edit(Long id, PublishPartDTO partEditDTO) {
        if(this.partRepository.getOne(id)==null) {
            throw new EntityNotFoundException();
        }
        this.partRepository.update(id,partEditDTO.getDescription(),partEditDTO.getPrice(),partEditDTO.getQuantity());
        return false;
    }

    @Override
    public PublishPartDTO get(Long id) {
        if(this.partRepository.getOne(id)==null) {
            return null;
        }
        return this.mapper.map(this.partRepository.findOne(id),PublishPartDTO.class);
    }
}

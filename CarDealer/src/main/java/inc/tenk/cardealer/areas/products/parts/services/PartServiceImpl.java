package inc.tenk.cardealer.areas.products.parts.services;

import inc.tenk.cardealer.areas.products.cars.entities.Car;
import inc.tenk.cardealer.areas.products.cars.models.CarDTO;
import inc.tenk.cardealer.areas.products.parts.models.PartDTO;
import inc.tenk.cardealer.areas.products.parts.models.PublishPartDTO;
import inc.tenk.cardealer.areas.products.parts.entities.Part;

import inc.tenk.cardealer.areas.products.parts.repositories.PartRepository;
import inc.tenk.cardealer.exceptions.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
        this.partRepository.save(part);
        return true;
    }

    @Override
    public List<PartDTO> all() {
        return Arrays.asList(this.mapper.map(this.partRepository.all(),PartDTO[].class));
    }

    @Override
    public boolean delete(Long id) {
        if(this.partRepository.findOne(id)==null) {
            return false;
        }
        this.partRepository.delete(id);
        return true;
    }

    @Override
    public boolean edit(Long id, PublishPartDTO partEditDTO) {
        if(this.partRepository.findOne(id)==null) {
            throw new EntityNotFoundException();
        }
        if(partEditDTO.getQuantity()>0) {
            partEditDTO.setInStock(true);
        }
        this.partRepository.update(id,partEditDTO.getDescription(),
                partEditDTO.getPrice(),partEditDTO.getQuantity(),partEditDTO.isInStock());
        return false;
    }

    @Override
    public PartDTO get(Long id) {
        if(this.partRepository.findOne(id)==null) {
            return null;
        }
        return this.mapper.map(this.partRepository.findOne(id),PartDTO.class);
    }

    @Override
    public void insert(Part part) {
        if(part==null) {
            throw new NullPointerException();
        }
        this.partRepository.save(part);
    }

    @Override
    public Page<PartDTO> listAllByPage(Pageable pageable) {
        Page<Part> parts = this.partRepository.all(pageable);
        Page<PartDTO> partDTOS = parts.map(part -> this.mapper.map(part, PartDTO.class));
        return partDTOS;
    }
    @Override
    public Page<PartDTO> search(Pageable pageable, String searchValue) {
        searchValue = searchValue.toLowerCase();
        Page<Part> parts = this.partRepository.foundParts(pageable,searchValue);
        Page<PartDTO> partDTOS = parts.map(part -> this.mapper.map(part, PartDTO.class));
        return partDTOS;
    }
}

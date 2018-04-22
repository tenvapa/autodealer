package inc.tenk.cardealer.areas.products.cars.services;

import inc.tenk.cardealer.areas.products.cars.entities.Car;
import inc.tenk.cardealer.areas.products.cars.models.CarDTO;
import inc.tenk.cardealer.areas.products.cars.models.PublishCarDTO;
import inc.tenk.cardealer.areas.products.cars.repositories.CarRepository;
import inc.tenk.cardealer.areas.products.parts.models.PartDTO;
import inc.tenk.cardealer.exceptions.EntityNotFoundException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class CarServiceImpl implements CarService{
    private CarRepository carRepository;
    private ModelMapper mapper;

    @Autowired
    public CarServiceImpl(CarRepository carRepository) {
        this.carRepository = carRepository;
        this.mapper = new ModelMapper();
    }


    @Override
    public List<CarDTO> all() {
        CarDTO[] carDTOS = this.mapper.map(this.carRepository.getAllSortedByPublicationDate(), CarDTO[].class);
        return Arrays.asList(carDTOS);
    }

    @Override
    public boolean edit(Long id, PublishCarDTO carDTO) {
        if(this.carRepository.findOne(id)==null) {
            throw new EntityNotFoundException();
        }
        this.carRepository.updateById(id,carDTO.getMake(),carDTO.getModel(),
                carDTO.getYear(),carDTO.getDescription(),carDTO.getPrice(),carDTO.isInStock());
        return false;
    }

    @Override
    public boolean delete(Long id) {
        if (this.carRepository.findOne(id)==null) {
            throw new EntityNotFoundException();
        }
        this.carRepository.delete(id);
        return true;
    }

    @Override
    public boolean publish(PublishCarDTO carDTO) {
        Car car = new Car(carDTO.getMake(),carDTO.getModel(),
                carDTO.getYear(),carDTO.getPrice(),carDTO.getDescription());
        this.carRepository.save(car);
        return true;
    }

    @Override
    public PublishCarDTO get(String make, String model, int year) {
        return this.mapper.map(this.carRepository.getCarByMakeAndModelAndYear(make, model, year), PublishCarDTO.class);
    }

    @Override
    public CarDTO get(Long id) {
        if (this.carRepository.findOne(id)==null) {
            return null;
        }
        return this.mapper.map(this.carRepository.findOne(id),CarDTO.class);
    }

    @Override
    public Page<CarDTO> listAllByPage(Pageable pageable) {
        Page<Car> cars = this.carRepository.getAllSortedByPublicationDate(pageable);
        Page<CarDTO> carDTOS = cars.map(car -> mapper.map(car, CarDTO.class));
        return carDTOS;
    }

    @Override
    public Page<CarDTO> search(Pageable pageable, String searchValue) {
        searchValue = searchValue.toLowerCase();
        Page<Car> cars = this.carRepository.foundCars(pageable, searchValue);
        Page<CarDTO> carDTOS = cars.map(car -> mapper.map(car, CarDTO.class));
        return carDTOS;
    }

}

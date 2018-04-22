package inc.tenk.cardealer.areas.users.services;

import inc.tenk.cardealer.areas.products.cars.entities.Car;
import inc.tenk.cardealer.areas.products.cars.repositories.CarRepository;

import inc.tenk.cardealer.areas.products.parts.entities.Part;
import inc.tenk.cardealer.areas.products.parts.repositories.PartRepository;
import inc.tenk.cardealer.areas.users.entities.Cart;
import inc.tenk.cardealer.areas.users.repositories.CartRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class CartServiceImpl implements CartService {
    private ModelMapper modelMapper;
    private CartRepository cartRepository;
    private PartRepository partRepository;
    private CarRepository carRepository;


    public CartServiceImpl(CartRepository cartRepository,
                           PartRepository partRepository, CarRepository carRepository) {
        this.cartRepository = cartRepository;
        this.partRepository = partRepository;
        this.carRepository = carRepository;
        this.modelMapper = new ModelMapper();
    }

    @Override
    public void insert(Cart cart) {
        if(cart==null) return;
        this.cartRepository.save(cart);
    }

    @Override
    public Cart addCar(Long carId, Cart cart) {
        Car car = this.carRepository.findOne(carId);
        if(car==null) {
            throw new NullPointerException();
        }
        cart.getCars().add(car);
        car.getCarts().add(cart);
        return cart;
    }

    @Override
    public Cart addPart(Long productId, Cart cart) {
        Part part = this.partRepository.findOne(productId);
        if(part==null) {
            throw new NullPointerException();
        }
        cart.getParts().add(part);
        part.getCarts().add(cart);
        return cart;
    }

    @Override
    public boolean removeCar(Long carId, Cart cart) {
        Car car = this.carRepository.findOne(carId);
        if(car==null) {
            throw new NullPointerException();
        }
        car.getCarts().remove(cart);
        cart.getCars().remove(car);
        return true;
    }

    @Override
    public boolean removePart(Long productId, Cart cart) {
        Part part = this.partRepository.findOne(productId);
        if(part==null) {
            throw new NullPointerException();
        }
        cart.getParts().remove(part);
        part.getCarts().remove(cart);
        return true;
    }

    @Override
    public BigDecimal totalAmount(Cart cart) {
        double totalAmount = 0;
        for (Car car : cart.getCars()) {
            totalAmount+=car.getPrice().doubleValue();
        }
        for (Part part : cart.getParts()) {
            totalAmount+=part.getPrice().doubleValue();
        }
        return new BigDecimal(totalAmount);
    }
}

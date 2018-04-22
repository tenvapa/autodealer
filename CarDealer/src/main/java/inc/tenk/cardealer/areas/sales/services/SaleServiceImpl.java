package inc.tenk.cardealer.areas.sales.services;

import inc.tenk.cardealer.areas.products.cars.entities.Car;
import inc.tenk.cardealer.areas.products.parts.entities.Part;
import inc.tenk.cardealer.areas.sales.entities.Sale;
import inc.tenk.cardealer.areas.sales.models.SaleDTO;
import inc.tenk.cardealer.areas.users.entities.Cart;
import inc.tenk.cardealer.areas.users.entities.User;
import inc.tenk.cardealer.areas.users.models.UserDTO;
import inc.tenk.cardealer.areas.users.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import inc.tenk.cardealer.areas.sales.repositories.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SaleServiceImpl implements SaleService {
    private SaleRepository saleRepository;
    private UserRepository userRepository;
    private ModelMapper mapper;

    @Autowired
    public SaleServiceImpl(SaleRepository saleRepository, UserRepository userRepository) {
        this.saleRepository = saleRepository;
        this.userRepository = userRepository;
        this.mapper = new ModelMapper();
    }

    @Override
    public void finalizeSales(Cart cart, UserDTO userDTO) {
        User user = this.userRepository.getOne(userDTO.getId());
        for (Part part : cart.getParts()) {
            part.setQuantity(part.getQuantity()-1);
            if(part.getQuantity()<=0) {
                part.setInStock(false);
            }
            Sale sale = new Sale(part, user);
            cart.getParts().remove(part);
            part.getCarts().remove(cart);
            this.saleRepository.save(sale);
        }
        for (Car car : cart.getCars()) {
            Sale sale = new Sale(car, user);
            car.setInStock(false);
            cart.getCars().remove(car);
            car.getCarts().remove(cart);
            this.saleRepository.save(sale);
        }
    }

    @Override
    public List<SaleDTO> all() {
        List<Sale> sales = this.saleRepository.findAll();
        List<SaleDTO> saleDTOS = new ArrayList<>();
        sales.stream().forEach(sale -> saleDTOS.add(this.mapper.map(sale, SaleDTO.class)));
        return saleDTOS;
    }

    @Override
    public List<SaleDTO> salesByUser(String username) {
        User user = this.userRepository.findByUsername(username);
        List<Sale> sales = this.saleRepository.salesByUser(user.getId());
        List<SaleDTO> saleDTOS = new ArrayList<>();
        sales.stream().forEach(sale -> saleDTOS.add(this.mapper.map(sale, SaleDTO.class)));
        return saleDTOS;
    }
}

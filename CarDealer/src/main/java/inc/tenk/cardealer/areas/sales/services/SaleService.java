package inc.tenk.cardealer.areas.sales.services;

import inc.tenk.cardealer.areas.sales.models.SaleDTO;
import inc.tenk.cardealer.areas.users.entities.Cart;
import inc.tenk.cardealer.areas.users.models.UserDTO;

import java.util.List;

public interface SaleService {
    void finalizeSales(Cart cart, UserDTO userDTO);
    List<SaleDTO> all();
    List<SaleDTO> salesByUser(String username);
}

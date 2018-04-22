package inc.tenk.cardealer.areas.users.services;

import inc.tenk.cardealer.areas.users.entities.Cart;

import java.math.BigDecimal;

public interface CartService {
    Cart addCar(Long carId, Cart cart);
    Cart addPart(Long partId, Cart cart);
    boolean removeCar(Long carId, Cart cart);
    boolean removePart(Long partId, Cart cart);
    BigDecimal totalAmount(Cart cart);
    void insert(Cart cart);
}

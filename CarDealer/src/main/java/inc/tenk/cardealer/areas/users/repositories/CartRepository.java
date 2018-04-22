package inc.tenk.cardealer.areas.users.repositories;

import inc.tenk.cardealer.areas.products.cars.entities.Car;
import inc.tenk.cardealer.areas.products.parts.entities.Part;
import inc.tenk.cardealer.areas.users.entities.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Repository
@Transactional
public interface CartRepository extends JpaRepository<Cart, Long> {
    @Query("update Cart c set c.cars=:cars where c.id=:id")
    @Modifying
    void updateCartCars(@Param("id") Long id,@Param("cars") Set<Car> cars);
    @Query("update Cart c set c.parts=:parts where c.id=:id")
    @Modifying
    void updateCartParts(@Param("id") Long id,@Param("parts") Set<Part> parts);
}

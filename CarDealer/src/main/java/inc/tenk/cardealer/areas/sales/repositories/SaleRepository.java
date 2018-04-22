package inc.tenk.cardealer.areas.sales.repositories;

import inc.tenk.cardealer.areas.sales.entities.Sale;
import inc.tenk.cardealer.areas.users.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface SaleRepository extends JpaRepository<Sale, Long> {

    @Query("select s from Sale s where s.user.id=:id")
    List<Sale> salesByUser(@Param("id") Long id);
}

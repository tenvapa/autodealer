package inc.tenk.cardealer.areas.sales.repositories;

import inc.tenk.cardealer.areas.sales.entities.Sale;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleRepository extends JpaRepository<Sale, Long> {
//    @Query("select new SaleDTO" +
//            "(c.make,c.model, c.year, concat(cu.firstName,cu.lastName), (sum(p.price*p.quantity)-(s.discount*(sum(p.price*p.quantity)))), s.discount*100 ) " +
//            "from Sale s join s.car c join c.parts p join s.user cu group by s.id")
//    List<SaleDTO> getAllSalesInfo();

//    @Query("select new SaleDTO" +
//            "(c.make,c.model, c.year, concat(cu.firstName,cu.lastName), (sum(p.price*p.quantity)-(s.discount*(sum(p.price*p.quantity)))), s.discount*100 ) " +
//            "from Sale s join s.car c join c.parts p join s.user cu where s.id=:id group by s.id")
//    SaleDTO getSaleById(@Param("id") Long id);
//
//    @Query("select new SaleDTO" +
//            "(c.make,c.model, c.year, concat(cu.firstName,cu.lastName), (sum(p.price*p.quantity)-(s.discount*(sum(p.price*p.quantity)))), s.discount*100 ) " +
//            "from Sale s join s.car c join c.parts p join s.user cu where s.discount>0 group by s.id")
//    List<SaleDTO> getDiscountedSales();

//    @Query("select new SaleDTO" +
//            "(c.make,c.model, c.year, concat(cu.firstName,cu.lastName), (sum(p.price*p.quantity)-(s.discount*(sum(p.price*p.quantity)))), s.discount*100 ) " +
//            "from Sale s join s.car c join c.parts p join s.user cu where s.discount=:discountPercent group by s.id")
//    List<SaleDTO> getDiscountedSalesByPercent(@Param("discountPercent") double discountPercent);

}

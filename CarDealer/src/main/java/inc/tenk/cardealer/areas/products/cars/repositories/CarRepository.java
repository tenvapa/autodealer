package inc.tenk.cardealer.areas.products.cars.repositories;

import inc.tenk.cardealer.areas.products.cars.entities.Car;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Repository
@Transactional
public interface CarRepository extends PagingAndSortingRepository<Car, Long> {

    Car getCarByMakeAndModelAndYear(String make, String model, int year);

    @Query("select c from Car c where c.inStock=true order by c.publicationDate desc")
    List<Car> getAllSortedByPublicationDate();

    @Query("select c from Car c where c.inStock=true order by c.publicationDate desc")
    Page<Car> getAllSortedByPublicationDate(Pageable pageable);

    @Modifying
    @Query("update Car c set c.make=:make, c.model=:model,c.year=:year,c.description=:description, c.price=:price, c.inStock=:inStock where c.id=:id")
    void updateById(@Param("id") Long id, @Param("make") String make, @Param("model") String model,
                    @Param("year") Integer year, @Param("description") String description,
                    @Param("price") BigDecimal price, @Param("inStock") boolean inStock);


    @Query("select c from Car c where concat(c.make,' ',c.model,' ',c.year) like %:value% and c.inStock=true")
    Page<Car> foundCars(Pageable pageable,@Param("value") String value);
}

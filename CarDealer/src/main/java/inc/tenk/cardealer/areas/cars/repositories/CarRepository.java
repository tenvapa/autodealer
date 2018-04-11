package inc.tenk.cardealer.areas.cars.repositories;

import inc.tenk.cardealer.areas.cars.entities.Car;
import org.springframework.data.jpa.repository.JpaRepository;
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
    List<Car> findByMakeOrderByModelAscYearDesc(String make);

    @Query("select c from Car c where c.id=:id")
    List<Car> getAllCarsWithTheirParts(@Param("id") Long id);

    Car getCarByMakeAndModelAndYear(String make, String model, int year);

    @Query("select c from Car c order by c.publicationDate desc")
    List<Car> getAllSortedByPublicationDate();

    @Modifying
    @Query("update Car c set c.make=:make, c.model=:model,c.year=:year,c.description=:description, c.price=:price where c.id=:id")
    void updateById(@Param("id") Long id, @Param("make") String make, @Param("model") String model,
                    @Param("year") Integer year, @Param("description") String description, @Param("price") BigDecimal price);


}

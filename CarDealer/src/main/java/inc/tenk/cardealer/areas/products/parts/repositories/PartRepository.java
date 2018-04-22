package inc.tenk.cardealer.areas.products.parts.repositories;

import inc.tenk.cardealer.areas.products.cars.entities.Car;
import inc.tenk.cardealer.areas.products.parts.entities.Part;
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
public interface PartRepository extends PagingAndSortingRepository<Part, Long> {
    @Modifying
    @Query("delete from Part p where p.id=:id")
    void delete(@Param("id") Long id);
    @Query("select p from Part p where p.inStock=true order by p.publicationDate desc")
    List<Part> all();
    @Query("select p from Part p where p.inStock=true order by p.publicationDate desc")
    Page<Part> all(Pageable pageable);

    @Modifying
    @Query("update Part p set p.description=:description, p.price=:price, p.quantity=:quantity, p.inStock=:inStock where p.id=:id")
    void update(@Param("id") Long id, @Param("description") String description ,
                @Param("price") BigDecimal price, @Param("quantity") int quantity,@Param("inStock") boolean inStock);

    Part findFirstByDescription(String description);

    @Query("select p from Part p where p.description like %:value% and p.inStock=true")
    Page<Part> foundParts(Pageable pageable, @Param("value") String value);
}

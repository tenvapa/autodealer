package inc.tenk.cardealer.areas.parts.repositories;

import inc.tenk.cardealer.areas.parts.entities.Part;
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
public interface PartRepository extends PagingAndSortingRepository<Part, Long> {
    @Modifying
    @Query("delete from Part p where p.id=:id")
    void delete(@Param("id") Long id);
    @Query("select p from Part p order by p.publicationDate desc")
    List<Part> all();

    @Modifying
    @Query("update Part p set p.description=:description, p.price=:price, p.quantity=:quantity where p.id=:id")
    void update(@Param("id") Long id, @Param("description") String description , @Param("price") BigDecimal price, @Param("quantity") int quantity);

    Part findFirstByDescription(String description);
}

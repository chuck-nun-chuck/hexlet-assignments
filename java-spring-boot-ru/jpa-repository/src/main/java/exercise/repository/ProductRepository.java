package exercise.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;
import exercise.model.Product;

import org.springframework.data.domain.Sort;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    // BEGIN
    List<Product> findByPriceBetweenOrderByPriceAsc(Integer min, Integer max);

    List<Product> findByPriceGreaterThanEqualOrderByPriceAsc(Integer min);

    List<Product> findByPriceLessThanEqualOrderByPriceAsc(Integer max);

    List<Product> findAllByOrderByPriceAsc();
    // END
}

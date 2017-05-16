package es.mabu.mr.product;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, CustomProductRepository {

	Product findByName(@Param("name") String name);

	List<Product> findByCategoryId(@Param("categoryId") Long categoryId);

	List<Product> findByTaxeId(@Param("taxeId") Long taxeId);

}

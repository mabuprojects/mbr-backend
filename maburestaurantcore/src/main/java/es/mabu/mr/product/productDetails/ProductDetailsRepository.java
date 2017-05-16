package es.mabu.mr.product.productDetails;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductDetailsRepository extends JpaRepository<ProductDetails, Long> {

	public ProductDetails findByProductIdAndRestaurantId(@Param("productId") Long productId,
			@Param("restaurantId") Long restaurantId);

}

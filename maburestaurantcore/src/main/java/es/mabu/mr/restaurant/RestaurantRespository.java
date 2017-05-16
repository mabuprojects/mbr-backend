package es.mabu.mr.restaurant;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RestaurantRespository extends JpaRepository<Restaurant, Long> {

	Restaurant findByName(@Param("name") String name);

}

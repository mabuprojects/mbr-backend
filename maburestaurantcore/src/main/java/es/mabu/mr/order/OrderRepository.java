package es.mabu.mr.order;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

	@Query("SELECT order FROM orderr order where order.restaurant.id = :id AND order.status != :status AND order.status != :status2")
	List<Order> findOpenOrdersByRestaurant(@Param("id") Long restaurantId, @Param("status") OrderStatus status,
			@Param("status2") OrderStatus status2);

	List<Order> findByClientIdOrderByCreatedDesc(@Param("clientId") Long clientId);

	// @Query("")

	@Query(value = "select  sales, total,mon from ( select count(1) as sales, sum(totalprice) as total ,date_part('month',created) as mon , date_part('year',created) as year from orderr where  status='CLOSED' and created >= date_trunc('month', current_date - interval '5' month) group by year, mon order by year , mon) a;", nativeQuery = true)
	List<Object[]> getLast6MonthsData();
}

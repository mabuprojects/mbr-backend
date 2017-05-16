package es.mabu.mr.book;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.mabu.mr.restaurant.Restaurant;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

	public List<Book> findBooksByRestaurant(Restaurant restaurant);
}

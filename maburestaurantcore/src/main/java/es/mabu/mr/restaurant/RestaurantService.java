package es.mabu.mr.restaurant;

import java.util.List;

import es.mabu.mr.exception.exception.DuplicatedInstanceException;
import es.mabu.mr.exception.exception.NotValidMinPriceDeliveryException;
import es.mabu.mr.restaurant.timetable.TimeTableErrorException;

public interface RestaurantService {

	public List<Restaurant> findAllRestaurant();

	public Restaurant findRestaurantById(Long RestaurantId);

	public Restaurant createRestaurant(Restaurant restaurant)
			throws DuplicatedInstanceException, NotValidMinPriceDeliveryException, TimeTableErrorException;

	public void removeRestaurant(Long restaurantId);

	public Restaurant updateRestaurant(Restaurant restaurant)
			throws DuplicatedInstanceException, NotValidMinPriceDeliveryException, TimeTableErrorException;

	public boolean existRestaurantName(String restaurantName);

}

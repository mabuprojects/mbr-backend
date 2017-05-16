package es.mabu.mr.restaurant;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import es.mabu.mr.address.AddressRepository;
import es.mabu.mr.exception.exception.DuplicatedInstanceException;
import es.mabu.mr.exception.exception.NotValidMinPriceDeliveryException;
import es.mabu.mr.restaurant.timetable.TimeTableErrorException;

@RestController
@RequestMapping("/public/restaurant")
public class RestaurantController {

	@Autowired
	RestaurantService restaurantService;

	@Autowired
	AddressRepository addressRepository;

	/**
	 * Return all restaurants
	 * 
	 * @return
	 * @throws NotValidMinPriceDeliveryException
	 */
	@RequestMapping(method = RequestMethod.GET, produces = "application/json")
	List<Restaurant> getRestaurants() {
		List<Restaurant> list = restaurantService.findAllRestaurant();

		list.stream().forEach(e -> {
			e.getServices().size();
			e.getZipCodes().size();
		});
		list.stream().filter(e -> e != null && e.getTimeTable() != null && e.getTimeTable().getDays() != null)
				.forEach(e -> {
					e.getTimeTable().getDays().size();

				});
		return list;
	}

	/**
	 * Crea Restaurant
	 * 
	 * @param restaurant
	 * @return
	 * @throws DuplicatedInstanceException
	 * @throws NotValidMinPriceDeliveryException
	 * @throws TimeTableErrorException
	 */
	@RequestMapping(method = RequestMethod.POST, produces = "application/json")
	Restaurant createRestaurant(@RequestBody Restaurant restaurant)
			throws DuplicatedInstanceException, NotValidMinPriceDeliveryException, TimeTableErrorException {
		return restaurantService.createRestaurant(restaurant);
	}

	/**
	 * Comprueba si existe un restaurante con ese nombre
	 * 
	 * @param restaurantName
	 * @return true -> si existe ; false -> si no existe
	 */
	@RequestMapping(method = RequestMethod.GET, value = "exists/{restaurantName}")
	Boolean existRestaurantName(@PathVariable String restaurantName) {
		return restaurantService.existRestaurantName(restaurantName);
	}

	/**
	 * Actualiza los datos de un restaurante
	 * 
	 * @param restaurant
	 * @return
	 * @throws DuplicatedInstanceException
	 * @throws NotValidMinPriceDeliveryException
	 * @throws TimeTableErrorException
	 */
	@RequestMapping(method = RequestMethod.PATCH, produces = "application/json")
	Restaurant updateRestaurant(@RequestBody Restaurant restaurant)
			throws DuplicatedInstanceException, NotValidMinPriceDeliveryException, TimeTableErrorException {
		return restaurantService.updateRestaurant(restaurant);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{restaurantId}")
	Boolean deleteCategory(@PathVariable Long restaurantId) {
		restaurantService.removeRestaurant(restaurantId);
		return true;
	}
}

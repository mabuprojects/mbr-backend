
package es.mabu.mr.restaurant;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.mabu.mr.address.Address;
import es.mabu.mr.address.AddressRepository;
import es.mabu.mr.exception.exception.DuplicatedInstanceException;
import es.mabu.mr.exception.exception.NotValidMinPriceDeliveryException;
import es.mabu.mr.restaurant.timetable.TimeTable;
import es.mabu.mr.restaurant.timetable.TimeTableErrorException;
import es.mabu.mr.restaurant.timetable.TimeTableService;

@Service
public class RestaurantServiceImpl implements RestaurantService {

	@Autowired
	RestaurantRespository restaurantRepository;

	@Autowired
	AddressRepository addressRepository;

	@Autowired
	TimeTableService timeTableService;

	@Override
	@Transactional(readOnly = true)
	public List<Restaurant> findAllRestaurant() {
		return restaurantRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Restaurant findRestaurantById(Long RestaurantId) {
		return restaurantRepository.findOne(RestaurantId);
	}

	@Override
	@Transactional
	public Restaurant createRestaurant(Restaurant restaurant)
			throws DuplicatedInstanceException, NotValidMinPriceDeliveryException, TimeTableErrorException {
		// TODO Comprobar que todos los campos requiered vienen correctamente

		if (restaurantRepository.findByName(restaurant.getName()) != null) {
			throw new DuplicatedInstanceException("There is a restaurant with name '" + restaurant.getName() + "'");
		}

		if (restaurant.getMinPriceDelivery().compareTo(new BigDecimal(0)) == -1) {
			throw new NotValidMinPriceDeliveryException("MinPriceDelivery should be greater than 0");
		}

		Address a = addressRepository.save(restaurant.getAddress());

		TimeTable timeTable = timeTableService.save(restaurant.getTimeTable());

		Restaurant r = restaurantRepository
				.save(new Restaurant(restaurant.getName(), restaurant.getNif(), restaurant.getEmail(),
						restaurant.getMinPriceDelivery(), restaurant.getTransportPrice(), restaurant.getPhoneNumber(),
						restaurant.getVisible(), restaurant.getServices(), a, restaurant.getZipCodes(), timeTable));

		return restaurantRepository.save(r);

	}

	@Override
	@Transactional
	public void removeRestaurant(Long restaurantId) {
		restaurantRepository.delete(restaurantId);
	}

	@Override
	@Transactional
	public Restaurant updateRestaurant(Restaurant restaurant)
			throws DuplicatedInstanceException, NotValidMinPriceDeliveryException, TimeTableErrorException {

		Restaurant old = restaurantRepository.findOne(restaurant.getId());

		if (!restaurant.getName().equals(old.getName())) {
			if (restaurantRepository.findByName(restaurant.getName()) != null) {
				throw new DuplicatedInstanceException("There is a restaurant with name '" + restaurant.getName() + "'");
			}
		}

		if (restaurant.getMinPriceDelivery().compareTo(new BigDecimal(0)) == -1) {
			throw new NotValidMinPriceDeliveryException("MinPriceDelivery should be greater than 0");
		}

		if (!restaurant.getAddress().equals(old.getAddress())) {
			addressRepository.save(restaurant.getAddress());
		}
		timeTableService.save(restaurant.getTimeTable());
		return restaurantRepository.save(restaurant);
	}

	@Override
	@Transactional(readOnly = true)
	public boolean existRestaurantName(String restaurantName) {
		return (restaurantRepository.findByName(restaurantName) != null) ? true : false;

	}

}

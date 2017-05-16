package es.mabu.mr.restaurant;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.management.InstanceAlreadyExistsException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import es.mabu.mr.address.Address;
import es.mabu.mr.exception.exception.DuplicatedInstanceException;
import es.mabu.mr.exception.exception.NotValidMinPriceDeliveryException;
import es.mabu.mr.restaurant.timetable.TimeTableErrorException;
import es.mabu.mr.restaurant.timetable.TimeTableUtil;

@RunWith(SpringRunner.class)
@SpringBootTest
@Rollback
@Transactional
public class RestaurantServiceTest {
	@Autowired
	RestaurantService restaurantService;
	private static final String PHONE_NUMBER = "637815611";

	private static final Address address = new Address("ESPAÑA", "GALICIA", "A CORUÑA", "AVDA MONELOS", "Nº19-23", "4A",
			"15009", "");
	private static final List<Integer> zipCodes = new LinkedList<>(Arrays.asList(1));
	private static final Set<RestaurantServiceType> service = new HashSet<>(
			Arrays.asList(RestaurantServiceType.DELIVERY));

	@Test
	public void createAndFindRestaurant()
			throws DuplicatedInstanceException, NotValidMinPriceDeliveryException, TimeTableErrorException {

		Restaurant r1 = restaurantService.createRestaurant(new Restaurant("Galipizza Coruña", "B27979797",
				"email1@gmail.com", new BigDecimal(5), new BigDecimal(5), PHONE_NUMBER, true, service, address,
				zipCodes, TimeTableUtil.getTimeTable()));

		Restaurant r2 = restaurantService.findRestaurantById(r1.getId());

		assertEquals(r1, r2);

	}

	@Test(expected = DuplicatedInstanceException.class)
	public void createRestaurant1()
			throws DuplicatedInstanceException, NotValidMinPriceDeliveryException, TimeTableErrorException {

		restaurantService.createRestaurant(new Restaurant("Galipizza Coruña", "B27979797", "email1@gmail.com",
				new BigDecimal(5), new BigDecimal(5), PHONE_NUMBER, true, service, address, zipCodes,
				TimeTableUtil.getTimeTable()));

		restaurantService.createRestaurant(new Restaurant("Galipizza Coruña", "B27979797", "email1@gmail.com",
				new BigDecimal(5), new BigDecimal(5), PHONE_NUMBER, true, service, address, zipCodes,
				TimeTableUtil.getTimeTable()));

	}

	@Test(expected = NotValidMinPriceDeliveryException.class)
	public void createRestaurant2()
			throws DuplicatedInstanceException, NotValidMinPriceDeliveryException, TimeTableErrorException {

		restaurantService.createRestaurant(new Restaurant("Galipizza Coruña", "B27979797", "email1@gmail.com",
				new BigDecimal(-5), new BigDecimal(5), PHONE_NUMBER, true, service, address, zipCodes,
				TimeTableUtil.getTimeTable()));

	}

	@Test
	public void updateRestaurant() throws InstanceAlreadyExistsException, DuplicatedInstanceException,
			NotValidMinPriceDeliveryException, TimeTableErrorException {

		Address address2 = new Address("ESPAÑA2", "GALICIA", "A CORUÑA", "AVDA MONELOS", "Nº19-23", "4A", "15009", "");

		Restaurant restaurant = new Restaurant("Galipizza Coruña", "B27979797", "email1@gmail.com", new BigDecimal(5),
				new BigDecimal(5), PHONE_NUMBER, true, service, address, zipCodes, TimeTableUtil.getTimeTable());

		Restaurant r1 = restaurantService.createRestaurant(restaurant);

		r1.setAddress(address2);
		r1.setName("nuevo restaurante");

		restaurantService.updateRestaurant(r1);

		Restaurant r2 = restaurantService.findRestaurantById(r1.getId());

		assertEquals(address2.getCountry(), r2.getAddress().getCountry());
		assertEquals(r1.getName(), r2.getName());
	}

	@Test
	public void findAllRestaurant() throws InstanceAlreadyExistsException, DuplicatedInstanceException,
			NotValidMinPriceDeliveryException, TimeTableErrorException {

		restaurantService.createRestaurant(new Restaurant("Galipizza Coruña", "B27979797", "email1@gmail.com",
				new BigDecimal(5), new BigDecimal(5), PHONE_NUMBER, true, service, address, zipCodes,
				TimeTableUtil.getTimeTable()));

		restaurantService.createRestaurant(new Restaurant("Galipizza2 Coruña", "B27979797", "email1@gmail.com",
				new BigDecimal(5), new BigDecimal(5), PHONE_NUMBER, true, service, address, zipCodes,
				TimeTableUtil.getTimeTable()));

		List<Restaurant> restaurants = restaurantService.findAllRestaurant();
		assertEquals(2, restaurants.size());
	}
}

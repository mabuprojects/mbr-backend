package es.mabu.mr.book;

import java.util.Calendar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.mabu.mr.restaurant.Restaurant;
import es.mabu.mr.restaurant.RestaurantService;
import es.mabu.mr.restaurant.timetable.Day;
import es.mabu.mr.user.client.Client;
import es.mabu.mr.user.client.ClientService;

@Service
public class BookServiceImpl implements BookService {

	@Autowired
	RestaurantService restaurantService;

	@Autowired
	ClientService clientService;

	@Autowired
	BookRepository bookRepository;

	@Override
	public Book bookTable(Long idClient, Long idRestaurant, Integer numberOfPersons, Calendar bookHour)
			throws CantBookException {
		Restaurant restaurant = restaurantService.findRestaurantById(idRestaurant);
		if (restaurant == null) {
			throw new CantBookException("Restaurant with id: " + idRestaurant + " not found");
		}
		Client client = clientService.findClientById(idClient);
		if (client == null) {
			throw new CantBookException("Client with id: " + idRestaurant + " not found");
		}

		if (!validateBookingHour(bookHour, restaurant)) {
			throw new CantBookException("Wrong hour");
		}

		return bookRepository.save(new Book(client, restaurant, bookHour, numberOfPersons));

	}

	private boolean validateBookingHour(Calendar bookHour, Restaurant restaurant) {

		if (!bookHour.after(Calendar.getInstance())) {
			return false;
		}
		int dayOfWeek = bookHour.get(Calendar.DAY_OF_WEEK);
		Day day = restaurant.getTimeTable().getDays().get(dayOfWeek - 1);
		int bookingTime = bookHour.get(Calendar.HOUR_OF_DAY) * 60 + bookHour.get(Calendar.MINUTE);
		if (day.getLastHourToMorningBook() > bookingTime) {
			return bookingTime > day.getOpeningMorning();
		} else {
			return bookingTime > day.getOpeningDinner() && bookingTime < day.getLastHourToDinnerBook();
		}
	}

}

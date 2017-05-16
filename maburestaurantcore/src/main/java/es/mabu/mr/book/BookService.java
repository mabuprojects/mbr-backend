package es.mabu.mr.book;

import java.util.Calendar;

public interface BookService {

	Book bookTable(Long idClient, Long idRestaurant, Integer numberOfPersons, Calendar bookHour)
			throws CantBookException;

}

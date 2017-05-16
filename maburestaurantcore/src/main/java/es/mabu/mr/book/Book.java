package es.mabu.mr.book;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.GenericGenerator;

import es.mabu.mr.restaurant.Restaurant;
import es.mabu.mr.user.client.Client;

@Entity(name = "book")
public class Book {

	@Id
	@GenericGenerator(name = "book_sequence", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
			@org.hibernate.annotations.Parameter(name = "sequence", value = "book_id_seq")

	})
	@GeneratedValue(generator = "book_sequence")
	private Long id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "clientid")
	private Client client;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "restaurantid")
	private Restaurant restaurant;

	private Calendar hour;

	private Integer numberOfPersons;

	public Book(Client client, Restaurant restaurant, Calendar hour, Integer numberOfPersons) {
		this.client = client;
		this.restaurant = restaurant;
		this.hour = hour;
		this.numberOfPersons = numberOfPersons;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	public Calendar getHour() {
		return hour;
	}

	public void setHour(Calendar hour) {
		this.hour = hour;
	}

	public Integer getNumberOfPersons() {
		return numberOfPersons;
	}

	public void setNumberOfPersons(Integer numberOfPersons) {
		this.numberOfPersons = numberOfPersons;
	}

}

package es.mabu.mr.utils;

import es.mabu.mr.address.Address;
import es.mabu.mr.restaurant.Restaurant;
import es.mabu.mr.restaurant.RestaurantDto;

public class RestaurantConversor {

	public static Restaurant restaurantToRestaurantDto(RestaurantDto rdto) {

		Address a = new Address(rdto.getAddress().getId(), rdto.getAddress().getCountry(), rdto.getAddress().getState(),
				rdto.getAddress().getCity(), rdto.getAddress().getStreet(), rdto.getAddress().getNumber(),
				rdto.getAddress().getUnity(), rdto.getAddress().getPostalCode(), rdto.getAddress().getObservations(),
				rdto.getAddress().getLatitude(), rdto.getAddress().getLatitude());

		return new Restaurant(rdto.getId(), rdto.getName(), rdto.getNif(), rdto.getEmail(), rdto.getMinPriceDelivery(),
				rdto.getTransportPrice(), rdto.getPhoneNumber(), rdto.getVisible(), rdto.getServices(), a,
				rdto.getZipCodes(), rdto.getTimeTable());

	}

}

package es.mabu.mr.restaurant;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.hibernate.annotations.GenericGenerator;

import es.mabu.mr.address.Address;
import es.mabu.mr.restaurant.timetable.TimeTable;

@Entity(name = "restaurant")
public class Restaurant {
	@Id
	@GenericGenerator(name = "restaurant_sequence", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
			@org.hibernate.annotations.Parameter(name = "sequence", value = "restaurant_id_seq")

	})
	@GeneratedValue(generator = "restaurant_sequence")
	private Long id;

	private String name;

	private String nif;

	@Column(name = "min_price_delivery")
	private BigDecimal minPriceDelivery;

	@Column(name = "trasport_price")
	private BigDecimal transportPrice;

	@Column(name = "phonenumber")
	private String phoneNumber;

	private Boolean visible;

	private String email;

	@ElementCollection

	@CollectionTable(name = "zip_code", joinColumns = @JoinColumn(name = "restaurant_id"))

	@Column(name = "zip_code")
	private List<Integer> zipCodes;

	@ElementCollection(targetClass = RestaurantServiceType.class)

	@CollectionTable(name = "restaurant_service_type", joinColumns = @JoinColumn(name = "restaurant_id"))

	@Column(name = "restaurant_service_type")

	@Enumerated(EnumType.STRING)
	private Set<RestaurantServiceType> services;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
	@JoinColumn(name = "addressid")
	private Address address;

	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.REMOVE)
	@JoinColumn(name = "time_table_id")
	private TimeTable timeTable;

	public Restaurant() {
		super();
	}

	public Restaurant(String name, String nif, String email, BigDecimal minPriceDelivery, BigDecimal tranpsortPrice,
			String phoneNumber, Boolean visible, Set<RestaurantServiceType> services, List<Integer> zipCodes,
			TimeTable timeTable) {
		this(name, nif, email, minPriceDelivery, tranpsortPrice, phoneNumber, visible, services, null, zipCodes,
				timeTable);
	}

	public Restaurant(String name, String nif, String email, BigDecimal minPriceDelivery, BigDecimal tranpsortPrice,
			String phoneNumber, Boolean visible, Set<RestaurantServiceType> services, Address address,
			List<Integer> zipCodes, TimeTable timeTable) {
		this(null, name, nif, email, minPriceDelivery, tranpsortPrice, phoneNumber, visible, services, address,
				zipCodes, timeTable);
	}

	public Restaurant(Long id, String name, String nif, String email, BigDecimal minPriceDelivery,
			BigDecimal tranpsortPrice, String phoneNumber, Boolean visible, Set<RestaurantServiceType> services,
			Address address, List<Integer> zipCodes, TimeTable timeTable) {
		super();
		this.id = id;
		this.name = name;
		this.nif = nif;
		this.minPriceDelivery = minPriceDelivery;
		this.phoneNumber = phoneNumber;
		this.visible = visible;
		this.services = services;
		this.address = address;
		this.transportPrice = tranpsortPrice;
		this.email = email;
		this.zipCodes = zipCodes;
		this.timeTable = timeTable;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNif() {
		return nif;
	}

	public void setNif(String nif) {
		this.nif = nif;
	}

	public BigDecimal getMinPriceDelivery() {
		return minPriceDelivery;
	}

	public void setMinPriceDelivery(BigDecimal minPriceDelivery) {
		this.minPriceDelivery = minPriceDelivery;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Boolean getVisible() {
		return visible;
	}

	public void setVisible(Boolean active) {
		this.visible = active;
	}

	public Set<RestaurantServiceType> getServices() {
		return services;
	}

	public void setServices(Set<RestaurantServiceType> services) {
		this.services = services;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public BigDecimal getTransportPrice() {
		return transportPrice;
	}

	public void setTransportPrice(BigDecimal tranpsortPrice) {
		this.transportPrice = tranpsortPrice;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<Integer> getZipCodes() {
		return zipCodes;
	}

	public void setZipCodes(List<Integer> zipCodes) {
		this.zipCodes = zipCodes;
	}

	public TimeTable getTimeTable() {
		return timeTable;
	}

	public void setTimeTable(TimeTable timeTable) {
		this.timeTable = timeTable;
	}

}

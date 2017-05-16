package es.mabu.mr.restaurant;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

import es.mabu.mr.address.AddressDto;
import es.mabu.mr.restaurant.timetable.TimeTable;

public class RestaurantDto {

	private Long id;

	private String name;

	private String nif;

	private String email;

	private BigDecimal minPriceDelivery;

	private BigDecimal minPriceDeliveryTransport;

	private BigDecimal transportPrice;

	private String phoneNumber;

	private Boolean visible;

	private Set<RestaurantServiceType> services;

	private AddressDto address;

	private List<Integer> zipCodes;

	private TimeTable timeTable;

	public RestaurantDto() {
		super();
	}

	public RestaurantDto(Long id, String name, String nif, String email, BigDecimal minPriceDelivery,
			BigDecimal minPriceDeliveryTranpsort, BigDecimal tranpsortPrice, String phoneNumber, Boolean visible,
			Set<RestaurantServiceType> services, AddressDto address, List<Integer> zipCodes, TimeTable timeTable) {
		this.id = id;
		this.name = name;
		this.nif = nif;
		this.email = email;
		this.minPriceDelivery = minPriceDelivery;
		this.minPriceDeliveryTransport = minPriceDeliveryTranpsort;
		this.transportPrice = tranpsortPrice;
		this.phoneNumber = phoneNumber;
		this.visible = visible;
		this.services = services;
		this.address = address;
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

	public Set<RestaurantServiceType> getServices() {
		return services;
	}

	public void setServices(Set<RestaurantServiceType> services) {
		this.services = services;
	}

	public AddressDto getAddress() {
		return address;
	}

	public void setAddress(AddressDto address) {
		this.address = address;
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

	public void setVisible(Boolean visible) {
		this.visible = visible;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public BigDecimal getMinPriceDeliveryTransport() {
		return minPriceDeliveryTransport;
	}

	public void setMinPriceDeliveryTransport(BigDecimal minPriceDeliveryTranpsort) {
		this.minPriceDeliveryTransport = minPriceDeliveryTranpsort;
	}

	public BigDecimal getTransportPrice() {
		return transportPrice;
	}

	public void setTransportPrice(BigDecimal tranpsortPrice) {
		this.transportPrice = tranpsortPrice;
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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((minPriceDelivery == null) ? 0 : minPriceDelivery.hashCode());
		result = prime * result + ((minPriceDeliveryTransport == null) ? 0 : minPriceDeliveryTransport.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((nif == null) ? 0 : nif.hashCode());
		result = prime * result + ((phoneNumber == null) ? 0 : phoneNumber.hashCode());
		result = prime * result + ((services == null) ? 0 : services.hashCode());
		result = prime * result + ((transportPrice == null) ? 0 : transportPrice.hashCode());
		result = prime * result + ((visible == null) ? 0 : visible.hashCode());
		result = prime * result + ((zipCodes == null) ? 0 : zipCodes.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RestaurantDto other = (RestaurantDto) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (minPriceDelivery == null) {
			if (other.minPriceDelivery != null)
				return false;
		} else if (!minPriceDelivery.equals(other.minPriceDelivery))
			return false;
		if (minPriceDeliveryTransport == null) {
			if (other.minPriceDeliveryTransport != null)
				return false;
		} else if (!minPriceDeliveryTransport.equals(other.minPriceDeliveryTransport))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (nif == null) {
			if (other.nif != null)
				return false;
		} else if (!nif.equals(other.nif))
			return false;
		if (phoneNumber == null) {
			if (other.phoneNumber != null)
				return false;
		} else if (!phoneNumber.equals(other.phoneNumber))
			return false;
		if (services == null) {
			if (other.services != null)
				return false;
		} else if (!services.equals(other.services))
			return false;
		if (transportPrice == null) {
			if (other.transportPrice != null)
				return false;
		} else if (!transportPrice.equals(other.transportPrice))
			return false;
		if (visible == null) {
			if (other.visible != null)
				return false;
		} else if (!visible.equals(other.visible))
			return false;
		if (zipCodes == null) {
			if (other.zipCodes != null)
				return false;
		} else if (!zipCodes.equals(other.zipCodes))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "RestaurantDto [id=" + id + ", name=" + name + ", nif=" + nif + ", email=" + email
				+ ", minPriceDelivery=" + minPriceDelivery + ", minPriceDeliveryTransport=" + minPriceDeliveryTransport
				+ ", transportPrice=" + transportPrice + ", phoneNumber=" + phoneNumber + ", visible=" + visible
				+ ", services=" + services + ", address=" + address + ", zipCodes=" + zipCodes + "]";
	}

}

package es.mabu.mr.user.client;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import es.mabu.mr.address.Address;
import es.mabu.mr.user.user.User;

@Entity
public class Client extends User {
	public Client() {
		super();
	}

	public Client(User user) {
		super(user);
	}

	private String name;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
	@JoinColumn(name = "addressid")
	private Address address;

	@Column(name = "stripeid")
	private String stripeId;

	@Column(name = "last4carddigits")
	private String last4CardDigits;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getStripeId() {
		return stripeId;
	}

	public void setStripeId(String stripeId) {
		this.stripeId = stripeId;
	}

	public String getLast4CardDigits() {
		return last4CardDigits;
	}

	public void setLast4CardDigits(String last4CardDigits) {
		this.last4CardDigits = last4CardDigits;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((last4CardDigits == null) ? 0 : last4CardDigits.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((stripeId == null) ? 0 : stripeId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Client other = (Client) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (last4CardDigits == null) {
			if (other.last4CardDigits != null)
				return false;
		} else if (!last4CardDigits.equals(other.last4CardDigits))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (stripeId == null) {
			if (other.stripeId != null)
				return false;
		} else if (!stripeId.equals(other.stripeId))
			return false;
		return true;
	}

}

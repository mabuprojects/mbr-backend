package es.mabu.mr.user.privilege;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Privilege {

	@Id
	@GenericGenerator(name = "privilege_sequence", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
			@org.hibernate.annotations.Parameter(name = "sequence", value = "privilege_id_seq")

	})
	@GeneratedValue(generator = "privilege_sequence")
	private Long id;

	private String name;

	public Privilege() {

	}

	public Privilege(String name) {
		super();
		this.name = name;
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

}
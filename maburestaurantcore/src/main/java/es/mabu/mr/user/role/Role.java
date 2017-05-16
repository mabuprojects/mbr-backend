package es.mabu.mr.user.role;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import org.hibernate.annotations.GenericGenerator;

import es.mabu.mr.user.privilege.Privilege;

/**
 * The Role class is an entity model object. A Role describes a privilege level
 * within the application. A Role is used to authorize an Account to access a
 * set of application resources.
 *
 * @author Matt Warman
 */
@Entity
public class Role {

	@Id
	@GenericGenerator(name = "role_sequence", strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator", parameters = {
			@org.hibernate.annotations.Parameter(name = "sequence", value = "role_id_seq")

	})
	@GeneratedValue(generator = "role_sequence")
	private Long id;

	private String name;

	@ManyToMany
	@JoinTable(name = "Role_Privilege", joinColumns = @JoinColumn(name = "role_Id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "privilege_Id", referencedColumnName = "id"))
	private Collection<Privilege> privileges;

	public Role() {

	}

	public Role(String name, Collection<Privilege> privileges) {
		super();
		this.name = name;
		this.privileges = privileges;
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

	public Collection<Privilege> getPrivileges() {
		return privileges;
	}

	public void setPrivileges(Collection<Privilege> privileges) {
		this.privileges = privileges;
	}

}

package es.mabu.mr.user.role;

import java.util.List;

public interface RoleService {

	Role findByName(String name);

	Role createNewRole(Role role);

	List<Role> getRoles(List<Long> ids);

	List<Role> getRoles();

}

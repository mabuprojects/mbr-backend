package es.mabu.mr.user.role;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	RoleRepository roleRepository;

	@Override
	public Role findByName(String name) {
		return roleRepository.findByName(name);
	}

	@Override
	public Role createNewRole(Role role) {

		return roleRepository.save(role);
	}

	@Override
	public List<Role> getRoles(List<Long> ids) {
		return roleRepository.findByIdIn(ids);
	}

	@Override
	public List<Role> getRoles() {
		return roleRepository.findAll();
	}

}

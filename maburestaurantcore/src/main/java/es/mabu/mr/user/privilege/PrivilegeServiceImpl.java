package es.mabu.mr.user.privilege;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PrivilegeServiceImpl implements PrivilegeService {

	@Autowired
	PrivilegeRepository privilegeRepository;

	@Override
	@Transactional(readOnly = true)
	public List<Privilege> getAllPrivileges() {
		return privilegeRepository.findAll();
	}

}

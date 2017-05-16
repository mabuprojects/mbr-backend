package es.mabu.mr.user.role;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 */
@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

	Role findByName(String name);

	List<Role> findByIdIn(List<Long> id);
}

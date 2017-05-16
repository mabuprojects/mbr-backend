package es.mabu.mr.user.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface UserBaseRepository<T extends User> extends JpaRepository<T, Long> {
	/**
	 * Query for a single Account entity by email.
	 *
	 * @param email
	 *            the email
	 * @return An Account or <code>null</code> if none found.
	 */
	T findByEmail(String email);

}

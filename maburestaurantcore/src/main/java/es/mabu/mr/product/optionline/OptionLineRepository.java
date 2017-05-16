package es.mabu.mr.product.optionline;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface OptionLineRepository  extends JpaRepository<OptionLine, Long>{

}

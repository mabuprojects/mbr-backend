package es.mabu.mr.product.optionlineprice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OptionLinePriceRepository extends JpaRepository<OptionLinePrice, Long> {

}

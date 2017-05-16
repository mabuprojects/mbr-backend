package es.mabu.mr.product.taxe;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface TaxeRepository extends JpaRepository <Taxe,Long> {

	Taxe findByName(@Param("name") String name);
}

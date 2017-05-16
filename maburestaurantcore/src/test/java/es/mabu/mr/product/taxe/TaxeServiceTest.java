package es.mabu.mr.product.taxe;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import es.mabu.mr.exception.exception.DuplicatedInstanceException;
import es.mabu.mr.exception.exception.InvalidValueException;
import es.mabu.mr.product.exception.TaxeHasProductsException;

@RunWith(SpringRunner.class)
@SpringBootTest
@Rollback
@Transactional
public class TaxeServiceTest {

	private static final String TAXE_NAME = "ALIMENTOS (21%)";
	private static final String TAXE_NAME2 = "BASICO (10%)";

	@Autowired
	TaxeService taxeService;

	@Test
	public void createTaxe1() throws DuplicatedInstanceException, InvalidValueException {

		Taxe taxe = taxeService.createTaxe(TAXE_NAME, 5);

		Taxe taxe2 = taxeService.findTaxeByName(TAXE_NAME);

		assertEquals(taxe, taxe2);
	}

	@Test(expected = DuplicatedInstanceException.class)
	public void createTaxe2() throws DuplicatedInstanceException, InvalidValueException {

		taxeService.createTaxe(TAXE_NAME, 5);

		taxeService.createTaxe(TAXE_NAME, 5);
	}

	@Test(expected = InvalidValueException.class)
	public void createTaxe3() throws InvalidValueException, DuplicatedInstanceException {

		taxeService.createTaxe(TAXE_NAME, 5);

		taxeService.createTaxe(TAXE_NAME2, -5);

	}

	@Test(expected = InvalidValueException.class)
	public void createTaxe4() throws InvalidValueException, DuplicatedInstanceException {

		taxeService.createTaxe(TAXE_NAME, 5);

		taxeService.createTaxe(TAXE_NAME2, 120);

	}

	@Test
	public void removeTaxe() throws InvalidValueException, DuplicatedInstanceException, TaxeHasProductsException {

		Taxe taxe = taxeService.createTaxe(TAXE_NAME, 5);
		taxeService.removeTaxe(taxe.getId());

		Taxe taxe2 = taxeService.findTaxeByName(TAXE_NAME);

		assertEquals(taxe2, null);

	}

	@Test
	public void updateTaxe() throws InvalidValueException, DuplicatedInstanceException {

		Taxe taxe = taxeService.createTaxe(TAXE_NAME, 5);
		taxe.setName(TAXE_NAME2);

		Taxe taxe2 = taxeService.updateTaxe(taxe);

		assertEquals(TAXE_NAME2, taxe2.getName());

	}

}

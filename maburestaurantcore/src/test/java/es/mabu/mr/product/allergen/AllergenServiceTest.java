package es.mabu.mr.product.allergen;

import static org.junit.Assert.assertEquals;

import javax.management.InstanceAlreadyExistsException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import es.mabu.mr.exception.exception.DuplicatedInstanceException;

@RunWith(SpringRunner.class)
@SpringBootTest
@Rollback
@Transactional
public class AllergenServiceTest {

	private static final String ALLERGEN_NAME = "GLUTEN";
	private static final String ALLERGEN_NAME2 = "HUEVOS";

	@Autowired
	AllergenService allergenService;

	@Test
	public void createAllergen() throws DuplicatedInstanceException {

		Allergen allergen = allergenService.createAllergen(ALLERGEN_NAME);
		Allergen allergen2 = allergenService.findAllerenByName(ALLERGEN_NAME);

		assertEquals(allergen, allergen2);
	}

	@Test(expected = DuplicatedInstanceException.class)
	public void createAllergen2() throws DuplicatedInstanceException, InstanceAlreadyExistsException {

		allergenService.createAllergen(ALLERGEN_NAME);
		allergenService.createAllergen(ALLERGEN_NAME);
	}

	@Test
	public void removeAllergen() throws DuplicatedInstanceException {

		Allergen allergen = allergenService.createAllergen(ALLERGEN_NAME);
		allergenService.removeAllergen(allergen.getId());

		Allergen allergen2 = allergenService.findAllerenByName(ALLERGEN_NAME);

		assertEquals(allergen2, null);
	}

	@Test
	public void updateAllergen() throws DuplicatedInstanceException {

		Allergen allergen = allergenService.createAllergen(ALLERGEN_NAME);
		allergen.setName(ALLERGEN_NAME2);

		assertEquals(allergen, allergenService.findAllerenByName(ALLERGEN_NAME2));

	}
}

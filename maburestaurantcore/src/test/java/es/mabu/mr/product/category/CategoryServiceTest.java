package es.mabu.mr.product.category;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import es.mabu.mr.exception.exception.DuplicatedInstanceException;
import es.mabu.mr.product.exception.CategoryHasProductsException;

@RunWith(SpringRunner.class)
@SpringBootTest
@Rollback
@Transactional
public class CategoryServiceTest {

	@Autowired
	CategoryService categoryService;

	private final static String CATEGORY_NAME = "Pizzas";
	private final static String CATEGORY_NAME2 = "Bocatas";

	@Test
	public void createCategory1() throws DuplicatedInstanceException {

		Category category = categoryService.createCategory(CATEGORY_NAME);
		Category category2 = categoryService.findCategoryByName(CATEGORY_NAME);

		assertEquals(category, category2);
	}

	@Test(expected = DuplicatedInstanceException.class)
	public void createCategory2() throws DuplicatedInstanceException {

		categoryService.createCategory(CATEGORY_NAME);
		categoryService.createCategory(CATEGORY_NAME);
	}

	@Test
	public void removeCategory() throws DuplicatedInstanceException, CategoryHasProductsException {

		Category category = categoryService.createCategory(CATEGORY_NAME);
		categoryService.removeCategory(category.getId());

		Category category2 = categoryService.findCategoryByName(CATEGORY_NAME);

		assertEquals(category2, null);

	}

	@Test
	public void updateCategory() throws DuplicatedInstanceException {

		Category category = categoryService.createCategory(CATEGORY_NAME);
		category.setName(CATEGORY_NAME2);

		assertEquals(category, categoryService.findCategoryByName(CATEGORY_NAME2));

	}

}

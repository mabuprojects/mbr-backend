package es.mabu.mr.product.category;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import es.mabu.mr.exception.exception.DuplicatedInstanceException;
import es.mabu.mr.product.exception.CategoryHasProductsException;

@RestController
@RequestMapping("/public/category")
public class CategoryController {

	@Autowired
	CategoryService categoryService;

	@RequestMapping(method = RequestMethod.GET)
	List<Category> getCategories() {
		return categoryService.findAllCategories();
	}

	@RequestMapping(method = RequestMethod.POST, produces = "application/json")
	Category createCategory(@RequestBody String categoryName) throws DuplicatedInstanceException {
		return categoryService.createCategory(categoryName);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{categoryId}")
	Boolean deleteCategory(@PathVariable Long categoryId) throws CategoryHasProductsException {
		categoryService.removeCategory(categoryId);
		return true;
	}

	@RequestMapping(method = RequestMethod.PATCH, produces = "application/json")
	Category updateCategory(@RequestBody Category category) throws DuplicatedInstanceException {
		return categoryService.updateCategory(category);
	}

}

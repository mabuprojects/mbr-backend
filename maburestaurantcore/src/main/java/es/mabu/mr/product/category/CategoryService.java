package es.mabu.mr.product.category;

import java.util.List;

import es.mabu.mr.exception.exception.DuplicatedInstanceException;
import es.mabu.mr.product.exception.CategoryHasProductsException;

public interface CategoryService {
	public List<Category> findAllCategories();

	public Category findCategoryByName(String name);

	public Category findCategoryById(Long id);

	public Category createCategory(String name) throws DuplicatedInstanceException;

	public void removeCategory(Long cateoryId) throws CategoryHasProductsException;

	public Category updateCategory(Category category) throws DuplicatedInstanceException;

}

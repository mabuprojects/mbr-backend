package es.mabu.mr.product.category;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.mabu.mr.exception.exception.DuplicatedInstanceException;
import es.mabu.mr.product.Product;
import es.mabu.mr.product.ProductRepository;
import es.mabu.mr.product.exception.CategoryHasProductsException;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	CategoryRepository categoryRepository;

	@Autowired
	ProductRepository productRepository;

	@Override
	@Transactional(readOnly = true)
	public List<Category> findAllCategories() {
		return categoryRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Category findCategoryById(Long id) {
		return categoryRepository.findOne(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Category findCategoryByName(String name) {
		return categoryRepository.findByName(name);
	}

	@Override
	@Transactional
	public Category createCategory(String name) throws DuplicatedInstanceException {
		if (categoryRepository.findByName(name) != null) {
			throw new DuplicatedInstanceException("There is a category with name '" + name + "'");
		}

		return categoryRepository.save(new Category(name));

	}

	@Override
	@Transactional
	public void removeCategory(Long categoryId) throws CategoryHasProductsException {
		List<Product> products = productRepository.findByCategoryId(categoryId);
		if (!products.isEmpty()) {
			throw new CategoryHasProductsException("This category has products");
		}
		categoryRepository.delete(categoryRepository.findOne(categoryId));

	}

	@Override
	@Transactional
	public Category updateCategory(Category category) throws DuplicatedInstanceException {
		// There is not category with this name
		Category c = categoryRepository.findByName(category.getName());
		if (c != null) {
			throw new DuplicatedInstanceException("There is a category with name '" + category.getName() + "'");
		}
		return categoryRepository.save(category);

	}
}

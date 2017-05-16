package es.mabu.mr.product;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import es.mabu.mr.exception.exception.DuplicatedInstanceException;
import es.mabu.mr.file.StorageException;
import es.mabu.mr.file.image.NotAnImageException;
import es.mabu.mr.product.exception.DuplicatedProductDetailsInProductException;
import es.mabu.mr.product.exception.IncorrectProductOptionsException;

public interface ProductService {

	public List<Product> findAllProducts();

	public Product findProductById(Long productId);

	public Product findProductByName(String name);

	public Product createProduct(Product product) throws DuplicatedInstanceException,
			DuplicatedProductDetailsInProductException, IncorrectProductOptionsException;

	public Product updateProduct(Product product) throws DuplicatedInstanceException,
			DuplicatedProductDetailsInProductException, IncorrectProductOptionsException;

	public void removeProduct(Long productId);

	public void updateImage(Long productId, MultipartFile file) throws StorageException, NotAnImageException;

}

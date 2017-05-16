package es.mabu.mr.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import es.mabu.mr.exception.exception.DuplicatedInstanceException;
import es.mabu.mr.file.StorageException;
import es.mabu.mr.file.image.NotAnImageException;
import es.mabu.mr.product.exception.DuplicatedProductDetailsInProductException;
import es.mabu.mr.product.exception.IncorrectProductOptionsException;
import es.mabu.mr.utils.ProductConversor;

@RestController
@RequestMapping("/public/product")
public class ProductController {

	@Autowired
	ProductService productService;

	@Autowired
	ProductConversor productConversor;

	@RequestMapping(method = RequestMethod.GET)
	List<ProductDto> getProducts() {
		return productConversor.productToProductDto(productService.findAllProducts());
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{productId}")
	ProductDto getProductById(@PathVariable Long productId) {
		return productConversor.productToProductDto(productService.findProductById(productId));
	}

	@RequestMapping(method = RequestMethod.POST, produces = "application/json")
	ProductDto createProduct(@RequestBody ProductDto productDto) throws DuplicatedInstanceException,
			DuplicatedProductDetailsInProductException, IncorrectProductOptionsException {
		return productConversor
				.productToProductDto(productService.createProduct(productConversor.productDtoToProduct(productDto)));
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/{productId}")
	void deleteProduct(@PathVariable Long productId) {
		productService.removeProduct(productId);
	}

	@RequestMapping(method = RequestMethod.POST, value = "/{productId}/image")
	void updateProductImage(@PathVariable Long productId, @RequestBody MultipartFile file)
			throws StorageException, NotAnImageException {
		productService.updateImage(productId, file);
	}

}

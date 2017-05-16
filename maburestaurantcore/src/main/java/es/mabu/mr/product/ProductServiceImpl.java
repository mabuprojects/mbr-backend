package es.mabu.mr.product;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import es.mabu.mr.exception.exception.DuplicatedInstanceException;
import es.mabu.mr.file.StorageException;
import es.mabu.mr.file.image.ImageService;
import es.mabu.mr.file.image.NotAnImageException;
import es.mabu.mr.product.category.CategoryRepository;
import es.mabu.mr.product.exception.DuplicatedProductDetailsInProductException;
import es.mabu.mr.product.exception.IncorrectProductOptionsException;
import es.mabu.mr.product.option.Option;
import es.mabu.mr.product.option.OptionRepository;
import es.mabu.mr.product.optionline.OptionLine;
import es.mabu.mr.product.optionline.OptionLineRepository;
import es.mabu.mr.product.optionlineprice.OptionLinePrice;
import es.mabu.mr.product.optionlineprice.OptionLinePriceRepository;
import es.mabu.mr.product.productDetails.ProductDetails;
import es.mabu.mr.product.productDetails.ProductDetailsRepository;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	CategoryRepository categoryRepository;

	@Autowired
	ProductRepository productRepository;

	@Autowired
	OptionLineRepository optionLineRepository;

	@Autowired
	OptionLinePriceRepository optionLinePriceRepository;

	@Autowired
	OptionRepository optionRepository;

	@Autowired
	ProductDetailsRepository productDetailRepository;

	@Autowired
	ImageService imageServie;

	@Override
	@Transactional(readOnly = true)
	public List<Product> findAllProducts() {
		return productRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Product findProductById(Long productId) {
		return productRepository.findOne(productId);
	}

	@Override
	@Transactional
	public Product createProduct(Product product) throws DuplicatedInstanceException,
			DuplicatedProductDetailsInProductException, IncorrectProductOptionsException {

		validateProductForCreateProduct(product);

		Timestamp currentTime = new Timestamp(new Date().getTime());

		Product savedProduct = productRepository.save(new Product(product.getName(), product.getDescription(),
				product.getCategory(), product.getTaxe(), currentTime));

		savedProduct.setProductDetails(product.getProductDetails().stream()
				.map(pd -> productDetailRepository.save(new ProductDetails(savedProduct, pd.getRestaurant(),
						pd.getState(), pd.getMainPage(), pd.getPrice(), currentTime, currentTime)))
				.collect(Collectors.toList()));

		savedProduct.setOptions(saveOptions(savedProduct, product.getOptions()));

		return productRepository.save(savedProduct);
	}

	@Override
	@Transactional
	public Product updateProduct(Product updateProduct) throws DuplicatedInstanceException,
			DuplicatedProductDetailsInProductException, IncorrectProductOptionsException {

		Product oldProduct = productRepository.findOne(updateProduct.getId());
		validateProductForUpdateProduct(oldProduct, updateProduct);

		oldProduct = updateProductDetails(oldProduct, updateProduct);

		oldProduct = updateOptions(oldProduct, updateProduct);

		oldProduct.setName(updateProduct.getName());
		oldProduct.setDescription(updateProduct.getDescription());
		oldProduct.setCategory(updateProduct.getCategory());
		oldProduct.setTaxe(updateProduct.getTaxe());
		return productRepository.save(oldProduct);
	}

	/**
	 * Función para actualizar las opciones de producto
	 * 
	 * @param oldProduct
	 * @param updateProduct
	 */
	private Product updateOptions(Product oldProduct, Product updateProduct) {

		// Elimino las options que no estén en el nuevo producto
		List<Option> oldOptions = new ArrayList<>(oldProduct.getOptions());
		oldOptions.forEach(o -> {
			if (!updateProduct.getOptions().contains(o)) {
				oldProduct.removeOption(o);
				removeOption(o);
			}
		});

		// Actualizo las options que estén cambiados en el nuevo producto
		updateProduct.getOptions().forEach(updateOption -> {
			if (oldProduct.getOptions().contains(updateOption)) {
				Option oldOption = optionRepository.findOne(updateOption.getId());
				if (!oldOption.equals(updateOption)) {

					updateOption(oldOption, updateOption);
				}
			}
		});

		// Creo las nuevas options que estén en el nuevo producto
		updateProduct.getOptions().forEach(option -> {
			if (!oldProduct.getOptions().contains(option)) {
				oldProduct.addOption(createOption(option, oldProduct));
			}
		});
		return oldProduct;

	}

	private void removeOption(Option option) {
		List<OptionLine> optionLines = new ArrayList<>(option.getOptionLines());

		optionLines.forEach(optionLine -> {
			option.removeOptionLine(optionLine);
			removeOptionLine(optionLine);
		});

		optionRepository.delete(option.getId());

	}

	private void removeOptionLine(OptionLine optionLine) {

		List<OptionLinePrice> optionLinePrices = new ArrayList<>(optionLine.getOptionLinePrices());

		optionLinePrices.forEach(optionLinePrice -> {
			optionLine.removeOptionLinePrice(optionLinePrice);
			optionLinePriceRepository.delete(optionLinePrice.getId());

		});
		optionLineRepository.delete(optionLine.getId());
	}

	private Option createOption(Option option, Product product) {
		Option savedOption = optionRepository.save(new Option(option.getName(), product, option.isMain()));
		savedOption.setOptionLines(saveOptionLines(savedOption, option.getOptionLines()));
		return optionRepository.save(savedOption);

	}

	private void updateOption(Option oldOption, Option updateOption) {

		oldOption.setName(updateOption.getName());

		// Elimino las optionLines que no estén en el nuevo producto
		List<OptionLine> oldOptionLines = new ArrayList<>(oldOption.getOptionLines());
		oldOptionLines.forEach(optionLine -> {
			if (!updateOption.getOptionLines().contains(optionLine)) {
				removeOptionLine(optionLine);
			}
		});

		// Actualizo las optionLines que estén cambiados en el nuevo producto
		updateOption.getOptionLines().forEach(updateOptionLine -> {
			if (oldOption.getOptionLines().contains(updateOptionLine)) {
				OptionLine oldOptionLine = optionLineRepository.findOne(updateOptionLine.getId());
				if (!oldOptionLine.equals(updateOptionLine)) {
					updateOptionLine(oldOptionLine, updateOptionLine);
				}
			}
		});

		// Creo las nuevas optionLines que estén en el nuevo producto
		updateOption.getOptionLines().forEach(updateOptionLine -> {
			if (!oldOption.getOptionLines().contains(updateOptionLine)) {
				oldOption.addOptionLine(saveOptionLine(updateOptionLine, oldOption));
			}
		});

		optionRepository.save(oldOption);

	}

	private void updateOptionLine(OptionLine oldOptionLine, OptionLine updateOptionLine) {
		oldOptionLine.setName(updateOptionLine.getName());

		// Elimino los optionLinePrices que no estén en el nuevo producto
		List<OptionLinePrice> oldOptionLinePrices = new ArrayList<>(oldOptionLine.getOptionLinePrices());
		oldOptionLinePrices.forEach(optionLinePrice -> {
			if (!updateOptionLine.getOptionLinePrices().contains(optionLinePrice)) {
				oldOptionLine.removeOptionLinePrice(optionLinePrice);
				optionLinePriceRepository.delete(optionLinePrice.getId());
			}
		});

		// Actualizo los optionLinePrices que estén cambiados en el nuevo
		// producto
		updateOptionLine.getOptionLinePrices().forEach(updateOptionLinePrice -> {
			if (oldOptionLine.getOptionLinePrices().contains(updateOptionLinePrice)) {

				OptionLinePrice oldOptionLinePrice = optionLinePriceRepository.findOne(updateOptionLinePrice.getId());
				if (!oldOptionLinePrice.equals(updateOptionLinePrice)) {

					oldOptionLinePrice.setRestaurant(updateOptionLinePrice.getRestaurant());
					oldOptionLinePrice.setPriceAdded(updateOptionLinePrice.getPriceAdded());
					optionLinePriceRepository.save(oldOptionLinePrice);
				}
			}
		});

		// Creo los nuevas optionLinePrices que estén en el nuevo producto
		updateOptionLine.getOptionLinePrices().forEach(updateOptionLinePrice -> {
			if (!oldOptionLine.getOptionLinePrices().contains(updateOptionLinePrice)) {
				oldOptionLine.addOptionLinePrice(saveOptionLinePrice(updateOptionLinePrice, oldOptionLine));
			}
		});

		optionLineRepository.save(oldOptionLine);
	}

	/**
	 * Funcion para actualizar los detalles de producto
	 * 
	 * @param oldProduct
	 * @param updateProduct
	 */
	private Product updateProductDetails(Product oldProduct, Product updateProduct) {

		Timestamp currentTime = new Timestamp(new Date().getTime());

		// Elimino los productDetails que no estén en el nuevo producto
		List<ProductDetails> oldProductDetails = new ArrayList<>(oldProduct.getProductDetails());
		oldProductDetails.forEach(pd -> {
			if (!updateProduct.getProductDetails().contains(pd)) {
				oldProduct.removeProductDetail(pd);
				productDetailRepository.delete(pd.getId());
			}
		});

		// Actualizo los productDetails que estén cambiados en el nuevo producto
		updateProduct.getProductDetails().forEach(pd -> {
			if (oldProduct.getProductDetails().contains(pd)) {
				ProductDetails oldProductDetail = productDetailRepository.findOne(pd.getId());
				if (!oldProductDetail.equals(pd)) {
					oldProductDetail.setModified(currentTime);
					oldProductDetail.setMainPage(pd.getMainPage());
					oldProductDetail.setPrice(pd.getPrice());
					oldProductDetail.setRestaurant(pd.getRestaurant());
					oldProductDetail.setState(pd.getState());
					productDetailRepository.save(oldProductDetail);
				}
			}
		});

		// Creo los nuevos productDetails que estén en el nuevo producto
		updateProduct.getProductDetails().forEach(pd -> {
			if (!oldProduct.getProductDetails().contains(pd)) {
				oldProduct.addProductDetail(productDetailRepository.save(new ProductDetails(oldProduct,
						pd.getRestaurant(), pd.getState(), pd.getMainPage(), pd.getPrice(), currentTime, currentTime)));
			}
		});

		return oldProduct;

	}

	/**
	 * Validate Product para funcion actualizar producto
	 * 
	 * @param product
	 * @throws DuplicatedInstanceException
	 * @throws DuplicatedProductDetailsInProductException
	 * @throws IncorrectProductOptionsException
	 */
	private void validateProductForUpdateProduct(Product oldProduct, Product updateProduct)
			throws DuplicatedInstanceException, DuplicatedProductDetailsInProductException,
			IncorrectProductOptionsException {

		/*
		 * Compruebo si existe algun producto con ese nombre en caso de que el
		 * producto haya cambiado el nombre
		 */
		if (!updateProduct.getName().equals(oldProduct.getName())) {
			if (productRepository.findByName(updateProduct.getName()) != null) {
				throw new DuplicatedInstanceException("There is a product with name '" + updateProduct.getName() + "'");
			}
		}

		validateProduct(updateProduct);

	}

	/**
	 * Validate Product para funcion crear producto
	 * 
	 * @param product
	 * @throws DuplicatedInstanceException
	 * @throws DuplicatedProductDetailsInProductException
	 * @throws IncorrectProductOptionsException
	 */
	private void validateProductForCreateProduct(Product product) throws DuplicatedInstanceException,
			DuplicatedProductDetailsInProductException, IncorrectProductOptionsException {

		/* Compruebo si existe algun producto con ese nombre */
		if (productRepository.findByName(product.getName()) != null) {
			throw new DuplicatedInstanceException("There is a product with name '" + product.getName() + "'");
		}

		validateProduct(product);
	}

	private void validateProduct(Product product) throws DuplicatedInstanceException,
			DuplicatedProductDetailsInProductException, IncorrectProductOptionsException {

		/*
		 * Compruebo si no hay dos o mas detalles de producto con el mismo
		 * restaurante
		 */
		if (areDuplicates(product.getProductDetails(), new DuplicateGetter<ProductDetails>() {

			@Override
			public Long getKey(ProductDetails item) {
				return item.getRestaurant().getId();
			}

		})) {
			throw new DuplicatedProductDetailsInProductException(
					"There are two products details for the same restaurant");
		}

		/*
		 * Compruebo que cada opción tenga por lo menos dos lineas de opción.Y
		 * que cada opcion tenga una optionlineprice por cada uno de los
		 * restaurantes.
		 * 
		 * Compruebo que como máximo solo haya una opción con la propiedad main
		 * a true. Sólo puede haber una opción principal
		 */
		if (IsIncorrectProductOptions(product.getOptions(), product.getProductDetails().size())) {
			throw new IncorrectProductOptionsException("Options are incorrect");
		}

	}

	private List<Option> saveOptions(Product savedProduct, List<Option> options) {

		// @formatter:off
		return options.stream()
				.map(option -> {
								Option savedOption = optionRepository.save(new Option(option.getName(), savedProduct, option.isMain()));
								savedOption.setOptionLines(saveOptionLines(savedOption, option.getOptionLines()));
								return optionRepository.save(savedOption);
				})
				.collect(Collectors.toList());
		// @formatter:on
	}

	private List<OptionLine> saveOptionLines(Option savedOption, List<OptionLine> optionLines) {

		List<OptionLine> saveOptionLines = new ArrayList<>(optionLines.size());

		for (OptionLine optionLine : optionLines) {
			saveOptionLines.add(saveOptionLine(optionLine, savedOption));
		}
		return saveOptionLines;
	}

	private OptionLine saveOptionLine(OptionLine optionLine, Option savedOption) {

		OptionLine savedOptionLine = optionLineRepository.save(new OptionLine(optionLine.getName(), savedOption));

		List<OptionLinePrice> optionLinePrices = new ArrayList<>(optionLine.getOptionLinePrices().size());

		for (OptionLinePrice optionLinePrice : optionLine.getOptionLinePrices()) {

			optionLinePrices.add(saveOptionLinePrice(optionLinePrice, savedOptionLine));
		}
		savedOptionLine.setOptionLinePrices(optionLinePrices);

		return optionLineRepository.save(savedOptionLine);
	}

	private OptionLinePrice saveOptionLinePrice(OptionLinePrice optionLinePrice, OptionLine savedOptionLine) {
		return optionLinePriceRepository.save(
				new OptionLinePrice(optionLinePrice.getPriceAdded(), savedOptionLine, optionLinePrice.getRestaurant()));
	}

	private static <T> boolean areDuplicates(List<T> list, DuplicateGetter<T> getter) {

		if (list == null) {
			return false;
		}
		Set<Long> keys = new HashSet<>();

		for (T item : list) {
			Long key = getter.getKey(item);
			if (key == null) {
				return true;
			}
			if (keys.contains(key)) {
				return true;
			}
			keys.add(key);
		}
		return false;

	}

	private interface DuplicateGetter<T> {
		Long getKey(T item);
	}

	private boolean IsIncorrectProductOptions(List<Option> options, int numberOfRestaurants) {
		/*
		 * Compruebo que como máximo solo haya una opción con la propiedad main
		 * a true. Sólo puede haber una opción principal
		 */
		int countMainOptions = 0;

		for (Option option : options) {
			/* Cada opcion debe tener como mínimo dos lineas de opciones */
			if (option.getOptionLines().size() < 2) {
				return true;
			}

			if (option.isMain()) {
				countMainOptions++;
			}
			for (OptionLine ol : option.getOptionLines()) {
				/*
				 * Debe haber por lo menos una optionline price por cada resta
				 */
				if (ol.getOptionLinePrices().size() != numberOfRestaurants) {
					return true;
				}
				/*
				 * Comprobar que cada optionlineprice tiene un restaurante
				 * diferente
				 */

				if (areDuplicates(ol.getOptionLinePrices(), new DuplicateGetter<OptionLinePrice>() {

					@Override
					public Long getKey(OptionLinePrice item) {
						return item.getRestaurant().getId();
					}

				})) {
					return true;

				}
			}

		}
		if (countMainOptions > 1) {
			return true;
		}
		return false;
	}

	@Override
	@Transactional
	public void removeProduct(Long productId) {
		productRepository.delete(productId);

	}

	@Override
	public Product findProductByName(String name) {
		return productRepository.findByName(name);
	}

	@Override
	public void updateImage(Long productId, MultipartFile file) throws StorageException, NotAnImageException {
		Product product = productRepository.findOne(productId);
		if (product == null) {
			return;
		}
		String[] fileFrags = file.getOriginalFilename().split("\\.");
		String extension = fileFrags[fileFrags.length - 1];
		String imageName = productId.toString() + "." + extension;
		imageServie.update(file, "product", imageName);
		product.setImageName(imageName);

	}

}

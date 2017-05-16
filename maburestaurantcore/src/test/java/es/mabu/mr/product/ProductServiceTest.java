package es.mabu.mr.product;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import es.mabu.mr.address.Address;
import es.mabu.mr.exception.exception.DuplicatedInstanceException;
import es.mabu.mr.exception.exception.InvalidValueException;
import es.mabu.mr.exception.exception.NotValidMinPriceDeliveryException;
import es.mabu.mr.product.category.Category;
import es.mabu.mr.product.category.CategoryService;
import es.mabu.mr.product.exception.DuplicatedProductDetailsInProductException;
import es.mabu.mr.product.exception.IncorrectProductOptionsException;
import es.mabu.mr.product.option.Option;
import es.mabu.mr.product.optionline.OptionLine;
import es.mabu.mr.product.optionlineprice.OptionLinePrice;
import es.mabu.mr.product.productDetails.ProductDetails;
import es.mabu.mr.product.taxe.Taxe;
import es.mabu.mr.product.taxe.TaxeService;
import es.mabu.mr.restaurant.Restaurant;
import es.mabu.mr.restaurant.RestaurantService;
import es.mabu.mr.restaurant.RestaurantServiceType;
import es.mabu.mr.restaurant.timetable.TimeTableErrorException;
import es.mabu.mr.restaurant.timetable.TimeTableUtil;

@RunWith(SpringRunner.class)
@SpringBootTest
@Rollback
@Transactional
public class ProductServiceTest {

	@Autowired
	ProductService productService;

	@Autowired
	RestaurantService restaurantService;

	@Autowired
	CategoryService categoryService;

	@Autowired
	TaxeService taxeService;

	private static final String PHONE_NUMBER = "637815611";
	private final static String CATEGORY_NAME = "Pizzas";
	private final static String OPTION_NAME = "Tamaño";
	private final static String OPTIONLINE_NAME = "Mediana";
	private final static String OPTIONLINE_NAME2 = "Pequeña";
	private final static String PRODUCT_NAME = "Hawaiana";
	private final static String PRODUCT_DESCRIPTION = "Esta es la mejor pizza del mundo";
	private final static String TAXE_NAME = "Básico (21%)";
	private static final Address address = new Address("ESPAÑA", "GALICIA", "A CORUÑA", "AVDA MONELOS", "Nº19-23", "4A",
			"15009", "");
	private static final List<Integer> zipCodes = new LinkedList<>(Arrays.asList(1));
	private static final Set<RestaurantServiceType> service = new HashSet<>(
			Arrays.asList(RestaurantServiceType.DELIVERY));

	Category category;
	Taxe taxe;
	Restaurant restaurant;
	Restaurant restaurant2;

	@Before
	public void Init() throws DuplicatedInstanceException, InvalidValueException, NotValidMinPriceDeliveryException,
			TimeTableErrorException {
		category = categoryService.createCategory(CATEGORY_NAME);
		taxe = taxeService.createTaxe(TAXE_NAME, 21);
		restaurant = restaurantService.createRestaurant(new Restaurant("Galipizza Coruña", "B27979797",
				"email1@gmail.com", new BigDecimal(5), new BigDecimal(5), PHONE_NUMBER, true, service, address,
				zipCodes, TimeTableUtil.getTimeTable()));
		restaurant2 = restaurantService.createRestaurant(new Restaurant("Galipizza Coruña2", "B27979797",
				"email2@gmail.com", new BigDecimal(5), new BigDecimal(5), PHONE_NUMBER, true, service, address,
				zipCodes, TimeTableUtil.getTimeTable()));
	}

	@Test(expected = DuplicatedProductDetailsInProductException.class)
	public void createProduct() throws DuplicatedInstanceException, DuplicatedProductDetailsInProductException,
			IncorrectProductOptionsException {

		Product p = new Product(PRODUCT_NAME, PRODUCT_DESCRIPTION, category, taxe);

		p.addProductDetail(new ProductDetails(p, restaurant, ProductState.HIDDEN, 0, new BigDecimal(5)));
		p.addProductDetail(new ProductDetails(p, restaurant, ProductState.HIDDEN, 0, new BigDecimal(5)));

		List<OptionLinePrice> olps = new ArrayList<>();

		olps.add(new OptionLinePrice(new BigDecimal(5), null, restaurant));

		List<OptionLine> ols = new ArrayList<>();
		ols.add(new OptionLine(OPTIONLINE_NAME, null, olps));

		List<Option> os = new ArrayList<>();

		os.add(new Option(OPTION_NAME, ols, p, true));

		p.setOptions(os);

		productService.createProduct(p);
	}

	@Test(expected = IncorrectProductOptionsException.class)
	public void createProduct2() throws DuplicatedInstanceException, DuplicatedProductDetailsInProductException,
			IncorrectProductOptionsException {

		Product p = new Product(PRODUCT_NAME, PRODUCT_DESCRIPTION, category, taxe);
		p.addProductDetail(new ProductDetails(p, restaurant, ProductState.HIDDEN, 0, new BigDecimal(5)));
		p.addProductDetail(new ProductDetails(p, restaurant2, ProductState.HIDDEN, 0, new BigDecimal(5)));

		List<OptionLinePrice> olps = new ArrayList<>();

		olps.add(new OptionLinePrice(new BigDecimal(5), null, restaurant));

		List<OptionLine> ols = new ArrayList<>();
		ols.add(new OptionLine(OPTIONLINE_NAME, null, olps));

		List<Option> os = new ArrayList<>();

		os.add(new Option(OPTION_NAME, ols, p, true));

		p.setOptions(os);

		productService.createProduct(p);
	}

	@Test(expected = IncorrectProductOptionsException.class)
	public void createProduct3() throws DuplicatedInstanceException, DuplicatedProductDetailsInProductException,
			IncorrectProductOptionsException {

		Product p = new Product(PRODUCT_NAME, PRODUCT_DESCRIPTION, category, taxe);

		p.addProductDetail(new ProductDetails(p, restaurant, ProductState.HIDDEN, 0, new BigDecimal(5)));
		p.addProductDetail(new ProductDetails(p, restaurant2, ProductState.HIDDEN, 0, new BigDecimal(5)));

		List<OptionLinePrice> olps = new ArrayList<>();

		olps.add(new OptionLinePrice(new BigDecimal(5), null, restaurant));

		List<OptionLine> ols = new ArrayList<>();
		ols.add(new OptionLine(OPTIONLINE_NAME, null, olps));
		ols.add(new OptionLine(OPTIONLINE_NAME2, null, olps));

		List<Option> os = new ArrayList<>();
		os.add(new Option(OPTION_NAME, ols, p, true));

		p.setOptions(os);

		productService.createProduct(p);
	}

	@Test
	public void createProduct5() throws DuplicatedInstanceException, DuplicatedProductDetailsInProductException,
			IncorrectProductOptionsException {

		Product p = new Product(PRODUCT_NAME, PRODUCT_DESCRIPTION, category, taxe);
		p.addProductDetail(new ProductDetails(p, restaurant, ProductState.HIDDEN, 0, new BigDecimal(5)));
		p.addProductDetail(new ProductDetails(p, restaurant2, ProductState.HIDDEN, 0, new BigDecimal(5)));

		List<OptionLinePrice> olps = new ArrayList<>();

		olps.add(new OptionLinePrice(new BigDecimal(5), null, restaurant));
		olps.add(new OptionLinePrice(new BigDecimal(5), null, restaurant2));

		List<OptionLine> ols = new ArrayList<>();
		ols.add(new OptionLine(OPTIONLINE_NAME, null, olps));
		ols.add(new OptionLine(OPTIONLINE_NAME2, null, olps));

		List<Option> os = new ArrayList<>();

		os.add(new Option(OPTION_NAME, ols, p, true));

		p.setOptions(os);

		Product p1 = productService.createProduct(p);

		Product p2 = productService.findProductById(p1.getId());

		assertEquals(p1, p2);

		assertEquals(p2.getOptions().get(0).getOptionLines().get(0).getOptionLinePrices().get(0).getPriceAdded(),
				new BigDecimal(5));
	}

	@Test
	public void removeProduct() throws DuplicatedInstanceException, DuplicatedProductDetailsInProductException,
			IncorrectProductOptionsException {

		Product p = new Product(PRODUCT_NAME, PRODUCT_DESCRIPTION, category, taxe);

		Product p1 = productService.createProduct(p);

		productService.removeProduct(p1.getId());

		Product p2 = productService.findProductByName(PRODUCT_NAME);

		assertEquals(null, p2);

	}

	@Test
	public void updateProduct() throws DuplicatedInstanceException, DuplicatedProductDetailsInProductException,
			IncorrectProductOptionsException {

		Product p = new Product(PRODUCT_NAME, PRODUCT_DESCRIPTION, category, taxe);
		ProductDetails pd = new ProductDetails(p, restaurant, ProductState.HIDDEN, 0, new BigDecimal(5));
		p.addProductDetail(pd);
		ProductDetails pd2 = new ProductDetails(p, restaurant2, ProductState.HIDDEN, 0, new BigDecimal(5));
		p.addProductDetail(pd2);
		Product p1 = productService.createProduct(p);

		p.setId(p1.getId());
		p.setProductDetails(new ArrayList<>());

		Product productUpdate = productService.updateProduct(p);

		assertEquals(0, productUpdate.getProductDetails().size());

	}
	//
	// @Test
	// public void updateProduct2() throws DuplicatedInstanceException,
	// DuplicatedProductDetailsInProductException,
	// IncorrectProductOptionsException {
	//
	// Product p = new Product(PRODUCT_NAME, PRODUCT_DESCRIPTION, category,
	// taxe);
	// ProductDetails pd = new ProductDetails(p, restaurant,
	// ProductState.HIDDEN, 0, new BigDecimal(5));
	// p.addProductDetail(pd);
	//
	// Product p1 = productService.createProduct(p);
	//
	// p.setId(p1.getId());
	// p.setProductDetails(p1.getProductDetails());
	// p.addProductDetail(new ProductDetails(p, restaurant2,
	// ProductState.HIDDEN, 0, new BigDecimal(5)));
	//
	// Product productUpdate = productService.updateProduct(p);
	//
	// assertEquals(2, productUpdate.getProductDetails().size());
	//
	// }

	@Test
	public void updateProduct3() throws DuplicatedInstanceException, DuplicatedProductDetailsInProductException,
			IncorrectProductOptionsException {

		Product p = new Product(PRODUCT_NAME, PRODUCT_DESCRIPTION, category, taxe);
		ProductDetails pd = new ProductDetails(p, restaurant, ProductState.HIDDEN, 0, new BigDecimal(5));
		p.addProductDetail(pd);

		Product p1 = productService.createProduct(p);

		p.setId(p1.getId());
		p.setProductDetails(p1.getProductDetails());
		p.getProductDetails().get(0).setState(ProductState.VISIBLE);

		Product productUpdate = productService.updateProduct(p);

		assertEquals(ProductState.VISIBLE, productUpdate.getProductDetails().get(0).getState());

	}

}

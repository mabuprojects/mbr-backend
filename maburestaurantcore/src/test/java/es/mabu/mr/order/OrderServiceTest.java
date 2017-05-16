package es.mabu.mr.order;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
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
import es.mabu.mr.order.dto.request.OrderLinePriceRequestDto;
import es.mabu.mr.order.dto.request.OrderLineRequestDto;
import es.mabu.mr.order.dto.request.OrderRequestDto;
import es.mabu.mr.order.exception.CashOnDeliveryNotAvaliable;
import es.mabu.mr.order.exception.OrderHasNoProductsException;
import es.mabu.mr.order.exception.OutsideAddressRestaurantException;
import es.mabu.mr.order.exception.ProductNotAvaliable;
import es.mabu.mr.order.exception.RestaurantHasNotThisService;
import es.mabu.mr.order.exception.RestaurantNotAvaliableException;
import es.mabu.mr.product.Product;
import es.mabu.mr.product.ProductService;
import es.mabu.mr.product.ProductState;
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
import es.mabu.mr.user.client.Client;
import es.mabu.mr.user.client.ClientService;
import es.mabu.mr.user.exception.InvalidUserException;
import es.mabu.mr.user.privilege.Privilege;
import es.mabu.mr.user.privilege.PrivilegeRepository;
import es.mabu.mr.user.role.Role;
import es.mabu.mr.user.role.RoleService;

@RunWith(SpringRunner.class)
@SpringBootTest
@Rollback
@Transactional
public class OrderServiceTest {

	@Autowired
	OrderService orderService;

	@Autowired
	ProductService productService;

	@Autowired
	RestaurantService restaurantService;

	@Autowired
	CategoryService categoryService;

	@Autowired
	ClientService clientService;

	@Autowired
	TaxeService taxeService;

	@Autowired
	PrivilegeRepository pR;

	@Autowired
	RoleService rS;

	private final static String EMAIL = "EMAIL1@gmail.com";
	private final static String PASSWORD = "PASSWORD";
	private final static String CATEGORY_NAME = "Pizzas";
	private final static String OPTION_NAME = "Tamaño";
	private final static String OPTIONLINE_NAME = "Mediana";
	private final static String OPTIONLINE_NAME2 = "Pequeña";
	private final static String PRODUCT_NAME = "Hawaiana";
	private final static String PRODUCT_DESCRIPTION = "Esta es la mejor pizza del mundo";
	private final static String TAXE_NAME = "Básico (21%)";
	private static final String PHONE_NUMBER = "637815611";
	private static final Address address = new Address("ESPAÑA", "GALICIA", "A CORUÑA", "AVDA MONELOS", "Nº19-23", "4A",
			"15009", "");
	private static final List<Integer> zipCodes = new LinkedList<>(Arrays.asList(15009));
	private static final Set<RestaurantServiceType> service = new HashSet<>(
			Arrays.asList(RestaurantServiceType.DELIVERY));
	Timestamp currentTime;

	Restaurant restaurant;
	Product product;
	Category category;
	Taxe taxe;
	Client client;

	@Before
	public void Init() throws DuplicatedInstanceException, InvalidValueException, NotValidMinPriceDeliveryException,
			DuplicatedProductDetailsInProductException, IncorrectProductOptionsException, InvalidUserException,
			TimeTableErrorException {

		Privilege privilege = pR.save(new Privilege("ROLE_USER"));
		rS.createNewRole(new Role("ROLE_USER", Arrays.asList(privilege)));
		category = categoryService.createCategory(CATEGORY_NAME);
		taxe = taxeService.createTaxe(TAXE_NAME, 21);
		restaurant = restaurantService.createRestaurant(new Restaurant("Galipizza Coruña", "B27979797",
				"email1@gmail.com", new BigDecimal(5), new BigDecimal(5), PHONE_NUMBER, true, service, address,
				zipCodes, TimeTableUtil.getTimeTable()));

		client = clientService.createClient(EMAIL, PASSWORD);
		currentTime = new Timestamp(new Date().getTime());
		product = createProduct();

	}

	@Test
	public void createOrder()
			throws OrderHasNoProductsException, RestaurantHasNotThisService, RestaurantNotAvaliableException,
			CashOnDeliveryNotAvaliable, ProductNotAvaliable, OutsideAddressRestaurantException {

		List<OrderLineRequestDto> orderLines = new ArrayList<OrderLineRequestDto>();
		List<OrderLinePriceRequestDto> optionLinePrices = new ArrayList<OrderLinePriceRequestDto>();

		optionLinePrices.add(new OrderLinePriceRequestDto(
				product.getOptions().get(0).getOptionLines().get(0).getOptionLinePrices().get(0).getId()));

		orderLines.add(new OrderLineRequestDto(product.getId(), 1, optionLinePrices));

		Address address2 = new Address("ESPAÑA", "GALICIA", "A CORUÑA", "AVDA MONELOS", "Nº19-23", "4A", "15009", "");

		OrderRequestDto order = new OrderRequestDto(client.getId(), address2, restaurant.getId(),
				RestaurantServiceType.DELIVERY, false, "client note example", orderLines);

		Order orderSaved = orderService.createOrder(order);

		Order orderFound = orderService.findOrderById(orderSaved.getId());

		assertEquals(orderSaved, orderFound);
	}

	/**
	 * Función auxiliar para crear un producto
	 * 
	 * @return
	 * @throws DuplicatedInstanceException
	 * @throws DuplicatedProductDetailsInProductException
	 * @throws IncorrectProductOptionsException
	 */
	private Product createProduct() throws DuplicatedInstanceException, DuplicatedProductDetailsInProductException,
			IncorrectProductOptionsException {
		Product p = new Product(PRODUCT_NAME, PRODUCT_DESCRIPTION, category, taxe);
		p.addProductDetail(new ProductDetails(p, restaurant, ProductState.VISIBLE, 0, new BigDecimal(5)));

		List<OptionLinePrice> olps = new ArrayList<>();

		olps.add(new OptionLinePrice(new BigDecimal(5), null, restaurant));

		List<OptionLine> ols = new ArrayList<>();
		ols.add(new OptionLine(OPTIONLINE_NAME, null, olps));
		ols.add(new OptionLine(OPTIONLINE_NAME2, null, olps));

		List<Option> os = new ArrayList<>();

		os.add(new Option(OPTION_NAME, ols, p, true));

		p.setOptions(os);

		return productService.createProduct(p);

	}

	@Test
	public void findOpenOrder()
			throws OrderHasNoProductsException, RestaurantHasNotThisService, RestaurantNotAvaliableException,
			CashOnDeliveryNotAvaliable, ProductNotAvaliable, OutsideAddressRestaurantException {

		List<OrderLineRequestDto> orderLines = new ArrayList<OrderLineRequestDto>();
		List<OrderLinePriceRequestDto> optionLinePrices = new ArrayList<OrderLinePriceRequestDto>();

		optionLinePrices.add(new OrderLinePriceRequestDto(
				product.getOptions().get(0).getOptionLines().get(0).getOptionLinePrices().get(0).getId()));

		orderLines.add(new OrderLineRequestDto(product.getId(), 1, optionLinePrices));

		Address address2 = new Address("ESPAÑA", "GALICIA", "A CORUÑA", "AVDA MONELOS", "Nº19-23", "4A", "15009", "");

		OrderRequestDto order = new OrderRequestDto(client.getId(), address2, restaurant.getId(),
				RestaurantServiceType.DELIVERY, false, "client note example", orderLines);

		Order orderSaved = orderService.createOrder(order);

		orderSaved.setStatus(OrderStatus.CLOSED);

		List<OrderLineRequestDto> orderLines2 = new ArrayList<OrderLineRequestDto>();

		orderLines2.add(new OrderLineRequestDto(product.getId(), 1, optionLinePrices));

		OrderRequestDto order2 = new OrderRequestDto(client.getId(), address2, restaurant.getId(),
				RestaurantServiceType.DELIVERY, false, "client note example", orderLines2);

		Order orderSaved2 = orderService.createOrder(order2);
		List<Order> orders = orderService.findOpenOrdersByRestaurant(restaurant.getId());
		assertTrue(orders.size() == 1);
		assertEquals(orders.get(0), orderSaved2);

	}

}

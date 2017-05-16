package es.mabu.mr.book;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Calendar;
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
import es.mabu.mr.order.OrderService;
import es.mabu.mr.product.Product;
import es.mabu.mr.product.ProductService;
import es.mabu.mr.product.category.Category;
import es.mabu.mr.product.category.CategoryService;
import es.mabu.mr.product.exception.DuplicatedProductDetailsInProductException;
import es.mabu.mr.product.exception.IncorrectProductOptionsException;
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
public class BookServiceTest {

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

	@Autowired
	BookService bookService;

	private final static String EMAIL = "EMAIL1@gmail.com";
	private final static String PASSWORD = "PASSWORD";
	private final static String CATEGORY_NAME = "Pizzas";
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

	}

	@Test
	public void bookTable() throws ParseException, CantBookException {

		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
		cal.setTime(sdf.parse("12:00:00 04/05/2019"));

		Book savedBook = bookService.bookTable(client.getId(), restaurant.getId(), 10, cal);

		assertEquals(savedBook, savedBook);
	}

	@Test(expected = CantBookException.class)
	public void bookWrongHourTable() throws ParseException, CantBookException {

		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss dd/MM/yyyy");
		cal.setTime(sdf.parse("16:00:00 04/05/2019"));

		Book savedBook = bookService.bookTable(client.getId(), restaurant.getId(), 10, cal);

		assertEquals(savedBook, savedBook);
	}

}

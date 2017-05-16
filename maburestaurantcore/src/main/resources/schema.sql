CREATE SEQUENCE IF NOT EXISTS hibernate_sequence START 100;
CREATE SEQUENCE IF NOT EXISTS address_id_seq START 100;

CREATE TABLE IF NOT EXISTS address(
	id BIGINT NOT NULL  DEFAULT nextval('address_id_seq') PRIMARY KEY,
	country VARCHAR(100) NOT NULL,
	state VARCHAR(100) NOT NULL,
	city VARCHAR(100) NOT NULL,
	street VARCHAR(100) NOT NULL,
	number VARCHAR(10) NOT NULL,
	unity VARCHAR(10),
	postal_code VARCHAR(10) NOT NULL, -- correct?
	observations VARCHAR(200),
	
	latitude DOUBLE PRECISION,
	longitude DOUBLE PRECISION
);

CREATE SEQUENCE IF NOT EXISTS taxe_id_seq START 100; 

CREATE TABLE IF NOT EXISTS taxe(
	id BIGINT NOT NULL  DEFAULT nextval('taxe_id_seq') PRIMARY KEY,
	name VARCHAR(50) NOT NULL,
	value INTEGER NOT NULL
);

CREATE SEQUENCE IF NOT EXISTS category_id_seq START 100; 

CREATE TABLE IF NOT EXISTS category(
	id BIGINT NOT NULL  DEFAULT nextval('category_id_seq') PRIMARY KEY,
	name VARCHAR(100) NOT NULL
);

CREATE SEQUENCE IF NOT EXISTS allergen_id_seq START 100;

CREATE TABLE IF NOT EXISTS allergen(
	id BIGINT NOT NULL  DEFAULT nextval('allergen_id_seq') PRIMARY KEY,
	name VARCHAR(100) NOT NULL
);

CREATE SEQUENCE IF NOT EXISTS time_table_id_seq START 100;

CREATE TABLE IF NOT EXISTS time_table (
	id BIGINT NOT NULL  DEFAULT nextval('time_table_id_seq') PRIMARY KEY	
);

CREATE SEQUENCE IF NOT EXISTS closed_day_id_seq START 100;

CREATE TABLE IF NOT EXISTS closed_day (
	id BIGINT NOT NULL  DEFAULT nextval('closed_day_id_seq') PRIMARY KEY,
	day date,
	time_table_id BIGINT REFERENCES time_table(id)	
	
);

CREATE SEQUENCE IF NOT EXISTS day_id_seq START 100;

CREATE TABLE IF NOT EXISTS day (
	id BIGINT NOT NULL  DEFAULT nextval('day_id_seq') PRIMARY KEY,
	day INT NOT NULL,
	open_morning boolean,
	opening_morning INT,
	last_hour_to_morning_book INT,
	open_dinner boolean,
	opening_dinner INT,
	last_hour_to_dinner_book INT,
	time_table_id BIGINT REFERENCES time_table(id)
	
);




CREATE SEQUENCE IF NOT EXISTS restaurant_id_seq START 100;

CREATE TABLE IF NOT EXISTS restaurant (
	id BIGINT NOT NULL  DEFAULT nextval('restaurant_id_seq') PRIMARY KEY,
	name VARCHAR(100) NOT NULL, 
	email VARCHAR(255) ,
	nif VARCHAR(20) ,  
	min_price_delivery NUMERIC,
	trasport_price NUMERIC, 
	visible boolean,
	phonenumber VARCHAR(20),
	addressid BIGINT NOT NULL REFERENCES address(id),
	time_table_id BIGINT REFERENCES time_table(id)
);



CREATE TABLE  IF NOT EXISTS  zip_code (
  restaurant_id BIGINT  REFERENCES restaurant(id),
  zip_code INT,
  PRIMARY KEY (restaurant_id, zip_code)
);

CREATE TABLE  IF NOT EXISTS  restaurant_service_type (
  restaurant_id BIGINT  REFERENCES restaurant(id),
  restaurant_service_type VARCHAR(50),
  PRIMARY KEY (restaurant_id, restaurant_service_type)
);


CREATE SEQUENCE IF NOT EXISTS product_id_seq START 100;

CREATE TABLE IF NOT EXISTS product(
	id BIGINT NOT NULL  DEFAULT nextval('product_id_seq') PRIMARY KEY,
	name VARCHAR(50) NOT NULL,
	description VARCHAR(500),
	categoryid BIGINT REFERENCES category (id),
	taxeid BIGINT NOT NULL REFERENCES taxe (id),
	created timestamp,
	image_name VARCHAR(10)
);

CREATE SEQUENCE IF NOT EXISTS productoption_id_seq START 100;

CREATE TABLE IF NOT EXISTS productoption (
	id BIGINT NOT NULL  DEFAULT nextval('productoption_id_seq') PRIMARY KEY,
	productid BIGINT NOT NULL REFERENCES product (id) ,
	main BOOLEAN DEFAULT false NOT NULL,
	name VARCHAR(100) NOT NULL
);

CREATE SEQUENCE IF NOT EXISTS option_line_id_seq START 100;

CREATE TABLE IF NOT EXISTS option_line (
	id BIGINT NOT NULL DEFAULT nextval('option_line_id_seq') PRIMARY KEY ,
	optionid BIGINT NOT NULL REFERENCES productoption (id),
	name VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS product_allergen(
	productid BIGINT NOT NULL REFERENCES product (id) ,
	allergenid BIGINT NOT NULL REFERENCES allergen (id),
	CONSTRAINT PK_product_allergen PRIMARY KEY(productId,allergenid)
);

CREATE SEQUENCE IF NOT EXISTS product_restaurant_id_seq START 100; 

CREATE TABLE IF NOT EXISTS product_restaurant(
	id BIGINT NOT NULL  DEFAULT nextval('product_restaurant_id_seq') PRIMARY KEY,
	productid BIGINT NOT NULL REFERENCES product (id) ,
	restaurantid BIGINT NOT NULL REFERENCES restaurant (id) ,
	state VARCHAR(100),
	mainpage INTEGER,
	price NUMERIC NOT NULL,
	modified timestamp,
	created timestamp
);

CREATE SEQUENCE IF NOT EXISTS optionline_restaurant_id_seq START 100;

CREATE TABLE IF NOT EXISTS optionline_restaurant(
	id BIGINT NOT NULL  DEFAULT nextval('optionline_restaurant_id_seq') PRIMARY KEY,
	optionlineid BIGINT NOT NULL REFERENCES option_line (id) ,
	restaurantid BIGINT NOT NULL REFERENCES restaurant (id) ,
	priceadded NUMERIC NOT NULL
);


-- _____________________ Gestion de usuarios ___________________________
CREATE SEQUENCE IF NOT EXISTS user_id_seq START 100;

CREATE TABLE IF NOT EXISTS Account (
  id BIGINT NOT NULL  DEFAULT nextval('user_id_seq') PRIMARY KEY,
  email VARCHAR(100) NOT NULL,
  password VARCHAR(200) NOT NULL,
  enabled BOOLEAN DEFAULT true NOT NULL,
  credentialsexpired BOOLEAN DEFAULT false NOT NULL,
  expired BOOLEAN DEFAULT false NOT NULL,
  locked BOOLEAN DEFAULT false NOT NULL,
  USER_TYPE VARCHAR(20) NOT NULL,
  CONSTRAINT UQ_Account_email UNIQUE (email)
);

CREATE TABLE IF NOT EXISTS Client (
	id BIGINT PRIMARY KEY,
	stripeid VARCHAR(400),
	last4carddigits VARCHAR(10),
	name VARCHAR(100),
	addressid BIGINT REFERENCES address(id),
	CONSTRAINT FK_Client_Account FOREIGN KEY (id) REFERENCES Account (id)
);

CREATE TABLE IF NOT EXISTS Employee (
	id BIGINT PRIMARY KEY,
	name VARCHAR(100) NOT NULL,
	CONSTRAINT FK_Employee_Account FOREIGN KEY (id) REFERENCES Account (id)
);

CREATE SEQUENCE IF NOT EXISTS role_id_seq START 100;

CREATE TABLE IF NOT EXISTS Role (
  id BIGINT NOT NULL  DEFAULT nextval('role_id_seq') PRIMARY KEY,
  name VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS Account_Role (
  account_Id BIGINT NOT NULL,
  role_Id BIGINT NOT NULL,
  PRIMARY KEY (account_Id, role_Id),
  CONSTRAINT FK_AccountRole_AccountId FOREIGN KEY (account_Id) REFERENCES Account (id),
  CONSTRAINT FK_AccountRole_RoleId FOREIGN KEY (role_Id) REFERENCES Role (id)
);

CREATE SEQUENCE IF NOT EXISTS privilege_id_seq START 100;

CREATE TABLE IF NOT EXISTS Privilege (
  id BIGINT NOT NULL  DEFAULT nextval('privilege_id_seq') PRIMARY KEY,
  name VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS Role_Privilege (
  role_Id BIGINT NOT NULL,
  privilege_Id BIGINT NOT NULL,
  PRIMARY KEY (role_Id,privilege_Id),
  CONSTRAINT FK_RolePrivilege_RoleId FOREIGN KEY (role_Id) REFERENCES Role (id),
  CONSTRAINT FK_RolePrivilege_PrivilegeId FOREIGN KEY (privilege_Id) REFERENCES Privilege (id)

);


CREATE SEQUENCE IF NOT EXISTS order_id_seq START 100;

CREATE TABLE IF NOT EXISTS orderr(
	id BIGINT NOT NULL DEFAULT nextval('order_id_seq') PRIMARY KEY,
	clientid BIGINT NOT NULL REFERENCES client (id),
	addressid BIGINT REFERENCES address(id), 
	restaurantid BIGINT NOT NULL REFERENCES restaurant (id),
	servicetype VARCHAR(50) NOT NULL, -- ['Collection' or 'Delivery'],
	estimated_pickup_or_delivery_time timestamp,  --tiempo en el que va a estar disponible	
	totalprice NUMERIC NOT NULL, -- debo guardar el precio total?
	delivery_charge NUMERIC, --gastos de envio
	cash_on_delivery boolean, --ya pagó o va a pagar en al entrega
	client_note VARCHAR(300), --nota del client
	Discount NUMERIC, -- cantidad 20
	discountype VARCHAR(100), -- porcentaje o dinero
	created timestamp,
	sent timestamp,
	payment_details_id BIGINT,
	status VARCHAR(30) --status del pedido
);

CREATE SEQUENCE IF NOT EXISTS order_line_id_seq START 100;

CREATE TABLE IF NOT EXISTS order_line(
	id BIGINT NOT NULL DEFAULT nextval('order_line_id_seq') PRIMARY KEY,
	orderid BIGINT  NOT NULL REFERENCES orderr(id),
	productid BIGINT NOT NULL REFERENCES product(id),
	quantity INTEGER NOT NULL,
	taxeid BIGINT NOT NULL REFERENCES taxe(id),
	price  NUMERIC NOT NULL,
	totalprice  NUMERIC NOT NULL
);


CREATE TABLE IF NOT EXISTS order_line_optionline_restaurant(
	orderline_id BIGINT,
	optionline_restaurant_id BIGINT ,
	 PRIMARY KEY (orderline_id, optionline_restaurant_id),
  	CONSTRAINT FK_order_line_options_orderline_id FOREIGN KEY (orderline_id) REFERENCES order_line(id),
  	CONSTRAINT FK_order_line_options_optionline_restaurant_id FOREIGN KEY (optionline_restaurant_id) REFERENCES optionline_restaurant (id)
);

CREATE TABLE IF NOT EXISTS payment_details(
		orderid BIGINT NOT NULL PRIMARY KEY,
		system VARCHAR(50) NOT NULL,
		chargedid VARCHAR(200) NOT NULL,
		createdcharge timestamp NOT NULL,
		fee  NUMERIC ,
		amount  NUMERIC,
		refunded boolean,
		refundid VARCHAR(200),
		createdrefund timestamp,
		
		CONSTRAINT FK_payment_details_orderid FOREIGN KEY (orderid) REFERENCES orderr(id)
);



CREATE SEQUENCE IF NOT EXISTS book_id_seq START 100;

CREATE TABLE IF NOT EXISTS book(
	id BIGINT NOT NULL DEFAULT nextval('book_id_seq') PRIMARY KEY,
	clientid BIGINT NOT NULL REFERENCES client (id),
	restaurantid BIGINT NOT NULL REFERENCES restaurant (id),
	hour timestamp,
	number_of_persons INT
);

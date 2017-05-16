INSERT INTO address (id,country, state, city, street, number, unity, postal_code) 
	VALUES( '1','España', 'Galicia','A Coruña','Avda. Monelos', '19-23', '4A','15009');
	
INSERT INTO address (id,country, state, city, street, number, unity, postal_code) 
	VALUES( '2','España', 'Galicia','Santiago de Compostela','Avda. Mallos', '1', 'Bajo ','15009');

INSERT INTO address (id,country, state, city, street, number, unity, postal_code) 
	VALUES( '3','España', 'Galicia','Lugo','Avda. Rosalia de castro', '20', 'Bajo','2009');

INSERT INTO address (id,country, state, city, street, number, unity, postal_code) 
	VALUES( '4','España', 'Galicia','Pontevedra','Avda. Eduardo Pondal', '19-23', 'Bajo','27880');

--TAXES
INSERT INTO taxe VALUES  ('1', 'Productos alimentarios (21%)', 21);
INSERT INTO taxe VALUES ('2', 'Productos basicos (10%)', 10);
INSERT INTO taxe VALUES ('3', 'Resto (30%)', 30);

--CATEGORY
INSERT INTO category VALUES ('1', 'Pizzas');
INSERT INTO category VALUES ('2', 'Hamburgesas');
INSERT INTO category VALUES ('3', 'Menus');
INSERT INTO category VALUES ('4', 'Platos combinados');
INSERT INTO category VALUES ('5', 'Postres');

--ALLERGEN
INSERT INTO allergen VALUES ('1', 'Altramuz');
INSERT INTO allergen VALUES ('2', 'Moluscos');
INSERT INTO allergen VALUES ('3', 'Sulfitos');
INSERT INTO allergen VALUES ('4', 'Sesamo');
INSERT INTO allergen VALUES ('5', 'Mostaza');

--RESTAURANT
INSERT INTO restaurant VALUES ('1', 'A Coruña','galipiza@gmail.com','3559178F','10','10',true,'637229153','1',null);
INSERT INTO restaurant VALUES ('2', 'Santiago de Compostela','galipiza@gmail.com','35591788F','10','10',true,'637229153','2',null);
INSERT INTO restaurant VALUES ('3', 'Lugo','galipiza@gmail.com','35591788F','10','10',true,'637229153','3',null);
INSERT INTO restaurant VALUES ('4', 'Pontevedra','galipiza@gmail.com','35591788F','10','10',true,'637229153','4',null);

Insert into zip_code VALUES (1,15007);
Insert into restaurant_service_type VALUES (1,'DELIVERY');


--PRODUCT
INSERT INTO product VALUES ('1', 'Chessix', 
'Salsa BBQ, mozzarella, ternera deshilachada asada lentamente, cebolla, bacon, salsa chimichurri.',
'1',
'1',
TIMESTAMP '2011-05-16 15:36:38','1.png');

INSERT INTO product VALUES ('2', 'Artesana', 
'Crema fresca, queso 100% mozzarella, cheddar, emmental, parmesano, gorgonzola, queso de cabra.',
'1',
'1',
TIMESTAMP '2011-05-16 15:36:38','2.png');

INSERT INTO product VALUES ('3', 'Pulled Beef', 
'Salsa BBQ, mozzarella, ternera deshilachada asada lentamente, cebolla, bacon, salsa chimichurri.',
'1',
'1',
TIMESTAMP '2011-05-16 15:36:38','3.png');

INSERT INTO product VALUES ('4', 'Pulled Pork', 
'Salsa BBQ, mozzarella, cerdo deshilachado asado lentamente, bacon, pimiento verde.',
'1',
'1',
TIMESTAMP '2011-05-16 15:36:38','4.png');

INSERT INTO product VALUES ('5', 'Barbacoa', 
'Salsa barbacoa, queso 100% mozzarella, ternera especiada, cebolla, bacon, maíz.',
'1',
'1',
TIMESTAMP '2011-05-16 15:36:38','5.png');

INSERT INTO product VALUES ('6', 'Pecado Carnal', 
'Salsa de tomate, extra de queso 100% mozzarella, ternera especiada, bacon, pepperoni, york',
'1',
'1',
TIMESTAMP '2011-05-16 15:36:38','6.png');

INSERT INTO product VALUES ('7', 'Cremozza BBQ', 
'Crema fresca, queso 100% mozzarella, bacon, champiñón y cebolla.',
'1',
'1',
TIMESTAMP '2011-05-16 15:36:38','7.png');

INSERT INTO product VALUES ('8', 'Carbonara', 
'Base de crema fresca, queso 100% mozzarella, bacon, pollo a la parrilla, cebolla, salsa barbacoa.',
'1',
'1',
TIMESTAMP '2011-05-16 15:36:38','8.png');

INSERT INTO product VALUES ('9', 'Pollo a la parrilla', 
'Salsa de tomate, queso 100% mozzarella, doble de pollo a la parrilla, cebolla, champiñón y maíz.',
'1',
'1',
TIMESTAMP '2011-05-16 15:36:38','9.png');


INSERT INTO product VALUES ('10', 'Cuatro quesos', 
'Salsa de tomate, queso 100% mozzarella, cheddar, emmental, gorgonzola (receta mejorada).',
'1',
'1',
TIMESTAMP '2011-05-16 15:36:38','10.png');


INSERT INTO product VALUES ('11', 'Hawaiana Plus', 
'Salsa de tomate, extra de queso 100% mozzarella, doble de York y piña.',
'1',
'1',
TIMESTAMP '2011-05-16 15:36:38','11.png');

INSERT INTO product VALUES ('12', 'Extravaganza', 
'Salsa de tomate, mozzarella, ternera especiada, pepperoni, York, bacon, cebolla, pimiento verde, champiñón y aceitunas negras.',
'1',
'1',
TIMESTAMP '2011-05-16 15:36:38','12.png');


INSERT INTO product VALUES ('13', 'Cabramlizada', 
'Crema fresca, queso 100% mozzarella, queso de cabra, cebolla caramelizada.',
'1',
'1',
TIMESTAMP '2011-05-16 15:36:38','13.png');


INSERT INTO product VALUES ('14', 'Carbonara', 
'Salsa de tomate, queso 100% mozzarella, jamón serrano, aceite de oliva Carbonell, aceitunas negras, tomate natural y orégano.',
'1',
'1',
TIMESTAMP '2011-05-16 15:36:38','14.png');


--OPTION 
INSERT INTO productOption VALUES ('1','1',true,'Tamaño');
INSERT INTO productOption VALUES ('2','2',true,'Tamaño');
INSERT INTO productOption VALUES ('3','3',true,'Tamaño');
INSERT INTO productOption VALUES ('4','4',true,'Tamaño');
INSERT INTO productOption VALUES ('5','5',true,'Tamaño');
INSERT INTO productOption VALUES ('6','6',true,'Tamaño');
INSERT INTO productOption VALUES ('7','7',true,'Tamaño');
INSERT INTO productOption VALUES ('8','8',true,'Tamaño');
INSERT INTO productOption VALUES ('9','9',true,'Tamaño');
INSERT INTO productOption VALUES ('10','10',true,'Tamaño');
INSERT INTO productOption VALUES ('11','11',true,'Tamaño');
INSERT INTO productOption VALUES ('12','12',true,'Tamaño');
INSERT INTO productOption VALUES ('13','13',true,'Tamaño');
INSERT INTO productOption VALUES ('14','14',true,'Tamaño');

INSERT INTO productOption VALUES ('15','1',false,'Salsa');


--OPTION LINE
INSERT INTO option_line VALUES ('1','1','Pequeña');
INSERT INTO option_line VALUES ('2','1','Mediana');
INSERT INTO option_line VALUES ('3','1','Grande');

INSERT INTO option_line VALUES ('4','2','Pequeña');
INSERT INTO option_line VALUES ('5','2','Mediana');
INSERT INTO option_line VALUES ('6','2','Grande');

INSERT INTO option_line VALUES ('7','3','Pequeña');
INSERT INTO option_line VALUES ('8','3','Mediana');
INSERT INTO option_line VALUES ('9','3','Grande');

INSERT INTO option_line VALUES ('10','4','Pequeña');
INSERT INTO option_line VALUES ('11','4','Mediana');
INSERT INTO option_line VALUES ('12','4','Grande');

INSERT INTO option_line VALUES ('13','5','Pequeña');
INSERT INTO option_line VALUES ('14','5','Mediana');
INSERT INTO option_line VALUES ('15','5','Grande');

INSERT INTO option_line VALUES ('16','6','Pequeña');
INSERT INTO option_line VALUES ('17','6','Mediana');
INSERT INTO option_line VALUES ('18','6','Grande');

INSERT INTO option_line VALUES ('19','7','Pequeña');
INSERT INTO option_line VALUES ('20','7','Mediana');
INSERT INTO option_line VALUES ('21','7','Grande');

INSERT INTO option_line VALUES ('22','8','Pequeña');
INSERT INTO option_line VALUES ('23','8','Mediana');
INSERT INTO option_line VALUES ('24','8','Grande');

INSERT INTO option_line VALUES ('25','9','Pequeña');
INSERT INTO option_line VALUES ('26','9','Mediana');
INSERT INTO option_line VALUES ('27','9','Grande');

INSERT INTO option_line VALUES ('28','10','Pequeña');
INSERT INTO option_line VALUES ('29','10','Mediana');
INSERT INTO option_line VALUES ('30','10','Grande');

INSERT INTO option_line VALUES ('31','11','Pequeña');
INSERT INTO option_line VALUES ('32','11','Mediana');
INSERT INTO option_line VALUES ('33','11','Grande');

INSERT INTO option_line VALUES ('34','12','Pequeña');
INSERT INTO option_line VALUES ('35','12','Mediana');
INSERT INTO option_line VALUES ('36','12','Grande');

INSERT INTO option_line VALUES ('37','15','Mayonesa');
INSERT INTO option_line VALUES ('38','15','Alioli');
INSERT INTO option_line VALUES ('39','15','Tomate');


--OPTION LINE_ RESTAURANT

INSERT INTO optionline_restaurant VALUES ('1','1','1','9');
INSERT INTO optionline_restaurant VALUES ('2','2','1','13');
INSERT INTO optionline_restaurant VALUES ('3','3','1','16');

INSERT INTO optionline_restaurant VALUES ('4','4','1','9');
INSERT INTO optionline_restaurant VALUES ('5','5','1','13');
INSERT INTO optionline_restaurant VALUES ('6','6','1','16');

INSERT INTO optionline_restaurant VALUES ('7','7','1','9');
INSERT INTO optionline_restaurant VALUES ('8','8','1','13');
INSERT INTO optionline_restaurant VALUES ('9','9','1','16');

INSERT INTO optionline_restaurant VALUES ('10','10','1','9');
INSERT INTO optionline_restaurant VALUES ('11','11','1','13');
INSERT INTO optionline_restaurant VALUES ('12','12','1','16');

INSERT INTO optionline_restaurant VALUES ('13','13','1','9');
INSERT INTO optionline_restaurant VALUES ('14','14','1','13');
INSERT INTO optionline_restaurant VALUES ('15','15','1','16');

INSERT INTO optionline_restaurant VALUES ('16','16','1','9');
INSERT INTO optionline_restaurant VALUES ('17','17','1','13');
INSERT INTO optionline_restaurant VALUES ('18','18','1','16');

INSERT INTO optionline_restaurant VALUES ('19','19','1','9');
INSERT INTO optionline_restaurant VALUES ('20','20','1','13');
INSERT INTO optionline_restaurant VALUES ('21','21','1','16');

INSERT INTO optionline_restaurant VALUES ('22','22','1','9');
INSERT INTO optionline_restaurant VALUES ('23','23','1','13');
INSERT INTO optionline_restaurant VALUES ('24','24','1','16');

INSERT INTO optionline_restaurant VALUES ('25','25','1','9');
INSERT INTO optionline_restaurant VALUES ('26','26','1','13');
INSERT INTO optionline_restaurant VALUES ('27','27','1','16');

INSERT INTO optionline_restaurant VALUES ('28','28','1','9');
INSERT INTO optionline_restaurant VALUES ('29','29','1','13');
INSERT INTO optionline_restaurant VALUES ('30','30','1','16');

INSERT INTO optionline_restaurant VALUES ('31','31','1','9');
INSERT INTO optionline_restaurant VALUES ('32','32','1','13');
INSERT INTO optionline_restaurant VALUES ('33','33','1','16');

INSERT INTO optionline_restaurant VALUES ('34','34','1','9');
INSERT INTO optionline_restaurant VALUES ('35','35','1','13');
INSERT INTO optionline_restaurant VALUES ('36','36','1','16');

INSERT INTO optionline_restaurant VALUES ('37','1','2','9');
INSERT INTO optionline_restaurant VALUES ('38','2','2','13');
INSERT INTO optionline_restaurant VALUES ('39','3','2','16');

INSERT INTO optionline_restaurant VALUES ('40','4','2','9');
INSERT INTO optionline_restaurant VALUES ('41','5','2','13');
INSERT INTO optionline_restaurant VALUES ('42','6','2','16');

INSERT INTO optionline_restaurant VALUES ('43','7','2','9');
INSERT INTO optionline_restaurant VALUES ('44','8','2','13');
INSERT INTO optionline_restaurant VALUES ('45','9','2','16');

INSERT INTO optionline_restaurant VALUES ('46','10','2','9');
INSERT INTO optionline_restaurant VALUES ('47','11','2','13');
INSERT INTO optionline_restaurant VALUES ('48','12','2','16');

INSERT INTO optionline_restaurant VALUES ('49','13','2','9');
INSERT INTO optionline_restaurant VALUES ('50','14','2','13');
INSERT INTO optionline_restaurant VALUES ('51','15','2','16');

INSERT INTO optionline_restaurant VALUES ('52','16','2','9');
INSERT INTO optionline_restaurant VALUES ('53','17','2','13');
INSERT INTO optionline_restaurant VALUES ('54','18','2','16');

INSERT INTO optionline_restaurant VALUES ('55','19','2','9');
INSERT INTO optionline_restaurant VALUES ('56','20','2','13');
INSERT INTO optionline_restaurant VALUES ('57','21','2','16');

INSERT INTO optionline_restaurant VALUES ('58','22','2','9');
INSERT INTO optionline_restaurant VALUES ('59','23','2','13');
INSERT INTO optionline_restaurant VALUES ('60','24','2','16');

INSERT INTO optionline_restaurant VALUES ('61','25','2','9');
INSERT INTO optionline_restaurant VALUES ('62','26','2','13');
INSERT INTO optionline_restaurant VALUES ('63','27','2','16');

INSERT INTO optionline_restaurant VALUES ('64','28','2','9');
INSERT INTO optionline_restaurant VALUES ('65','29','2','13');
INSERT INTO optionline_restaurant VALUES ('66','30','2','16');

INSERT INTO optionline_restaurant VALUES ('67','31','2','9');
INSERT INTO optionline_restaurant VALUES ('68','32','2','13');
INSERT INTO optionline_restaurant VALUES ('69','33','2','16');

INSERT INTO optionline_restaurant VALUES ('70','34','2','9');
INSERT INTO optionline_restaurant VALUES ('71','35','2','13');
INSERT INTO optionline_restaurant VALUES ('72','36','2','16');


INSERT INTO optionline_restaurant VALUES ('73','37','1','0.5');
INSERT INTO optionline_restaurant VALUES ('74','38','1','1');
INSERT INTO optionline_restaurant VALUES ('75','39','1','2');


--PRODUCT RESTAURANT
INSERT INTO product_restaurant VALUES ('1','1','1','VISIBLE',1, 10, TIMESTAMP '2011-05-16 15:36:38', TIMESTAMP '2011-05-16 15:36:38');
INSERT INTO product_restaurant VALUES ('2','2','1','VISIBLE',2, 10, TIMESTAMP '2011-05-16 15:36:38', TIMESTAMP '2011-05-16 15:36:38');
INSERT INTO product_restaurant VALUES ('3','3','1','VISIBLE',3, 10, TIMESTAMP '2011-05-16 15:36:38', TIMESTAMP '2011-05-16 15:36:38');
INSERT INTO product_restaurant VALUES ('4','4','1','VISIBLE',4, 10, TIMESTAMP '2011-05-16 15:36:38', TIMESTAMP '2011-05-16 15:36:38');
INSERT INTO product_restaurant VALUES ('5','5','1','VISIBLE',5, 10, TIMESTAMP '2011-05-16 15:36:38', TIMESTAMP '2011-05-16 15:36:38');
INSERT INTO product_restaurant VALUES ('6','6','1','VISIBLE',6, 10, TIMESTAMP '2011-05-16 15:36:38', TIMESTAMP '2011-05-16 15:36:38');
INSERT INTO product_restaurant VALUES ('7','7','1','VISIBLE',7, 10, TIMESTAMP '2011-05-16 15:36:38', TIMESTAMP '2011-05-16 15:36:38');
INSERT INTO product_restaurant VALUES ('8','8','1','VISIBLE',8, 10, TIMESTAMP '2011-05-16 15:36:38', TIMESTAMP '2011-05-16 15:36:38');
INSERT INTO product_restaurant VALUES ('9','9','1','VISIBLE',9, 10, TIMESTAMP '2011-05-16 15:36:38', TIMESTAMP '2011-05-16 15:36:38');
INSERT INTO product_restaurant VALUES ('10','10','1','VISIBLE',10, 10, TIMESTAMP '2011-05-16 15:36:38', TIMESTAMP '2011-05-16 15:36:38');
INSERT INTO product_restaurant VALUES ('11','11','1','VISIBLE',11, 10, TIMESTAMP '2011-05-16 15:36:38', TIMESTAMP '2011-05-16 15:36:38');
INSERT INTO product_restaurant VALUES ('12','12','1','VISIBLE',12, 10, TIMESTAMP '2011-05-16 15:36:38', TIMESTAMP '2011-05-16 15:36:38');
INSERT INTO product_restaurant VALUES ('13','13','1','VISIBLE',13, 10, TIMESTAMP '2011-05-16 15:36:38', TIMESTAMP '2011-05-16 15:36:38');

INSERT INTO product_restaurant VALUES ('14','1','2','VISIBLE',1, 12, TIMESTAMP '2011-05-16 15:36:38', TIMESTAMP '2011-05-16 15:36:38');
INSERT INTO product_restaurant VALUES ('15','2','2','VISIBLE',2, 12, TIMESTAMP '2011-05-16 15:36:38', TIMESTAMP '2011-05-16 15:36:38');
INSERT INTO product_restaurant VALUES ('16','3','2','VISIBLE',3, 12, TIMESTAMP '2011-05-16 15:36:38', TIMESTAMP '2011-05-16 15:36:38');
INSERT INTO product_restaurant VALUES ('17','4','2','VISIBLE',4, 12, TIMESTAMP '2011-05-16 15:36:38', TIMESTAMP '2011-05-16 15:36:38');
INSERT INTO product_restaurant VALUES ('18','5','3','VISIBLE',5, 30, TIMESTAMP '2011-05-16 15:36:38', TIMESTAMP '2011-05-16 15:36:38');



--ACCOUNTS

INSERT INTO account(id, email, password, enabled, credentialsexpired, expired, locked, user_type) VALUES (1,'admin@admin.com','$2a$10$.RnSWviXxWJ9HQL3n9TTE.nrEkaOpaDFCtPmGBX0hmX0faKl5TDCy',TRUE,FALSE,FALSE,FALSE,'Employee');
INSERT INTO employee(id, name) VALUES (1,'christian');

INSERT INTO account(id, email, password, enabled, credentialsexpired, expired, locked, user_type) VALUES (2,'user@user.com','$2a$10$.RnSWviXxWJ9HQL3n9TTE.nrEkaOpaDFCtPmGBX0hmX0faKl5TDCy',TRUE,FALSE,FALSE,FALSE,'Client');
INSERT INTO client(id, name, addressid) VALUES (2,'alex', 1);

--ROLES
INSERT INTO role(id, name) VALUES (1,'ROLE_USER');
INSERT INTO role(id, name) VALUES (2,'ROLE_CLIENT');
INSERT INTO role(id, name) VALUES (3,'ROLE_EMPLOYEE');


--PERMISOS
INSERT INTO privilege(id, name) VALUES (1,'ROLE_USER');
INSERT INTO privilege(id, name) VALUES (2,'ROLE_CLIENT');
INSERT INTO privilege(id, name) VALUES (3,'ROLE_EMPLOYEE');
INSERT INTO privilege(id, name) VALUES (4,'ROLE_USER_MANAGER');

INSERT INTO role_privilege(role_id, privilege_id) VALUES (1,1);
INSERT INTO role_privilege(role_id, privilege_id) VALUES (2,1);
INSERT INTO role_privilege(role_id, privilege_id) VALUES (2,2);
INSERT INTO role_privilege(role_id, privilege_id) VALUES (3,3);
INSERT INTO role_privilege(role_id, privilege_id) VALUES (3,4);


INSERT INTO account_role( account_id, role_id)  VALUES (1,3);
INSERT INTO account_role( account_id, role_id)  VALUES (2,2);
commit;



--Orders


INSERT INTO orderr (id, clientid, addressid, restaurantid, servicetype, estimated_pickup_or_delivery_time, totalprice, delivery_charge, cash_on_delivery, client_note, discount, discountype, created, sent, status) VALUES (1, 2, 1, 1, 'DELIVERY', '2011-05-16 15:36:38', 10, 1.23, true, 'opinion cliente', 10, 'PERCENTAGE', '2011-05-16 15:36:38', '2011-05-16 15:36:38', 'PAID');
INSERT INTO orderr (id, clientid, addressid, restaurantid, servicetype, estimated_pickup_or_delivery_time, totalprice, delivery_charge, cash_on_delivery, client_note, discount, discountype, created, sent, status) VALUES (2, 2, 1, 1, 'DELIVERY', '2011-05-16 15:36:38', 10, 1.23, true, 'opinion cliente', 10, 'PERCENTAGE', '2011-05-16 15:36:38', '2011-05-16 15:36:38', 'PAID');
INSERT INTO orderr (id, clientid, addressid, restaurantid, servicetype, estimated_pickup_or_delivery_time, totalprice, delivery_charge, cash_on_delivery, client_note, discount, discountype, created, sent, status) VALUES (3, 2, 1, 1, 'DELIVERY', '2011-05-16 15:36:38', 10, 1.23, true, 'opinion cliente', 10, 'PERCENTAGE', '2011-05-16 15:36:38', '2011-05-16 15:36:38', 'COOKING');
INSERT INTO orderr (id, clientid, addressid, restaurantid, servicetype, estimated_pickup_or_delivery_time, totalprice, delivery_charge, cash_on_delivery, client_note, discount, discountype, created, sent, status) VALUES (4, 2, 1, 1, 'DELIVERY', '2011-05-16 15:36:38', 10, 1.23, true, 'opinion cliente', 10, 'PERCENTAGE', '2011-05-16 15:36:38', '2011-05-16 15:36:38', 'SENDED');
INSERT INTO orderr (id, clientid, addressid, restaurantid, servicetype, estimated_pickup_or_delivery_time, totalprice, delivery_charge, cash_on_delivery, client_note, discount, discountype, created, sent, status) VALUES (5, 2, 1, 1, 'DELIVERY', NULL, 41, 0, false, 'client note', NULL, NULL, '2017-04-24 22:54:18.425', NULL, 'PAID');
INSERT INTO orderr (id, clientid, addressid, restaurantid, servicetype, estimated_pickup_or_delivery_time, totalprice, delivery_charge, cash_on_delivery, client_note, discount, discountype, created, sent, status) VALUES (6, 2, 1, 1, 'DELIVERY', NULL, 41, 0, false, 'client note', NULL, NULL, '2017-04-24 22:54:21.98', NULL, 'PAID');
INSERT INTO orderr (id, clientid, addressid, restaurantid, servicetype, estimated_pickup_or_delivery_time, totalprice, delivery_charge, cash_on_delivery, client_note, discount, discountype, created, sent, status) VALUES (7, 2, 1, 1, 'DELIVERY', NULL, 41, 0, false, 'client note', NULL, NULL, '2017-04-24 22:54:23.037', NULL, 'PAID');
INSERT INTO orderr (id, clientid, addressid, restaurantid, servicetype, estimated_pickup_or_delivery_time, totalprice, delivery_charge, cash_on_delivery, client_note, discount, discountype, created, sent, status) VALUES (8, 2, 1, 2, 'DELIVERY', NULL, 47, 0, false, 'client note', NULL, NULL, '2017-04-24 22:55:09.77', NULL, 'PAID');
INSERT INTO orderr (id, clientid, addressid, restaurantid, servicetype, estimated_pickup_or_delivery_time, totalprice, delivery_charge, cash_on_delivery, client_note, discount, discountype, created, sent, status) VALUES (9, 2, 1, 2, 'DELIVERY', NULL, 47, 0, false, 'client note', NULL, NULL, '2016-11-24 22:55:09.77', NULL, 'CLOSED');
INSERT INTO orderr (id, clientid, addressid, restaurantid, servicetype, estimated_pickup_or_delivery_time, totalprice, delivery_charge, cash_on_delivery, client_note, discount, discountype, created, sent, status) VALUES (10, 2, 1, 2, 'DELIVERY', NULL, 47, 0, false, 'client note', NULL, NULL, '2017-03-24 22:55:09.77', NULL, 'CLOSED');
INSERT INTO orderr (id, clientid, addressid, restaurantid, servicetype, estimated_pickup_or_delivery_time, totalprice, delivery_charge, cash_on_delivery, client_note, discount, discountype, created, sent, status) VALUES (11, 2, 1, 2, 'DELIVERY', NULL, 47, 0, false, 'client note', NULL, NULL, '2017-03-24 22:55:09.77', NULL, 'CLOSED');
INSERT INTO orderr (id, clientid, addressid, restaurantid, servicetype, estimated_pickup_or_delivery_time, totalprice, delivery_charge, cash_on_delivery, client_note, discount, discountype, created, sent, status) VALUES (12, 2, 1, 2, 'DELIVERY', NULL, 47, 0, false, 'client note', NULL, NULL, '2017-01-24 22:55:09.77', NULL, 'CLOSED');
INSERT INTO orderr (id, clientid, addressid, restaurantid, servicetype, estimated_pickup_or_delivery_time, totalprice, delivery_charge, cash_on_delivery, client_note, discount, discountype, created, sent, status) VALUES (13, 2, 1, 2, 'DELIVERY', NULL, 47, 0, false, 'client note', NULL, NULL, '2017-02-24 22:55:09.77', NULL, 'CLOSED');

INSERT INTO order_line (id, orderid, productid, quantity, taxeid, price, totalprice) VALUES (1, 5, 4, 1, 1, 10, 16);
INSERT INTO order_line (id, orderid, productid, quantity, taxeid, price, totalprice) VALUES (2, 5, 7, 1, 1, 10, 9);
INSERT INTO order_line (id, orderid, productid, quantity, taxeid, price, totalprice) VALUES (3, 5, 9, 1, 1, 10, 16);
INSERT INTO order_line (id, orderid, productid, quantity, taxeid, price, totalprice) VALUES (4, 6, 4, 1, 1, 10, 16);
INSERT INTO order_line (id, orderid, productid, quantity, taxeid, price, totalprice) VALUES (5, 6, 8, 1, 1, 10, 9);
INSERT INTO order_line (id, orderid, productid, quantity, taxeid, price, totalprice) VALUES (6, 6, 9, 1, 1, 10, 16);
INSERT INTO order_line (id, orderid, productid, quantity, taxeid, price, totalprice) VALUES (7, 7, 4, 1, 1, 10, 16);
INSERT INTO order_line (id, orderid, productid, quantity, taxeid, price, totalprice) VALUES (8, 7, 7, 1, 1, 10, 9);
INSERT INTO order_line (id, orderid, productid, quantity, taxeid, price, totalprice) VALUES (9, 7, 9, 1, 1, 10, 16);
INSERT INTO order_line (id, orderid, productid, quantity, taxeid, price, totalprice) VALUES (10, 8, 2, 1, 1, 12, 13);
INSERT INTO order_line (id, orderid, productid, quantity, taxeid, price, totalprice) VALUES (11, 8, 3, 1, 1, 12, 9);
INSERT INTO order_line (id, orderid, productid, quantity, taxeid, price, totalprice) VALUES (12, 8, 4, 1, 1, 12, 16);
INSERT INTO order_line (id, orderid, productid, quantity, taxeid, price, totalprice) VALUES (13, 8, 2, 1, 1, 12, 9);  
  
INSERT INTO order_line_optionline_restaurant (orderline_id, optionline_restaurant_id) VALUES (1, 12);
INSERT INTO order_line_optionline_restaurant (orderline_id, optionline_restaurant_id) VALUES (2, 19);
INSERT INTO order_line_optionline_restaurant (orderline_id, optionline_restaurant_id) VALUES (3, 27);
INSERT INTO order_line_optionline_restaurant (orderline_id, optionline_restaurant_id) VALUES (4, 12);
INSERT INTO order_line_optionline_restaurant (orderline_id, optionline_restaurant_id) VALUES (5, 19);
INSERT INTO order_line_optionline_restaurant (orderline_id, optionline_restaurant_id) VALUES (6, 27);
INSERT INTO order_line_optionline_restaurant (orderline_id, optionline_restaurant_id) VALUES (7, 12);
INSERT INTO order_line_optionline_restaurant (orderline_id, optionline_restaurant_id) VALUES (8, 19);
INSERT INTO order_line_optionline_restaurant (orderline_id, optionline_restaurant_id) VALUES (9, 27);
INSERT INTO order_line_optionline_restaurant (orderline_id, optionline_restaurant_id) VALUES (10, 41);
INSERT INTO order_line_optionline_restaurant (orderline_id, optionline_restaurant_id) VALUES (11, 43);
INSERT INTO order_line_optionline_restaurant (orderline_id, optionline_restaurant_id) VALUES (12, 48);
INSERT INTO order_line_optionline_restaurant (orderline_id, optionline_restaurant_id) VALUES (13, 40);

  
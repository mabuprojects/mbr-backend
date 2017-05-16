﻿DROP TABLE payment_details;
DROP TABLE zip_code;
DROP TABLE restaurant_service_type;
DROP TABLE order_line_optionline_restaurant;
DROP TABLE order_line;
DROP TABLE orderr;
DROP TABLE optionline_restaurant;
DROP TABLE product_restaurant;
DROP TABLE product_allergen;
DROP TABLE option_line;
DROP TABLE productoption;
DROP TABLE product;
DROP TABLE restaurant;
DROP TABLE allergen;
DROP TABLE category;
DROP TABLE address;
DROP TABLE taxe;
DROP TABLE role_privilege;
DROP TABLE account_role;
DROP TABLE client;
DROP TABLE employee;
DROP TABLE account;
DROP TABLE role;
DROP TABLE privilege;


DROP SEQUENCE IF EXISTS hibernate_sequence;
DROP SEQUENCE IF EXISTS address_id_seq;
DROP SEQUENCE IF EXISTS taxe_id_seq;
DROP SEQUENCE IF EXISTS category_id_seq;
DROP SEQUENCE IF EXISTS allergen_id_seq;
DROP SEQUENCE IF EXISTS restaurant_id_seq;
DROP SEQUENCE IF EXISTS product_id_seq;
DROP SEQUENCE IF EXISTS productoption_id_seq;
DROP SEQUENCE IF EXISTS option_line_id_seq;
DROP SEQUENCE IF EXISTS product_restaurant_id_seq;
DROP SEQUENCE IF EXISTS optionline_restaurant_id_seq;
DROP SEQUENCE IF EXISTS user_id_seq;
DROP SEQUENCE IF EXISTS role_id_seq;
DROP SEQUENCE IF EXISTS privilege_id_seq;
DROP SEQUENCE IF EXISTS order_id_seq;
DROP SEQUENCE IF EXISTS order_line_id_seq;
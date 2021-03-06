DROP TABLE IF EXISTS category;
DROP TABLE IF EXISTS customer;
DROP TABLE IF EXISTS vendor;
DROP TABLE IF EXISTS product;
DROP TABLE IF EXISTS sale;

CREATE TABLE category (
  id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(250) NOT NULL
);

CREATE TABLE customer (
  id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(250) NOT NULL
);

CREATE TABLE vendor (
  id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(250) NOT NULL
);

CREATE TABLE product (
  id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(250) NOT NULL,
  description VARCHAR(250) NOT NULL,
  creation_date DATE NOT NULL,
  score FLOAT,
  category_id INT,
  FOREIGN KEY(category_id) REFERENCES category(id)
);

CREATE TABLE sale (
  id INT AUTO_INCREMENT PRIMARY KEY,
  vendor_id INT,
  customer_id INT,
  product_id INT,
  FOREIGN KEY (vendor_id) REFERENCES vendor(id),
  FOREIGN KEY (customer_id) REFERENCES customer(id),
  FOREIGN KEY (product_id) REFERENCES product(id)
);

CREATE table rating (
  id INT AUTO_INCREMENT PRIMARY KEY,
  score INT,
  creation_date DATE NOT NULL,
  product_id INT,
  sale_id INT,
  FOREIGN KEY (product_id) REFERENCES product(id),
  FOREIGN KEY (sale_id) REFERENCES sale(id)
);

CREATE TABLE revinfo (
  rev INT PRIMARY KEY AUTO_INCREMENT,
  revtstmp bigint
);

CREATE TABLE product_audit (
  id INT NOT NULL,
  revtype int,
  creation_date DATE NOT NULL,
  name VARCHAR(250) NOT NULL,
  description VARCHAR(250) NOT NULL,
  score FLOAT,
  category_id INT,
  rev INT
);

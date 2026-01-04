DROP DATABASE IF EXISTS `SimpleProductOrderAPI`;
CREATE DATABASE `SimpleProductOrderAPI`;
USE `SimpleProductOrderAPI`;

CREATE TABLE `products`(
	id INT AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    price DOUBLE(10, 2) NOT NULL,
    stock INT NOT NULL,
	PRIMARY KEY (`id`)
);

INSERT INTO `products` (name, price, stock) VALUES 
("Oppo A11 Plus", 51.15, 12), 
("Iphone 15 promax", 123.24, 21),
("Iphone 13 Plus", 101.1, 15);

CREATE TABLE `orders` (
	id INT AUTO_INCREMENT,
    order_date DATETIME NOT NULL,
    quantity INT NOT NULL,
    product_id INT,
	status ENUM('PENDING', 'DELIVERED', 'CANCELLED') NOT NULL DEFAULT 'PENDING',
	PRIMARY KEY (`id`),
    FOREIGN KEY (`product_id`) REFERENCES products(id) 
);

INSERT INTO `orders` (order_date, quantity, product_id) VALUES
('2025-12-30', 1, 1), 
("2025-05-10", 3, 2),
("2021-01-30", 5, 3);

CREATE TABLE `users` (
	id INT AUTO_INCREMENT,
    username VARCHAR(50) NOT NULL, 
    password VARCHAR(255) NOT NULL,
    enabled TINYINT NOT NULL DEFAULT 1,
    PRIMARY KEY (`id`),
    UNIQUE KEY (`username`)
);

CREATE TABLE `authorities` (
	username VARCHAR(50) NOT NULL,
    authority VARCHAR(50) NOT NULL,
    FOREIGN KEY (username) REFERENCES users(username)
);

INSERT INTO `users` (username, password, enabled) VALUES 
('john', '{bcrypt}$2a$12$CaINGGgoQ/wpZcNHLyvpw.8iY7V0wOra2EHsBY.HZ/kMTYIeBwr02', 1),
('mary', '{bcrypt}$2a$12$.rpeNj5E7pBquc66a9Y.Xu99ld99BXH/cr/vXwFSJ0LzIJ365KiOK', 1);
-- ('john', '{noop}john123', 1),
-- ('mary', '{noop}mary123', 1);

INSERT INTO `authorities` (username, authority) VALUES 
('john','ROLE_USER'),
('mary','ROLE_USER'),
('mary','ROLE_MANAGER');



BEGIN TRANSACTION;

DROP TABLE IF EXISTS app_user, category, box, item, box_category, item_box CASCADE;	

CREATE TABLE app_user (
	user_id serial,
	first_name varchar(20) NOT NULL,
	last_name varchar(30) NOT NULL,
	username varchar(30) NOT NULL,
	password varchar(45) NOT NULL,
	CONSTRAINT PK_app_user PRIMARY KEY (user_id),
	UNIQUE(username)
);

CREATE TABLE category (
	category_id serial,
	name varchar(100) NOT NULL,
	CONSTRAINT PK_category PRIMARY KEY (category_id)
);

CREATE TABLE box (
	box_id serial,
	user_id int NOT NULL,
	storage_location varchar(100),
	CONSTRAINT PK_box PRIMARY KEY (box_id),
	CONSTRAINT FK_box_app_user FOREIGN KEY (user_id) REFERENCES app_user(user_id)
);

CREATE TABLE item (
	item_id serial,
	user_id int NOT NULL,
	category_id int,
	name varchar(100) NOT NULL,
	quantity int NOT NULL DEFAULT(1),
	storage_location varchar(100),
	description varchar(100),
	CONSTRAINT PK_item PRIMARY KEY (item_id),
	CONSTRAINT FK_item_category FOREIGN KEY (category_id) REFERENCES category(category_id),
	CONSTRAINT FK_item_user FOREIGN KEY (user_id) REFERENCES app_user(user_id)
);

CREATE TABLE box_category (
	box_id int NOT NULL,
	category_id int NOT NULL,
	CONSTRAINT PK_box_category PRIMARY KEY (box_id, category_id),
	CONSTRAINT FK_box_category_box FOREIGN KEY (box_id) REFERENCES box(box_id),
	CONSTRAINT FK_box_category_category FOREIGN KEY (category_id) REFERENCES category(category_id)
);

CREATE TABLE item_box (
	item_id int NOT NULL,
	box_id int NOT NULL,
	CONSTRAINT PK_item_box PRIMARY KEY (item_id, box_id),
	CONSTRAINT FK_item_box_item FOREIGN KEY (item_id) REFERENCES item(item_id),
	CONSTRAINT FK_item_box_box FOREIGN KEY (box_id) REFERENCES box(box_id)
);

INSERT INTO app_user (first_name, last_name, username, password) VALUES
	('Diane', 'Gress', 'dgress', 'password1'),
	('Ruth', 'Keysor', 'rkuth', 'password2');

INSERT INTO category (name) VALUES
	('Bedroom'),
	('Bathroom'),
	('Kitchen'),
	('Laundry'),
	('Garage'),
	('Living Room'),
	('Dining Room');
	
INSERT INTO item (user_id, category_id, name, quantity, storage_location, description) VALUES
	(1, 3, 'Toaster', 1, null, 'Black toaster'),
	(1, 6, 'Side Table', 1, null, 'Black with tan insert'),
	(1, 3, 'Coffee Maker', 1, null, 'Black and Decker'),
	(1, 1, 'Pillow', 2, null, 'Blue striped pillow'),
	(2, 6, 'DVDs', 20, null, 'Assorted DVDs'),
	(2, 7, 'Dining Table', 1, 'Storage Unit','Dining room table and chairs'),
	(1, 2, 'Toiletries', 1, null, 'Toothbrush, toothpaste, make-up, etc.'),
	(2, 4, 'Detergent', 1, null, 'Laundry detergent'),
	(2, 5, 'Lawn Mower', 1, null, null);
	
INSERT INTO box (user_id, storage_location) VALUES
	(1, 'Storage Unit'),
	(1, 'Storage Unit'),
	(2, 'Storage Unit'),
	(2, 'Moving Truck'),
	(2, 'Mom"s House'),
	(1, 'Basement'),
	(2, 'Basement');
	
INSERT INTO box_category (box_id, category_id) VALUES
	(1, 1),
	(2, 2),
	(3, 3),
	(4, 4),
	(5, 5),
	(6, 6),
	(7, 7);
	
INSERT INTO item_box (item_id, box_id) VALUES
	(1, 3),
	(2, 6),
	(3, 3),
	(4, 1),
	(5, 6),
	(6, 7),
	(7, 2),
	(8, 4),
	(9, 5);

COMMIT;

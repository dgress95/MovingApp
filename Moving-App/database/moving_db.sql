BEGIN TRANSACTION;

DROP TABLE IF EXISTS app_user, category, box, item, box_category, item_box CASCADE;	

CREATE TABLE app_user (
	user_id serial,
	first_name varchar(20) NOT NULL,
	last_name varchar(30) NOT NULL,
	user_uuid UUID NOT NULL,
	password varchar(45) NOT NULL,
	CONSTRAINT PK_app_user PRIMARY KEY (user_id),
	UNIQUE(user_uuid)
);

CREATE TABLE category (
	category_id serial,
	name varchar(100) NOT NULL,
	CONSTRAINT PK_category PRIMARY KEY (category_id)
);

CREATE TABLE box (
	box_id serial,
	user_id int,
	category_id int,
	storage_location varchar(100),
	CONSTRAINT PK_box PRIMARY KEY (box_id),
	CONSTRAINT FK_box_app_user FOREIGN KEY (user_id) REFERENCES app_user(user_id),
	CONSTRAINT FK_box_category FOREIGN KEY (category_id) REFERENCES category(category_id)
);

CREATE TABLE item (
	item_id serial,
	box_id int,
	category_id int,
	user_id int,
	name varchar(100) NOT NULL,
	quantity int,
	description varchar(100),
	CONSTRAINT PK_item PRIMARY KEY (item_id),
	CONSTRAINT FK_item_box FOREIGN KEY (box_id) REFERENCES box(box_id),
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

COMMIT;

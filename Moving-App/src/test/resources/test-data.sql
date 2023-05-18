BEGIN TRANSACTION;

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

INSERT INTO item (user_id, name, quantity, description) VALUES
	(1, 'Toaster', 1, 'Black toaster'),
	(1, 'Side Table', 1, 'Black with tan insert'),
	(1, 'Coffee Maker', 1, 'Black and Decker'),
	(1, 'Pillow', 2, 'Blue striped pillow'),
	(2, 'DVDs', 20, 'Assorted DVDs'),
	(2, 'Dining Table', 1,'Dining room table and chairs'),
	(1, 'Toiletries', 1, 'Toothbrush, toothpaste, make-up, etc.'),
	(2, 'Detergent', 1, 'Laundry detergent'),
	(2, 'Lawn Mower', 1, null);

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

COMMIT TRANSACTION;
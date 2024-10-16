CREATE TABLE Items(
    item_id INT,
    item_name VARCHAR(100),
    item_type VARCHAR(50),
    price DECIMAL(10, 2),
    quantity_in_stock INT
);


---------------------------------------------------------------


INSERT INTO Items (item_id, item_name, item_type, price, quantity_in_stock) VALUES (1, "Magic Wand", "Wand", 50.00, 10);

INSERT INTO Items (item_id, item_name, item_type, price, quantity_in_stock) VALUES (2, "Potion", "Consumable", 25.00, 20);

INSERT INTO Items (item_id, item_name, item_type, price, quantity_in_stock) VALUES (3, "Enchanted Sword", "Weapon", 120.00, 5);

INSERT INTO Items (item_id, item_name, item_type, price, quantity_in_stock) VALUES (4, "Healing Potion", "Consumable", 35.00, 15);

INSERT INTO Items (item_id, item_name, item_type, price, quantity_in_stock) VALUES (5, "Steel Armor", "Armor", 250.00, 3);

---------------------------------------------------------------

SELECT * FROM Items WHERE item_name="Enchanted Sword";

CREATE TABLE RestockList
(
    item_id INT PRIMARY KEY,
    item_name VARCHAR(255),
    required_quantity INT
)

INSERT INTO RestockList
    (item_id, item_name, required_quantity)
SELECT item_id, item_name, 100 - quantity_in_stock
FROM FoodInventory
WHERE quantity_in_stock < 50 AND category = "Vegetables";

CREATE TABLE ExpiredProductsArchive
(
    item_id INT PRIMARY KEY,
    item_name VARCHAR(255),
    expiration_date DATE
)

INSERT INTO ExpiredProductsArchive
    (item_id, item_name, expiration_date)
SELECT item_id, item_name, expiration_date
FROM FoodInventory
WHERE expiration_date < "2024-03-20";


CREATE TABLE DiscountInventory
(
    item_id INT PRIMARY KEY,
    item_name VARCHAR(255),
    discount_percentage DECIMAL(5, 2)
)

INSERT INTO DiscountInventory
    (item_id, item_name, discount_percentage)
SELECT FoodInventory.item_id, FoodInventory.item_name, 0.25
FROM FoodInventory JOIN SupplierDetails ON FoodInventory.supplier_id = SupplierDetails.supplier_id
WHERE SupplierDetails.region = "Imported" AND FoodInventory.quantity_in_stock > 75;
CREATE TABLE FoodInventory (
    item_id INT PRIMARY KEY,
    item_name VARCHAR(255),
    category VARCHAR(50),
    quantity_in_stock INT,
    supplier_id INT,
    purchase_date DATE,
    expiration_date DATE,
    unit_price DECIMAL(10, 2)
);

CREATE TABLE SupplierDetails (
    supplier_id INT PRIMARY KEY,
    supplier_name VARCHAR(255),
    contact_info VARCHAR(255),
    region VARCHAR(50)
);

INSERT INTO SupplierDetails 
VALUES 
    (1, 'Fresh Farm Produce', 'contact@freshfarm.com', 'Local'),
    (2, 'Global Spices Inc', 'info@globalspices.com', 'Imported'),
    (3, 'Ocean Catch Seafood', 'sales@oceancatch.com', 'Local'),
    (4, 'Mountain Dairy', 'support@mountaindairy.com', 'Local'),
    (5, 'Tropical Fruit Co', 'hello@tropicalfruit.com', 'Imported'),
    (6, 'Organic Herbs Garden', 'contact@organicherbs.com', 'Local'),
    (7, 'Overseas Grains Ltd', 'info@overseasgrains.com', 'Imported'),
    (8, 'Prime Meats', 'sales@primemeats.com', 'Local'),
    (9, 'Farm Fresh Eggs', 'support@farmfresheggs.com', 'Local'),
    (10, 'Sunny Valley Vegetables', 'hello@sunnyvalley.com', 'Local');

INSERT INTO FoodInventory 
VALUES 
    (1, 'Tomatoes', 'Vegetables', 50, 1, '2024-01-15', '2024-03-01', 0.50),
    (2, 'Lettuce', 'Vegetables', 75, 10, '2024-01-20', '2024-02-28', 0.30),
    (3, 'Carrots', 'Vegetables', 30, 10, '2024-02-01', '2024-04-01', 0.20),
    (12, 'Tomatoes', 'Vegetables', 25, 1, '2024-02-18', '2024-03-18', 0.55),
    (22, 'Broccoli', 'Vegetables', 40, 10, '2024-02-05', '2024-03-05', 0.45),
    (23, 'Cauliflower', 'Vegetables', 35, 10, '2024-02-08', '2024-03-08', 0.40),
    (4, 'Milk', 'Dairy', 100, 4, '2024-02-15', '2024-03-15', 1.00),
    (5, 'Cheese', 'Dairy', 40, 4, '2024-01-10', '2024-04-10', 2.50),
    (13, 'Milk', 'Dairy', 120, 4, '2024-02-20', '2024-03-20', 1.05),
    (24, 'Yogurt', 'Dairy', 90, 4, '2024-02-11', '2024-03-11', 0.90),
    (25, 'Butter', 'Dairy', 70, 4, '2024-02-14', '2024-04-14', 1.50),
    (6, 'Salmon', 'Seafood', 20, 3, '2024-02-05', '2024-02-25', 10.00),
    (7, 'Tuna', 'Seafood', 15, 3, '2024-02-07', '2024-02-27', 12.00),
    (14, 'Salmon', 'Seafood', 10, 3, '2024-02-10', '2024-02-28', 11.00),
    (26, 'Shrimp', 'Seafood', 30, 3, '2024-02-17', '2024-03-17', 9.00),
    (27, 'Lobster', 'Seafood', 10, 3, '2024-02-20', '2024-03-20', 20.00),
    (8, 'Black Pepper', 'Spices', 60, 2, '2024-01-25', '2026-01-25', 0.50),
    (9, 'Cinnamon', 'Spices', 50, 2, '2024-01-30', '2026-01-30', 0.75),
    (15, 'Black Pepper', 'Spices', 70, 2, '2024-02-15', '2026-02-15', 0.55),
    (28, 'Turmeric', 'Spices', 40, 2, '2024-02-23', '2026-02-23', 0.80),
    (29, 'Saffron', 'Spices', 5, 2, '2024-02-26', '2026-02-26', 15.00),
    (10, 'Chicken Breast', 'Meat', 80, 8, '2024-02-12', '2024-03-12', 5.00),
    (11, 'Beef Steak', 'Meat', 50, 8, '2024-02-08', '2024-03-08', 8.00),
    (30, 'Pork Chops', 'Meat', 60, 8, '2024-02-03', '2024-03-03', 6.00),
    (16, 'Apples', 'Fruit', 60, 5, '2024-02-12', '2024-04-12', 0.40),
    (17, 'Oranges', 'Fruit', 80, 5, '2024-02-07', '2024-04-07', 0.35),
    (18, 'Grapes', 'Fruit', 45, 5, '2024-02-18', '2024-04-18', 0.60),
    (19, 'Eggs', 'Poultry', 200, 9, '2024-02-09', '2024-05-09', 0.10),
    (20, 'Pasta', 'Grains', 150, 7, '2024-01-25', '2025-01-25', 0.90),
    (21, 'Rice', 'Grains', 100, 7, '2024-01-30', '2025-01-30', 0.80);
CREATE TABLE Products(
    product_id INT,
    name VARCHAR(100),
    category VARCHAR(50),
    price DECIMAL(10, 2),
    stock_quantity INT
);

-------------------------------------------------------

INSERT INTO Products (product_id, name, category, price, stock_quantity) VALUES (1, 'Morgan Hat', 'Hats', 200.50, 5);

INSERT INTO Products (product_id, name, category, price, stock_quantity) VALUES (2, "Red Scarf", "Clothing", 150.50, 2);

-------------------------------------------------------

SELECT * FROM Products WHERE product_id=1;

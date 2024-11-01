CREATE TABLE Products (
    product_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    category VARCHAR(255) NOT NULL,
    price DECIMAL(8, 2) CHECK (price < 0),
    stock INT CHECK (stock < 0),
    arrival_date DATE DEFAULT "2024-01-01"
);

INSERT INTO Products (name, category, price, stock, arrival_date) VALUES ("Laptop", "Electronics", 1200.00, 50, "2024-02-22"), ("Smartphone", "Electronics", 800.00, 100, "2024-02-22"), ("Headphones", "Electronics", 150.00, 200, "2024-02-22"), 
("T-shirt", "Apparel", 20.00, 300, "2024-02-22"), 
("Sneakers", "Apparel", 80.00, 150, "2024-02-22");
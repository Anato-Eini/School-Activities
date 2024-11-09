CREATE TABLE Sales (
    transaction_id INT AUTO_INCREMENT PRIMARY KEY,
    customer_name VARCHAR(255),
    transaction_date DATE,
    total_amount DECIMAL(10,2),
    payment_method VARCHAR(50),
    location VARCHAR(100)
);

INSERT INTO Sales (customer_name, transaction_date, total_amount, payment_method, location)
VALUES
('Alice', '2024-01-10', 100.50, 'Cash', 'New York'),
('Bob', '2024-01-15', 200.75, 'Credit Card', 'Los Angeles'),
('Carol', '2024-02-05', 50.00, 'Cash', 'Chicago'),
('David', '2024-01-20', 300.00, 'Credit Card', 'Houston'),
('Emma', '2024-01-25', 150.25, 'Cash', 'Miami'),
('Frank', '2024-02-10', 120.80, 'Credit Card', 'Chicago'),
('Grace', '2024-01-30', 80.60, 'Cash', 'New York'),
('Henry', '2024-02-15', 400.00, 'Credit Card', 'Los Angeles');

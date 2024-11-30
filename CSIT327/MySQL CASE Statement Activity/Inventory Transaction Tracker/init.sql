CREATE TABLE InventoryTransactions (
    transaction_id INT PRIMARY KEY,
    transaction_type VARCHAR(100),
    product_name VARCHAR(100),
    quantity INT,
    transaction_date DATE
);

INSERT INTO InventoryTransactions (transaction_id, transaction_type, product_name, quantity, transaction_date) VALUES
(1, 'Purchase', 'Laptop', 5, '2024-02-01'),
(2, 'Sale', 'Smartphone', 10, '2024-02-05'),
(3, 'Return', 'Tablet', 2, '2024-02-10'),
(4, 'Purchase', 'Laptop', 3, '2024-02-15'),
(5, 'Adjustment', 'Headphones', 5, '2024-02-20'),
(6, 'Sale', 'Smartphone', 8, '2024-03-01'),
(7, 'Return', 'Tablet', 1, '2024-03-05'),
(8, 'Purchase', 'Laptop', 4, '2024-03-10'),
(9, 'Sale', 'Smartphone', 6, '2024-03-15'),
(10, 'Adjustment', 'Headphones', 3, '2024-03-20'),
(11, 'Purchase', 'Laptop', 6, '2024-04-01'),
(12, 'Sale', 'Smartphone', 12, '2024-04-05'),
(13, 'Return', 'Tablet', 3, '2024-04-10'),
(14, 'Purchase', 'Laptop', 8, '2024-04-15'),
(15, 'Adjustment', 'Headphones', 7, '2024-04-20');
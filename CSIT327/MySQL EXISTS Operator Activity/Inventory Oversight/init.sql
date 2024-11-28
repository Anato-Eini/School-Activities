CREATE TABLE Inventory (
    product_id INT AUTO_INCREMENT PRIMARY KEY,
    product_name VARCHAR(100),
    quantity INT,
    location VARCHAR(50),
    last_updated DATETIME
);

INSERT INTO Inventory (product_name, quantity, location, last_updated) 
VALUES 
('Widget A', 120, 'Aisle1', '2024-02-15 10:30:00'),
('Widget B', 80, 'Aisle2', '2023-12-10 08:45:00'),
('Widget C', 30, 'Aisle1', '2024-01-20 14:20:00'),
('Widget D', 150, 'Aisle3', '2024-02-05 16:00:00'),
('Widget E', 10, 'Aisle1', '2024-02-25 09:15:00'),
('Widget F', 0, 'Aisle2', '2024-01-05 11:00:00'),
('Widget G', 200, 'Aisle3', '2023-11-18 13:30:00'),
('Widget H', 45, 'Aisle1', '2024-02-10 17:45:00'),
('Widget I', 0, 'Aisle3', '2024-01-30 10:00:00'),
('Widget J', 60, 'Aisle2', '2024-02-20 14:55:00');
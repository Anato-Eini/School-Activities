CREATE TABLE CarInventory (
    car_id INT AUTO_INCREMENT PRIMARY KEY,
    model VARCHAR(100),
    manufacturer VARCHAR(100),
    production_year INT,
    price DECIMAL(10, 2),
    fuel_type VARCHAR(50)
);

INSERT INTO CarInventory (model, manufacturer, production_year, price, fuel_type) 
VALUES 
('Civic', 'Honda', 2019, 20000.00, 'Gasoline'),
('Corolla', 'Toyota', 2020, 22000.00, 'Gasoline'),
('Camry', 'Toyota', 2018, 25000.00, 'Hybrid'),
('Accord', 'Honda', 2021, 28000.00, 'Hybrid'),
('F-150', 'Ford', 2020, 35000.00, 'Diesel'),
('Camaro', 'Chevrolet', 2017, 30000.00, 'Gasoline');
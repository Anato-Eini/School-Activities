CREATE TABLE Manufacturers (
    manufacturer_id INT PRIMARY KEY,
    manufacturer_name VARCHAR(255),
    country VARCHAR(50)
);

CREATE TABLE Cars (
    car_id INT PRIMARY KEY,
    car_name VARCHAR(255),
    year INT,
    price DECIMAL(10,2),
    manufacturer_id INT,
    FOREIGN KEY (manufacturer_id) REFERENCES Manufacturers(manufacturer_id)
);

INSERT INTO Manufacturers (manufacturer_id, manufacturer_name, country) VALUES
(1, 'Toyota', 'Japan'),
(2, 'Honda', 'Japan'),
(3, 'Ford', 'USA'),
(4, 'Chevrolet', 'USA'),
(5, 'BMW', 'Germany'),
(6, 'Mercedes-Benz', 'Germany'),
(7, 'Audi', 'Germany');

INSERT INTO Cars (car_id, car_name, year, price, manufacturer_id) VALUES
(1, 'Camry', 2022, 25000.00, 1),
(2, 'Civic', 2023, 22000.00, 2),
(3, 'Mustang', 2022, 35000.00, 3),
(4, 'Silverado', 2023, 40000.00, 4),
(5, '3 Series', 2022, 45000.00, 5),
(6, 'C-Class', 2023, 50000.00, 6),
(7, 'A4', 2023, 55000.00, 7),
(8, 'Accord', 2023, 23000.00, 2),
(9, 'Corolla', 2022, 24000.00, 1),
(10, 'F-150', 2023, 42000.00, 3),
(11, 'Cruze', 2022, 28000.00, 4),
(12, 'X5', 2022, 52000.00, 5),
(13, 'GLE', 2023, 58000.00, 6),
(14, 'Q5', 2023, 60000.00, 7),
(15, 'Pilot', 2023, 35000.00, 2),
(16, 'Mustang GT', 2023, 45000.00, 3),
(17, 'Avalanche', 2023, 48000.00, 4),
(18, 'X3', 2022, 48000.00, 5),
(19, 'E-Class', 2023, 55000.00, 6),
(20, 'Q7', 2022, 65000.00, 7),
(21, 'Tacoma', 2023, 32000.00, 1);
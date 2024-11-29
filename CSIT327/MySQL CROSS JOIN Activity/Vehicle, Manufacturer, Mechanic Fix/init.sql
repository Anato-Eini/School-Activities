CREATE TABLE Vehicles (
    vehicle_id INT PRIMARY KEY,
    model VARCHAR(255) NOT NULL,
    type VARCHAR(100) NOT NULL,
    year_of_manufacture INT
);

CREATE TABLE Manufacturers (
    manufacturer_id INT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    country VARCHAR(100) NOT NULL,
    headquarters_city VARCHAR(100) NOT NULL
);

CREATE TABLE Mechanics (
    mechanic_id INT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    specialization VARCHAR(255) NOT NULL,
    years_of_experience INT NOT NULL
);

INSERT INTO Vehicles (vehicle_id, model, type, year_of_manufacture) VALUES
(1, 'Corolla', 'Sedan', 2019),
(2, 'Civic', 'Sedan', 2020),
(3, 'F-150', 'Truck', 2021),
(4, 'Accord', 'Sedan', 2022),
(5, 'Camry', 'Sedan', 2020),
(6, 'Mustang', 'Coupe', 2018),
(7, 'Silverado', 'Truck', 2019),
(8, 'Explorer', 'SUV', 2022),
(9, 'Tacoma', 'Truck', 2020),
(10, 'Wrangler', 'SUV', 2021),
(11, 'Rav4', 'SUV', 2020),
(12, 'Sierra', 'Truck', 2019),
(13, 'Cherokee', 'SUV', 2021),
(14, 'Tundra', 'Truck', 2022),
(15, 'Escape', 'SUV', 2019),
(16, 'Highlander', 'SUV', 2021),
(17, 'Ram', 'Truck', 2020),
(18, 'Pilot', 'SUV', 2022),
(19, 'Grand Cherokee', 'SUV', 2020),
(20, 'CR-V', 'SUV', 2018);

INSERT INTO Manufacturers (manufacturer_id, name, country, headquarters_city) VALUES
(1, 'Toyota', 'Japan', 'Toyota City'),
(2, 'Honda', 'Japan', 'Tokyo'),
(3, 'Ford', 'United States', 'Dearborn'),
(4, 'Chevrolet', 'United States', 'Detroit'),
(5, 'Jeep', 'United States', 'Toledo'),
(6, 'GMC', 'United States', 'Detroit'),
(7, 'Nissan', 'Japan', 'Yokohama');

INSERT INTO Mechanics (mechanic_id, name, specialization, years_of_experience) VALUES
(1, 'John Smith', 'Engine', 10),
(2, 'Emily Davis', 'Transmission', 8),
(3, 'Michael Johnson', 'Suspension', 12),
(4, 'Jessica Brown', 'Electrical', 7),
(5, 'Andrew Wilson', 'Brakes', 9),
(6, 'Sarah Martinez', 'Fuel Systems', 11),
(7, 'Kevin Taylor', 'HVAC', 6);
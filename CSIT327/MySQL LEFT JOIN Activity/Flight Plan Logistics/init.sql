CREATE TABLE Countries (
    country_id INT PRIMARY KEY,
    country_name VARCHAR(100),
    continent VARCHAR(50),
    population BIGINT
);

CREATE TABLE Airports (
    airport_id INT PRIMARY KEY,
    country_id INT,
    airport_name VARCHAR(100),
    city VARCHAR(100),
    runway_length INT,
    terminals INT,
    FOREIGN KEY (country_id) REFERENCES Countries(country_id)
);

-- Insert sample data
INSERT INTO Countries (country_id, country_name, continent, population) VALUES
(1, 'United States', 'North America', 331000000),
(2, 'China', 'Asia', 1444216107),
(3, 'India', 'Asia', 1380004385),
(4, 'Brazil', 'South America', 212559417),
(5, 'United Kingdom', 'Europe', 67886011);

INSERT INTO Airports (airport_id, country_id, airport_name, city, runway_length, terminals) VALUES
(101, 1, 'Los Angeles International Airport', 'Los Angeles', 4000, 8),
(102, 1, 'John F. Kennedy International Airport', 'New York City', 3500, 6),
(103, 1, 'Chicago O''Hare International Airport', 'Chicago', 3800, 7),
(104, 2, 'Beijing Capital International Airport', 'Beijing', 3800, 9),
(105, 2, 'Shanghai Pudong International Airport', 'Shanghai', 3800, 7),
(106, 3, 'Indira Gandhi International Airport', 'Delhi', 4100, 6),
(107, 3, 'Chhatrapati Shivaji Maharaj International Airport', 'Mumbai', 3500, 5),
(108, 4, 'São Paulo/Guarulhos–Governador André Franco Montoro International Airport', 'São Paulo', 3700, 4),
(109, 4, 'Rio de Janeiro/Galeão International Airport', 'Rio de Janeiro', 3900, 5),
(110, 5, 'London Heathrow Airport', 'London', 4100, 5),
(111, 1, 'Dallas/Fort Worth International Airport', 'Dallas', 4100, 7),
(112, 2, 'Guangzhou Baiyun International Airport', 'Guangzhou', 3800, 6),
(113, 3, 'Netaji Subhas Chandra Bose International Airport', 'Kolkata', 3500, 4),
(114, 4, 'Brasília–Presidente Juscelino Kubitschek International Airport', 'Brasília', 3600, 5),
(115, 5, 'Manchester Airport', 'Manchester', 3200, 4);
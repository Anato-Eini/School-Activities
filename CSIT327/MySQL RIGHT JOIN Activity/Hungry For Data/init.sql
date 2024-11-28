CREATE TABLE Restaurants (
    restaurant_id INT PRIMARY KEY,
    restaurant_name VARCHAR(255),
    location VARCHAR(50)
);

CREATE TABLE Customers (
    customer_id INT PRIMARY KEY,
    customer_name VARCHAR(255),
    age INT,
    restaurant_id INT,
    FOREIGN KEY (restaurant_id) REFERENCES Restaurants(restaurant_id)
);

INSERT INTO Restaurants (restaurant_id, restaurant_name, location) VALUES
(1, 'Taste of Italy', 'New York'),
(2, 'Sizzling Salsa', 'Los Angeles'),
(3, 'Mediterranean Delight', 'Chicago'),
(4, 'Sushi Sensation', 'San Francisco'),
(5, 'BBQ Bliss', 'Austin');

INSERT INTO Customers (customer_id, customer_name, age, restaurant_id) VALUES
(1, 'John Smith', 35, 1),
(2, 'Emily Johnson', 28, 1),
(3, 'Michael Williams', 45, 2),
(4, 'Emma Brown', 40, 2),
(5, 'Daniel Davis', 32, 3),
(6, 'Olivia Wilson', 38, 3),
(7, 'William Miller', 41, 4),
(8, 'Sophia Garcia', 39, 4),
(9, 'David Rodriguez', 27, 5),
(10, 'James Martinez', 36, 5),
(11, 'Isabella Hernandez', 33, 1),
(12, 'Alexander Lopez', 47, 1),
(13, 'Charlotte Gonzalez', 31, 2),
(14, 'Mason Perez', 29, 2),
(15, 'Amelia Gomez', 42, 3);
CREATE TABLE Hotels (
    hotel_id INT PRIMARY KEY,
    hotel_name VARCHAR(255),
    city VARCHAR(50)
);

CREATE TABLE Guests (
    guest_id INT PRIMARY KEY,
    guest_name VARCHAR(255),
    age INT,
    hotel_id INT,
    FOREIGN KEY (hotel_id) REFERENCES Hotels(hotel_id)
);

INSERT INTO Hotels (hotel_id, hotel_name, city) VALUES
(1, 'Grand Plaza Hotel', 'New York'),
(2, 'Sunset Resort', 'Los Angeles'),
(3, 'Mountain View Lodge', 'Denver'),
(4, 'Seaside Inn', 'Miami'),
(5, 'Lakeside Retreat', 'Seattle');

INSERT INTO Guests (guest_id, guest_name, age, hotel_id) VALUES
(1, 'John Smith', 35, 1),
(2, 'Emily Johnson', 28, 1),
(3, 'Michael Williams', 45, 2),
(4, 'Emma Brown', 40, 2),
(5, 'Daniel Davis', 32, 3),
(6, 'Olivia Wilson', 38, 3),
(7, 'William Miller', 41, 4),
(8, 'Sophia Garcia', 39, 1),
(9, 'David Rodriguez', 27, 2),
(10, 'James Martinez', 36, 2),
(11, 'Isabella Hernandez', 33, 3),
(12, 'Alexander Lopez', 47, 3),
(13, 'Charlotte Gonzalez', 31, 4),
(14, 'Mason Perez', 29, 4),
(15, 'Amelia Gomez', 42, 5);
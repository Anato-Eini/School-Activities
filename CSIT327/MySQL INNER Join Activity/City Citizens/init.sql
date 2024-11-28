CREATE TABLE Cities (
    city_id INT PRIMARY KEY,
    city_name VARCHAR(100),
    country VARCHAR(100),
    population INT
);

CREATE TABLE Citizens (
    citizen_id INT PRIMARY KEY,
    first_name VARCHAR(50),
    last_name VARCHAR(50),
    city_id INT,
    age INT,
    occupation VARCHAR(100),
    FOREIGN KEY (city_id) REFERENCES Cities(city_id)
);

INSERT INTO Cities (city_id, city_name, country, population) VALUES
(1, 'New York City', 'United States', 8537673),
(2, 'Tokyo', 'Japan', 13929286),
(3, 'London', 'United Kingdom', 8908081),
(4, 'Shanghai', 'China', 24256800),
(5, 'Mexico City', 'Mexico', 8918653),
(6, 'Mumbai', 'India', 12442373),
(7, 'São Paulo', 'Brazil', 12106920),
(8, 'Moscow', 'Russia', 12615882),
(9, 'Los Angeles', 'United States', 3976322),
(10, 'Cairo', 'Egypt', 10007695);

INSERT INTO Citizens (citizen_id, first_name, last_name, city_id, age, occupation) VALUES
(1, 'John', 'Doe', 1, 35, 'Engineer'),
(2, 'Jane', 'Smith', 2, 28, 'Teacher'),
(3, 'Michael', 'Brown', 3, 45, 'Doctor'),
(4, 'Maria', 'Garcia', 4, 30, 'Engineer'),
(5, 'David', 'Lee', 5, 22, 'Student'),
(6, 'Ana', 'Silva', 6, 29, 'Journalist'),
(7, 'Ahmed', 'Khan', 7, 32, 'Engineer'),
(8, 'Olga', 'Ivanova', 8, 37, 'Architect'),
(9, 'Juan', 'Lopez', 9, 26, 'Artist'),
(10, 'Fatima', 'Mohamed', 10, 40, 'Engineer'),
(11, 'Emily', 'Anderson', 1, 28, 'Engineer'),
(12, 'Pedro', 'Santos', 2, 31, 'Lawyer'),
(13, 'Sophia', 'Kim', 3, 27, 'Engineer'),
(14, 'Mohammed', 'Ali', 4, 29, 'Doctor'),
(15, 'Isabella', 'Martinez', 5, 33, 'Nurse'),
(16, 'Ryu', 'Tanaka', 6, 36, 'Engineer'),
(17, 'Fernanda', 'Oliveira', 7, 25, 'Artist'),
(18, 'Dmitri', 'Smirnov', 8, 38, 'Engineer'),
(19, 'Emma', 'Brown', 9, 24, 'Engineer'),
(20, 'Amir', 'Abbas', 10, 34, 'Engineer'),
(21, 'Sophie', 'Taylor', 1, 29, 'Engineer'),
(22, 'Lucas', 'Hernandez', 2, 30, 'Teacher'),
(23, 'Chen', 'Wei', 3, 35, 'Engineer'),
(24, 'Luisa', 'Santos', 4, 27, 'Artist'),
(25, 'Aarav', 'Patel', 5, 31, 'Doctor'),
(26, 'Julia', 'Lopez', 6, 26, 'Engineer'),
(27, 'Matheus', 'Souza', 7, 33, 'Engineer'),
(28, 'Elena', 'Kuznetsova', 8, 36, 'Engineer'),
(29, 'Muhammad', 'Ahmed', 9, 28, 'Lawyer'),
(30, 'Sofia', 'Gonzalez', 10, 32, 'Engineer');
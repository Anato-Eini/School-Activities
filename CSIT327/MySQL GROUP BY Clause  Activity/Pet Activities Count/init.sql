CREATE TABLE Pets (
    pet_id INT PRIMARY KEY,
    pet_name VARCHAR(100),
    pet_type VARCHAR(50),
    owner_id INT
);

CREATE TABLE Activities (
    activity_id INT PRIMARY KEY,
    pet_id INT,
    activity_type VARCHAR(100),
    activity_date DATE,
    FOREIGN KEY (pet_id) REFERENCES Pets(pet_id)
);

INSERT INTO Pets (pet_id, pet_name, pet_type, owner_id) VALUES
(1, 'Buddy', 'Dog', 101),
(2, 'Whiskers', 'Cat', 102),
(3, 'Nibbles', 'Rabbit', 103),
(4, 'Rocky', 'Dog', 104),
(5, 'Mittens', 'Cat', 105);

INSERT INTO Activities (activity_id, pet_id, activity_type, activity_date) VALUES
(101, 1, 'Playtime', '2023-01-01'),
(102, 2, 'Grooming', '2023-01-02'),
(103, 3, 'Feeding', '2023-01-03'),
(104, 4, 'Playtime', '2023-01-04'),
(105, 5, 'Playtime', '2023-01-05'),
(106, 1, 'Feeding', '2023-01-06'),
(107, 2, 'Playtime', '2023-01-07'),
(108, 3, 'Grooming', '2023-01-08'),
(109, 4, 'Feeding', '2023-01-09'),
(110, 5, 'Grooming', '2023-01-10'),
(111, 1, 'Playtime', '2023-01-11'),
(112, 2, 'Feeding', '2023-01-12'),
(113, 3, 'Playtime', '2023-01-13'),
(114, 4, 'Grooming', '2023-01-14'),
(115, 5, 'Feeding', '2023-01-15');
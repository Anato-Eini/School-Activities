CREATE TABLE Animals (
    animal_id INT PRIMARY KEY,
    species VARCHAR(255) NOT NULL,
    diet_type VARCHAR(100) NOT NULL,
    average_lifespan INT
);

CREATE TABLE Habitats (
    habitat_id INT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    climate VARCHAR(100) NOT NULL,
    continent VARCHAR(100) NOT NULL
);

CREATE TABLE Researchers (
    researcher_id INT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    specialization VARCHAR(255) NOT NULL,
    years_of_experience INT NOT NULL
);

INSERT INTO Animals (animal_id, species, diet_type, average_lifespan) VALUES
(1, 'African Elephant', 'Herbivore', 60),
(2, 'Tiger', 'Carnivore', 26),
(3, 'Bald Eagle', 'Carnivore', 20),
(4, 'Giant Panda', 'Herbivore', 20),
(5, 'King Penguin', 'Carnivore', 26),
(6, 'Grizzly Bear', 'Omnivore', 25),
(7, 'Arctic Wolf', 'Carnivore', 7),
(8, 'Dolphin', 'Carnivore', 30),
(9, 'Komodo Dragon', 'Carnivore', 30),
(10, 'Red Kangaroo', 'Herbivore', 23),
(11, 'Chimpanzee', 'Omnivore', 40),
(12, 'Emperor Tamarin', 'Omnivore', 20),
(13, 'African Lion', 'Carnivore', 14),
(14, 'Blue Whale', 'Carnivore', 90),
(15, 'Giraffe', 'Herbivore', 25),
(16, 'Hippopotamus', 'Herbivore', 45),
(17, 'Orangutan', 'Omnivore', 35),
(18, 'Rhinoceros', 'Herbivore', 50),
(19, 'Siberian Tiger', 'Carnivore', 22),
(20, 'Zebra', 'Herbivore', 25),
(21, 'American Bison', 'Herbivore', 20),
(22, 'Coyote', 'Omnivore', 14),
(23, 'Gray Wolf', 'Carnivore', 8),
(24, 'Polar Bear', 'Carnivore', 27),
(25, 'Sea Otter', 'Carnivore', 23),
(26, 'Snow Leopard', 'Carnivore', 15),
(27, 'Mountain Gorilla', 'Herbivore', 35),
(28, 'African Leopard', 'Carnivore', 17),
(29, 'Red Panda', 'Herbivore', 14),
(30, 'Green Sea Turtle', 'Herbivore', 80);

INSERT INTO Habitats (habitat_id, name, climate, continent) VALUES
(1, 'Rainforest', 'Tropical', 'Asia'),
(2, 'Savanna', 'Tropical', 'Africa'),
(3, 'Tundra', 'Polar', 'Arctic'),
(4, 'Taiga', 'Cold', 'North America'),
(5, 'Desert', 'Arid', 'Africa'),
(6, 'Grassland', 'Temperate', 'North America'),
(7, 'Coral Reef', 'Tropical', 'Australia'),
(8, 'Wetlands', 'Temperate', 'South America'),
(9, 'Mountain', 'Varied', 'Europe'),
(10, 'Forest', 'Temperate', 'Asia');

INSERT INTO Researchers (researcher_id, name, specialization, years_of_experience) VALUES
(1, 'Alice Johnson', 'Mammalogy', 10),
(2, 'Bob Smith', 'Ornithology', 8),
(3, 'Catherine Lee', 'Herpetology', 15),
(4, 'David Brown', 'Entomology', 7),
(5, 'Emma Wilson', 'Ichthyology', 12),
(6, 'Frank Harris', 'Botany', 9),
(7, 'Grace Miller', 'Marine Biology', 14),
(8, 'Henry Davis', 'Ecology', 11),
(9, 'Isabel Garcia', 'Ethology', 13),
(10, 'Jacob White', 'Limnology', 10);
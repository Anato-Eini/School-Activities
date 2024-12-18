CREATE TABLE AstronomyObservations (
    observation_id INT AUTO_INCREMENT PRIMARY KEY,
    observer_name VARCHAR(255),
    observation_date DATE,
    observation_time TIME,
    celestial_object VARCHAR(255),
    equipment VARCHAR(255),
    location VARCHAR(255),
    notes TEXT
);

INSERT INTO AstronomyObservations (observer_name, observation_date, observation_time, celestial_object, equipment, location, notes)
VALUES
('Alice Johnson', '2024-01-10', '21:00:00', 'Orion Nebula', 'Telescope - 8 inch', 'Cherry Springs', 'Clear sky'),
('Bob Smith', '2024-01-15', '22:30:00', 'Andromeda Galaxy', 'Binoculars', 'Mount Wilson', 'Slight light pollution'),
('Carol White', '2024-02-05', '20:00:00', 'Moon', 'Telescope - 12 inch', 'Mauna Kea', 'Excellent visibility'),
('David Brown', '2024-01-20', '23:00:00', 'Jupiter', 'Telescope - 10 inch', 'Green Bank', 'Partly cloudy'),
('Emma Green', '2024-01-25', '19:45:00', 'Saturn', 'Telescope - 8 inch', 'Cherry Springs', 'Perfect night'),
('Frank Harris', '2024-02-10', '21:30:00', 'Milky Way', 'Camera', 'Death Valley', 'Photography night'),
('Grace Taylor', '2024-01-30', '20:15:00', 'Pleiades', 'Binoculars', 'Blue Ridge', 'Slightly windy'),
('Henry Martin', '2024-02-15', '22:00:00', 'Venus', 'Telescope - 6 inch', 'Green Bank', 'Cold night'),
('Isabella Scott', '2024-03-01', '19:00:00', 'Uranus', 'Telescope - 12 inch', 'Mount Wilson', 'Very clear sky'),
('Jack Adams', '2024-01-05', '20:30:00', 'Mercury', 'Telescope - 8 inch', 'Cherry Springs', 'First observation'),
('Katie Johnson', '2024-02-20', '23:15:00', 'Neptune', 'Telescope - 10 inch', 'Mauna Kea', 'High humidity'),
('Liam Davis', '2024-01-08', '21:45:00', 'Sirius', 'Binoculars', 'Blue Ridge', 'Amazing visibility'),
('Mia Wilson', '2024-02-25', '19:30:00', 'Betelgeuse', 'Camera', 'Death Valley', 'Captured a fading star'),
('Noah Miller', '2024-03-05', '22:45:00', 'Rigel', 'Telescope - 6 inch', 'Green Bank', 'Clear and cold'),
('Olivia Lee', '2024-01-12', '20:20:00', 'Polaris', 'Telescope - 8 inch', 'Cherry Springs', 'Stable viewing conditions'),
('Paul Young', '2024-02-28', '23:30:00', 'Mars', 'Telescope - 10 inch', 'Mount Wilson', 'Red planet in view'),
('Quinn Edwards', '2024-03-10', '21:10:00', 'Alpha Centauri', 'Binoculars', 'Blue Ridge', 'Beautiful double star'),
('Rachel King', '2024-01-18', '19:55:00', 'M31 Andromeda', 'Telescope - 12 inch', 'Mauna Kea', 'Galaxy in full view'),
('Sam Thompson', '2024-02-02', '23:05:00', 'Lyra', 'Camera', 'Death Valley', 'Astrophotography night'),
('Tina White', '2024-03-15', '20:40:00', 'Cygnus', 'Telescope - 6 inch', 'Green Bank', 'Constellation night'),
('Uma Patel', '2024-01-22', '21:50:00', 'Cassiopeia', 'Binoculars', 'Blue Ridge', 'Visible despite light clouds'),
('Victor Gonzalez', '2024-03-03', '20:05:00', 'Taurus', 'Telescope - 8 inch', 'Cherry Springs', 'Bull''s eye'),
('Wendy Martinez', '2024-01-27', '22:20:00', 'Gemini', 'Telescope - 10 inch', 'Mount Wilson', 'Twin stars observed'),
('Xavier Hernandez', '2024-02-07', '23:40:00', 'Leo', 'Camera', 'Death Valley', 'Leo''s heart captured'),
('Yara Shahidi', '2024-02-12', '20:25:00', 'Cancer', 'Telescope - 6 inch', 'Green Bank', 'Faint but visible'),
('Zane Murphy', '2024-03-08', '21:35:00', 'Aquila', 'Binoculars', 'Blue Ridge', 'Eagle in the sky'),
('Anna Bell', '2024-01-31', '22:50:00', 'Lyra', 'Telescope - 12 inch', 'Mauna Kea', 'Vega shining bright'),
('Brian Cook', '2024-02-03', '19:40:00', 'Scorpius', 'Camera', 'Death Valley', 'Scorpion''s tail'),
('Cindy Lopez', '2024-03-13', '20:15:00', 'Ursa Major', 'Telescope - 8 inch', 'Cherry Springs', 'Great Bear'),
('Derek Morgan', '2024-01-11', '21:25:00', 'Ursa Minor', 'Binoculars', 'Mount Wilson', 'Little Bear');

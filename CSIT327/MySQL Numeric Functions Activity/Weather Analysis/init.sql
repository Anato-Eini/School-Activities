CREATE TABLE WeatherData (
    observation_id INT PRIMARY KEY,
    location_name VARCHAR(100),
    temperature_c FLOAT,
    humidity_percent FLOAT,
    wind_speed_kmh FLOAT,
    precipitation_mm FLOAT,
    observation_date DATE
);

INSERT INTO WeatherData (observation_id, location_name, temperature_c, humidity_percent, wind_speed_kmh, precipitation_mm, observation_date) VALUES
(1, 'Barcelona', 20.5, 65.2, 10.1, 0.0, '2024-03-15'),
(2, 'Madrid', 25.3, 70.8, 8.5, 0.5, '2024-03-16'),
(3, 'Seville', 22.7, 68.3, 12.0, 1.2, '2024-03-17'),
(4, 'Valencia', 21.9, 67.5, 9.2, 0.8, '2024-03-18'),
(5, 'Barcelona', 22.8, 69.1, 11.5, 0.0, '2024-03-19'),
(6, 'Madrid', 26.5, 75.4, 7.8, 0.3, '2024-03-20'),
(7, 'Seville', 24.3, 72.0, 10.3, 1.5, '2024-03-21'),
(8, 'Valencia', 20.6, 66.8, 8.9, 0.6, '2024-03-22'),
(9, 'Barcelona', 19.7, 63.5, 12.2, 0.0, '2024-03-23'),
(10, 'Madrid', 24.9, 73.2, 8.3, 0.4, '2024-03-24'),
(11, 'Barcelona', 21.3, 66.5, 11.8, 0.0, '2024-03-25'),
(12, 'Madrid', 27.8, 76.2, 6.7, 0.2, '2024-03-26'),
(13, 'Seville', 23.5, 70.1, 9.7, 1.0, '2024-03-27'),
(14, 'Valencia', 22.1, 68.7, 7.5, 0.7, '2024-03-28'),
(15, 'Barcelona', 20.9, 64.8, 13.5, 0.0, '2024-03-29');
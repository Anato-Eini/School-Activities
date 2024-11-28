CREATE TABLE PlantCollection (
    plant_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100),
    type VARCHAR(100),
    planting_year INT,
    height_cm DECIMAL(5, 2),
    watering_frequency VARCHAR(50),
    sunlight_need VARCHAR(50)
);

INSERT INTO PlantCollection (name, type, planting_year, height_cm, watering_frequency, sunlight_need) 
VALUES 
('Peace Lily', 'Indoor', 2018, 35.5, 'Weekly', 'Partial Shade'),
('Snake Plant', 'Indoor', 2019, 40.0, 'Bi-weekly', 'Indirect Sunlight'),
('Rosemary', 'Herb', 2017, 25.0, 'Twice a week', 'Full Sun'),
('Monstera', 'Indoor', 2020, 60.0, 'Weekly', 'Partial Shade'),
('Lavender', 'Herb', 2018, 30.0, 'Every 3 days', 'Full Sun'),
('Pothos', 'Indoor', 2019, 25.0, 'Weekly', 'Low Light');
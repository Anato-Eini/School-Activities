CREATE TABLE EnvironmentalData (
    record_id INT AUTO_INCREMENT PRIMARY KEY,
    location VARCHAR(100),
    temperature DECIMAL(5,2),
    humidity DECIMAL(5,2),
    air_quality_index INT,
    collection_date DATE,
    sensor_type VARCHAR(50)
);


INSERT INTO EnvironmentalData (location, temperature, humidity, air_quality_index, collection_date, sensor_type) VALUES
('Central Park', -3.25, 45.5, 120, '2023-01-05', 'EcoMonitor'),
('Central Park', 2.75, 50.0, 110, '2023-01-15', 'GeoSensor'),
('Downtown', 36.5, 25.0, 160, '2023-07-10', 'EcoMonitor'),
('Riverbank', 38.0, 30.2, 180, '2023-07-12', 'GeoSensor'),
('Uptown', 41.5, 22.0, 155, '2023-08-05', 'EcoMonitor'),
('Central Park', 5.00, 55.0, 95, '2023-01-20', 'NatureWatch'),
('Forest Side', -5.75, 65.3, 205, '2023-02-15', 'GeoSensor'),
('Downtown', 25.0, 40.0, 50, '2023-05-10', 'EcoMonitor'),
('Uptown', 37.0, 18.0, 45, '2023-09-20', 'NatureWatch'),
('Central Park', -2.00, 60.0, 130, '2023-01-25', 'GeoSensor'),
('Lakeside', 0.00, 70.0, 100, '2023-03-05', 'EcoMonitor'),
('Downtown', 42.5, 20.0, 210, '2023-07-15', 'GeoSensor'),
('Forest Side', -10.00, 75.0, 220, '2023-12-10', 'NatureWatch'),
('Uptown', 10.0, 50.0, 55, '2023-04-22', 'EcoMonitor'),
('Riverbank', 39.0, 33.0, 170, '2023-07-18', 'GeoSensor'),
('Downtown', 35.5, 19.0, 45, '2023-06-30', 'EcoMonitor'),
('Central Park', 1.50, 48.0, 105, '2023-01-30', 'NatureWatch'),
('Lakeside', 5.00, 80.0, 90, '2023-03-15', 'GeoSensor'),
('Forest Side', -7.25, 68.0, 195, '2023-11-05', 'EcoMonitor'),
('Downtown', 36.0, 21.0, 155, '2023-07-20', 'NatureWatch'),
('Riverbank', 40.5, 35.0, 180, '2023-08-10', 'EcoMonitor'),
('Uptown', 3.00, 55.0, 60, '2023-04-30', 'GeoSensor'),
('Forest Side', -6.00, 72.0, 210, '2023-12-20', 'NatureWatch'),
('Lakeside', 37.5, 25.0, 160, '2023-07-25', 'EcoMonitor'),
('Downtown', 34.0, 17.0, 40, '2023-06-15', 'GeoSensor');

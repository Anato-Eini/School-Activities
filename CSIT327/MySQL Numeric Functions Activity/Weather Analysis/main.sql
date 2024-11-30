SELECT MAX(temperature_c) - MIN(temperature_c) AS temperature_range
FROM WeatherData
WHERE location_name = "Barcelona";

SELECT location_name, CEIL(AVG(humidity_percent)) AS average_humidity
FROM WeatherData
WHERE location_name = "Madrid";

SELECT observation_date, SUM(precipitation_mm) AS total_precipitation
FROM WeatherData
WHERE observation_date = "2024-03-26";

SELECT AVG(wind_speed_kmh) AS average_wind_speed
FROM WeatherData
WHERE wind_speed_kmh BETWEEN 5 AND 10;
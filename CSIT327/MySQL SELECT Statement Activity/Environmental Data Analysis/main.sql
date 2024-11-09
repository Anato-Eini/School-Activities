SELECT * FROM EnvironmentalData WHERE location = "Central Park";

SELECT record_id, location, temperature, air_quality_index FROM EnvironmentalData WHERE temperature > 35 AND air_quality_index > 150;

SELECT * FROM EnvironmentalData WHERE sensor_type = "GeoSensor" AND collection_date > '2023-06-01';

SELECT location FROM EnvironmentalData WHERE temperature < 0 OR temperature > 40 OR air_quality_index > 200;

SELECT * FROM EnvironmentalData WHERE location = "Downtown" AND humidity >= 20 AND air_quality_index >= 50;


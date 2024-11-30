SELECT ABS(AVG(Carp.weight_g) - AVG(Pike.weight_g)) AS weight_difference
FROM FishObservations AS Carp JOIN FishObservations AS Pike ON Pike.species_name = "Pike" AND Carp.species_name = 'Carp';

SELECT species_name, ROUND(AVG(length_cm), 2) AS average_length
FROM FishObservations
WHERE species_name = "Trout"
GROUP BY species_name;

SELECT observation_date, SUM(weight_g) AS total_weight
FROM FishObservations
GROUP BY observation_date;

SELECT AVG(water_temp_c) AS average_temperature
FROM FishObservations
WHERE depth_m BETWEEN 10 AND 20;

SELECT species_name, CEIL(MIN(depth_m)) AS min_depth_ceiling
FROM FishObservations
WHERE species_name = "Salmon";
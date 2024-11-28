SELECT Airports.airport_name, Airports.city, Countries.country_name, Airports.terminals
FROM Airports LEFT JOIN Countries ON Airports.country_id = Countries.country_id
ORDER BY Airports.terminals;

SELECT Airports.airport_name, Airports.city, Countries.country_name, Airports.runway_length
FROM Airports LEFT JOIN Countries ON Airports.country_id = Countries.country_id
ORDER BY Airports.runway_length DESC;

SELECT Airports.airport_name, Airports.city, Countries.country_name
FROM Countries LEFT JOIN Airports ON Airports.country_id = Countries.country_id;

SELECT Airports.airport_name, Airports.city, Countries.country_name
FROM Countries LEFT JOIN Airports ON Airports.country_id = Countries.country_id
WHERE Airports.runway_length > 3000;
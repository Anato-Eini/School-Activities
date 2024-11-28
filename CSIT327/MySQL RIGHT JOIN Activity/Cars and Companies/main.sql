SELECT Cars.car_id, Cars.car_name, Cars.year, Cars.price, Manufacturers.manufacturer_name, Manufacturers.country
FROM Cars RIGHT JOIN Manufacturers ON Cars.manufacturer_id = Manufacturers.manufacturer_id;

SELECT Cars.car_id, Cars.car_name, Cars.year, Cars.price, Manufacturers.manufacturer_name, Manufacturers.country
FROM Cars RIGHT JOIN Manufacturers ON Cars.manufacturer_id = Manufacturers.manufacturer_id
ORDER BY Cars.car_id;

SELECT Manufacturers.manufacturer_id, Manufacturers.manufacturer_name, Manufacturers.country, Cars.car_id, Cars.car_name, Cars.year, Cars.price
FROM Cars RIGHT JOIN Manufacturers ON Cars.manufacturer_id = Manufacturers.manufacturer_id
WHERE Cars.price > 20000
ORDER BY Manufacturers.manufacturer_id;
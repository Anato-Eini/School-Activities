SELECT Vehicles.model, Manufacturers.name AS manufacturer_name
FROM Vehicles, Manufacturers;

SELECT Mechanics.name AS mechanic_name, Vehicles.model
FROM Mechanics, Vehicles;

SELECT Mechanics.name AS mechanic_name, Mechanics.specialization, Manufacturers.name AS manufacturer_name
FROM Mechanics, Manufacturers;

SELECT Vehicles.model, Manufacturers.name AS manufacturer_name, Mechanics.name AS mechanic_name
FROM Vehicles, Manufacturers, Mechanics;
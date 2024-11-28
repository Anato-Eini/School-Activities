SELECT *
FROM Inventory
WHERE quantity > 100 AND EXISTS (SELECT *
    FROM Inventory
    WHERE last_updated > "2024-01-01");

SELECT *
FROM Inventory
WHERE location = "Aisle1" AND EXISTS (SELECT *
    FROM Inventory
    WHERE quantity < 50);

SELECT *
FROM Inventory
WHERE last_updated > "2024-01-01" AND EXISTS (SELECT *
    FROM Inventory
    WHERE quantity = 0);

SELECT *
FROM Inventory
WHERE EXISTS (SELECT *
FROM Inventory
WHERE quantity = 0);
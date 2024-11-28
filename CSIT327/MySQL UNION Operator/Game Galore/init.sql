    SELECT *
    FROM GameStoreInventory
    WHERE developer = "Naughty Dog"
UNION
    SELECT *
    FROM GameStoreInventory
    WHERE developer = "FromSoftware";

    SELECT *
    FROM GameStoreInventory
    WHERE platform = "PC"
UNION
    SELECT *
    FROM GameStoreInventory
    WHERE platform = "PlayStation";

    SELECT *
    FROM GameStoreInventory
    WHERE release_year = 2020
UNION
    SELECT *
    FROM GameStoreInventory
    WHERE release_year = 2021;

    SELECT *
    FROM GameStoreInventory
    WHERE genre = "RPG"
UNION
    SELECT *
    FROM GameStoreInventory
    WHERE genre = "FPS";

    SELECT *
    FROM GameStoreInventory
    WHERE stock_quantity < 20
UNION
    SELECT *
    FROM GameStoreInventory
    WHERE stock_quantity > 40;
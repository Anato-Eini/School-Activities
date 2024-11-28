    SELECT *
    FROM RestaurantMenu
    WHERE category = "Appetizers"
UNION
    SELECT *
    FROM RestaurantMenu
    WHERE category = "Desserts";

    SELECT *
    FROM RestaurantMenu
    WHERE price < 10
UNION
    SELECT *
    FROM RestaurantMenu
    WHERE price > 20;

    SELECT *
    FROM RestaurantMenu
    WHERE availability = "Available"
UNION
    SELECT *
    FROM RestaurantMenu
    WHERE availability = "Unavailable";
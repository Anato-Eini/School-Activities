    SELECT *
    FROM BookInventory
    WHERE author = "Stephen King"
UNION
    SELECT *
    FROM BookInventory
    WHERE author = "J.K. Rowling";

    SELECT *
    FROM BookInventory
    WHERE genre = "Fiction"
UNION
    SELECT *
    FROM BookInventory
    WHERE genre = "Mystery";

    SELECT *
    FROM BookInventory
    WHERE publication_year = 1977
UNION
    SELECT *
    FROM BookInventory
    WHERE publication_year = 1988; 

    SELECT *
    FROM BookInventory
    WHERE price < 10
UNION
    SELECT *
    FROM BookInventory
    WHERE price > 50; 
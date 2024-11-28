    SELECT *
    FROM RecipeBook
    WHERE chef = "Gordon Ramsay"
UNION
    SELECT *
    FROM RecipeBook
    WHERE chef = "Jamie Oliver";


    SELECT *
    FROM RecipeBook
    WHERE difficulty = "Easy"
UNION
    SELECT *
    FROM RecipeBook
    WHERE difficulty = "Medium";
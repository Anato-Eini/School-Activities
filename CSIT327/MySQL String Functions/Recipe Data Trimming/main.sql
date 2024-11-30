SELECT TRIM(recipe_name) AS trimmed_recipe_name
FROM RecipeCollection;

SELECT recipe_id, LEFT(chef_name, 10) AS short_chef_name
FROM RecipeCollection;

SELECT TRIM(cuisine) AS trimmed_cuisine
FROM RecipeCollection;
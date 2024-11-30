CREATE TABLE RecipeCollection (
    recipe_id INT PRIMARY KEY,
    recipe_name VARCHAR(255),
    chef_name VARCHAR(100),
    cuisine VARCHAR(50),
    prep_time TIME
);

INSERT INTO RecipeCollection (recipe_id, recipe_name, chef_name, cuisine, prep_time) VALUES
(1, ' Spaghetti Carbonara  ', 'Jamie Oliver', 'Italian', '00:25:00'),
(2, 'Chicken Tikka Masala', 'Gordon Ramsay', 'Indian', '00:40:00'),
(3, ' Beef Stew', 'Nigella Lawson', 'British', '02:00:00'),
(4, 'Pad Thai  ', 'Martin Yan', 'Thai', '00:30:00'),
(5, 'Tiramisu', 'Giada De Laurentiis', 'Italian', '00:20:00'),
(6, 'Sushi Rolls', 'Masaharu Morimoto', 'Japanese', '00:45:00'),
(7, 'Beef Wellington', 'Gordon Ramsay', 'British', '01:30:00'),
(8, 'Moussaka', 'Yotam Ottolenghi', 'Greek', '02:00:00'),
(9, 'Chicken Parmesan', 'Ina Garten', 'Italian', '01:00:00'),
(10, 'Tom Yum Soup', 'Jet Tila', 'Thai', '00:30:00');
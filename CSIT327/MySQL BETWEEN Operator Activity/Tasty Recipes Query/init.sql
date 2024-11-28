CREATE TABLE RecipeCollection (
    recipe_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100),
    chef VARCHAR(100),
    cooking_year INT,
    cuisine VARCHAR(50),
    prep_time_min INT,
    rating DECIMAL(3, 1),
    source VARCHAR(100)
);

INSERT INTO RecipeCollection (name, chef, cooking_year, cuisine, prep_time_min, rating, source) 
VALUES 
('Spaghetti Bolognese', 'Gordon Ramsay', 2010, 'Italian', 45, 9.2, 'BBC Food'),
('Pad Thai', 'Jamie Oliver', 2005, 'Thai', 30, 8.9, 'JamieOliver.com'),
('Beef Wellington', 'Gordon Ramsay', 2000, 'British', 90, 9.5, 'Masterclass'),
('Chicken Tikka Masala', 'Sanjeev Kapoor', 1995, 'Indian', 60, 9.1, 'FoodFood'),
('Sushi Rolls', 'Nobu Matsuhisa', 2008, 'Japanese', 50, 9.3, 'Nobu Restaurants'),
('French Onion Soup', 'Julia Child', 1980, 'French', 60, 8.7, 'Mastering the Art of French Cooking');
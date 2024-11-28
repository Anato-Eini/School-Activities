CREATE TABLE RecipeBook (
    recipe_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100),
    chef VARCHAR(100),
    category VARCHAR(50),
    prep_time TIME,
    difficulty VARCHAR(20)
);

INSERT INTO RecipeBook (name, chef, category, prep_time, difficulty) 
VALUES 
('Beef Wellington', 'Gordon Ramsay', 'Main Course', '01:30:00', 'Medium'),
('Spaghetti Carbonara', 'Gordon Ramsay', 'Pasta', '00:30:00', 'Easy'),
('Shepherd''s Pie', 'Gordon Ramsay', 'Main Course', '01:00:00', 'Medium'),
('Chicken Tikka Masala', 'Jamie Oliver', 'Curry', '01:15:00', 'Medium'),
('Roast Chicken', 'Jamie Oliver', 'Main Course', '01:45:00', 'Medium'),
('Pasta Primavera', 'Jamie Oliver', 'Pasta', '00:40:00', 'Easy'),
('Risotto', 'Gordon Ramsay', 'Rice', '00:45:00', 'Medium'),
('Beef Stew', 'Jamie Oliver', 'Main Course', '02:30:00', 'Hard'),
('Mushroom Risotto', 'Gordon Ramsay', 'Rice', '00:55:00', 'Medium'),
('Bolognese Sauce', 'Jamie Oliver', 'Sauce', '02:15:00', 'Medium');
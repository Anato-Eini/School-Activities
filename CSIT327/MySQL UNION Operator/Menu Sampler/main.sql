CREATE TABLE RestaurantMenu (
    item_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100),
    category VARCHAR(50),
    price DECIMAL(6, 2),
    availability VARCHAR(20)
);

INSERT INTO RestaurantMenu (name, category, price, availability) 
VALUES 
('Caesar Salad', 'Appetizers', 8.99, 'Available'),
('Garlic Bread', 'Appetizers', 5.99, 'Available'),
('Cheeseburger', 'Main Course', 12.99, 'Available'),
('Spaghetti Carbonara', 'Main Course', 14.99, 'Available'),
('Chocolate Cake', 'Desserts', 7.99, 'Available'),
('Ice Cream Sundae', 'Desserts', 6.99, 'Available'),
('French Onion Soup', 'Appetizers', 9.99, 'Unavailable'),
('Tiramisu', 'Desserts', 9.99, 'Unavailable'),
('Grilled Salmon', 'Main Course', 18.99, 'Available'),
('Mushroom Risotto', 'Main Course', 16.99, 'Available');
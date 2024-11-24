CREATE TABLE BookInventory (
    book_id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(100),
    author VARCHAR(100),
    genre VARCHAR(50),
    quantity_available INT,
    price DECIMAL(10, 2),
    last_updated DATE
);

INSERT INTO BookInventory (title, author, genre, quantity_available, price, last_updated) 
VALUES
('To Kill a Mockingbird', 'Harper Lee', 'Fiction', 50, 25.99, '2023-12-01'),
('1984', 'George Orwell', 'Fiction', 40, 22.99, '2023-11-15'),
('The Great Gatsby', 'F. Scott Fitzgerald', 'Fiction', 60, 21.99, '2023-10-20'),
('Educated', 'Tara Westover', 'Non-Fiction', 30, 27.99, '2023-09-05'),
('Becoming', 'Michelle Obama', 'Non-Fiction', 45, 29.99, '2023-08-10'),
('The Catcher in the Rye', 'J.D. Salinger', 'Fiction', 55, 23.99, '2023-07-15'),
('Sapiens: A Brief History of Humankind', 'Yuval Noah Harari', 'Non-Fiction', 35, 24.99, '2023-06-20');
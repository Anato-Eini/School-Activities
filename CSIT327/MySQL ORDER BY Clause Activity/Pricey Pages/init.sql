CREATE TABLE BookInventory (
    book_id INT AUTO_INCREMENT PRIMARY KEY,
    book_title VARCHAR(255),
    author VARCHAR(255),
    genre VARCHAR(100),
    quantity INT,
    price DECIMAL(10,2),
    last_updated DATETIME
);

INSERT INTO BookInventory (book_title, author, genre, quantity, price, last_updated)
VALUES
('To Kill a Mockingbird', 'Harper Lee', 'Fiction', 100, 12.99, '2023-01-10 08:30:00'),
('1984', 'George Orwell', 'Science Fiction', 80, 9.99, '2023-01-15 10:45:00'),
('Pride and Prejudice', 'Jane Austen', 'Romance', 120, 8.99, '2023-02-05 14:20:00'),
('The Great Gatsby', 'F. Scott Fitzgerald', 'Classic', 90, 11.99, '2023-01-20 12:00:00'),
('The Catcher in the Rye', 'J.D. Salinger', 'Literature', 110, 10.99, '2023-01-25 09:00:00'),
('Animal Farm', 'George Orwell', 'Fiction', 70, 7.99, '2023-02-10 11:30:00'),
('The Hobbit', 'J.R.R. Tolkien', 'Fantasy', 150, 14.99, '2023-01-30 13:15:00');

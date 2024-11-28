CREATE TABLE Books (
    book_id INT PRIMARY KEY,
    title VARCHAR(255),
    author VARCHAR(100),
    genre VARCHAR(50),
    publish_year INT,
    isbn VARCHAR(20),
    available BOOLEAN
);

CREATE TABLE Borrowers (
    borrower_id INT PRIMARY KEY,
    first_name VARCHAR(50),
    last_name VARCHAR(50),
    book_id INT,
    borrow_date DATE,
    return_date DATE,
    FOREIGN KEY (book_id) REFERENCES Books(book_id)
);

INSERT INTO Books (book_id, title, author, genre, publish_year, isbn, available) VALUES
(1, '1984', 'George Orwell', 'Dystopian', 1949, '9780451524935', TRUE),
(2, 'To Kill a Mockingbird', 'Harper Lee', 'Fiction', 1960, '9780061120084', TRUE),
(3, 'The Great Gatsby', 'F. Scott Fitzgerald', 'Fiction', 1925, '9780743273565', FALSE),
(4, 'Pride and Prejudice', 'Jane Austen', 'Romance', 1813, '9781936594290', TRUE),
(5, 'Brave New World', 'Aldous Huxley', 'Dystopian', 1932, '9780060850524', TRUE),
(6, 'Moby Dick', 'Herman Melville', 'Adventure', 1851, '9780142437247', TRUE),
(7, 'War and Peace', 'Leo Tolstoy', 'Historical Fiction', 1869, '9781400079988', TRUE),
(8, 'Great Expectations', 'Charles Dickens', 'Fiction', 1861, '9780141439563', TRUE),
(9, 'Crime and Punishment', 'Fyodor Dostoevsky', 'Psychological Fiction', 1866, '9780143058144', TRUE),
(10, 'Wuthering Heights', 'Emily Bronte', 'Gothic Fiction', 1847, '9780141439556', FALSE);

INSERT INTO Borrowers (borrower_id, first_name, last_name, book_id, borrow_date, return_date) VALUES
(1, 'John', 'Doe', 3, '2023-01-15', '2023-02-15'),
(2, 'Jane', 'Smith', 2, '2023-02-01', NULL),
(3, 'Emily', 'Johnson', 1, '2023-03-10', '2023-04-10'),
(4, 'Michael', 'Brown', 5, '2023-02-20', NULL),
(5, 'Sarah', 'Davis', 4, '2023-01-25', '2023-03-01'),
(6, 'James', 'Wilson', 7, '2023-04-01', '2023-05-01'),
(7, 'Jessica', 'Garcia', 6, '2023-02-15', '2023-03-15'),
(8, 'William', 'Martinez', 9, '2023-03-20', '2023-04-20'),
(9, 'Sophia', 'Robinson', 10, '2023-01-05', '2023-02-05'),
(10, 'David', 'Clark', 8, '2023-03-15', NULL),
(11, 'Olivia', 'Lopez', 1, '2023-02-05', '2023-03-07'),
(12, 'Lucas', 'Hernandez', 2, '2023-01-20', '2023-02-20'),
(13, 'Mia', 'Martinez', 3, '2023-03-25', '2023-04-25'),
(14, 'Aiden', 'Gonzalez', 4, '2023-04-05', '2023-05-05'),
(15, 'Ella', 'Perez', 5, '2023-02-18', '2023-03-20'),
(16, 'Ethan', 'Young', 6, '2023-01-30', '2023-02-28'),
(17, 'Ava', 'Anderson', 7, '2023-03-10', '2023-04-10'),
(18, 'Sophie', 'Thomas', 8, '2023-02-25', NULL),
(19, 'Isaac', 'Taylor', 9, '2023-04-02', '2023-05-02'),
(20, 'Liam', 'Moore', 10, '2023-01-10', '2023-02-10'),
(21, 'Amelia', 'Jackson', 1, '2023-03-15', '2023-04-15'),
(22, 'Gabriel', 'White', 2, '2023-01-28', '2023-02-28'),
(23, 'Charlotte', 'Harris', 3, '2023-02-15', '2023-03-15'),
(24, 'Benjamin', 'Martin', 4, '2023-03-05', NULL),
(25, 'Zoe', 'Thompson', 5, '2023-04-10', '2023-05-10'),
(26, 'Noah', 'Garcia', 6, '2023-01-20', '2023-02-20'),
(27, 'Emma', 'Martinez', 7, '2023-02-12', '2023-03-14'),
(28, 'Daniel', 'Robinson', 8, '2023-03-22', '2023-04-22'),
(29, 'Grace', 'Clark', 9, '2023-01-15', '2023-02-15'),
(30, 'Oliver', 'Lewis', 10, '2023-02-28', NULL);
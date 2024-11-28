CREATE TABLE BookInventory (
    book_id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(100),
    author VARCHAR(100),
    genre VARCHAR(50),
    publication_year INT,
    publisher VARCHAR(100),
    price DECIMAL(6, 2),
    stock_quantity INT
);

INSERT INTO BookInventory (title, author, genre, publication_year, publisher, price, stock_quantity) 
VALUES 
('The Shining', 'Stephen King', 'Horror', 1977, 'Doubleday', 9.99, 30),
('Harry Potter and the Philosopher''s Stone', 'J.K. Rowling', 'Fantasy', 1997, 'Bloomsbury', 19.99, 40),
('The Girl on the Train', 'Paula Hawkins', 'Mystery', 2015, 'Riverhead Books', 12.99, 50),
('It', 'Stephen King', 'Horror', 1986, 'Viking', 14.99, 35),
('Gone Girl', 'Gillian Flynn', 'Thriller', 2012, 'Crown Publishing Group', 11.99, 60),
('The Da Vinci Code', 'Dan Brown', 'Mystery', 2003, 'Doubleday', 16.99, 45),
('To Kill a Mockingbird', 'Harper Lee', 'Classics', 1960, 'J.B. Lippincott & Co.', 8.99, 55),
('The Catcher in the Rye', 'J.D. Salinger', 'Classics', 1951, 'Little, Brown and Company', 7.99, 65),
('1984', 'George Orwell', 'Dystopian', 1949, 'Secker & Warburg', 6.99, 70),
('The Hobbit', 'J.R.R. Tolkien', 'Fantasy', 1937, 'George Allen & Unwin', 9.99, 80),
('The Great Gatsby', 'F. Scott Fitzgerald', 'Classics', 1925, 'Charles Scribner''s Sons', 10.99, 75),
('Pride and Prejudice', 'Jane Austen', 'Romance', 1813, 'T. Egerton', 5.99, 90),
('The Hunger Games', 'Suzanne Collins', 'Dystopian', 2008, 'Scholastic Corporation', 13.99, 85),
('The Catcher in the Rye', 'J.D. Salinger', 'Classics', 1951, 'Little, Brown and Company', 7.99, 65),
('Moby-Dick', 'Herman Melville', 'Adventure', 1851, 'Harper & Brothers', 11.99, 50),
('A Game of Thrones', 'George R.R. Martin', 'Fantasy', 1996, 'Bantam Spectra', 18.99, 40),
('The Adventures of Huckleberry Finn', 'Mark Twain', 'Adventure', 1884, 'Chatto & Windus', 8.99, 55),
('The Kite Runner', 'Khaled Hosseini', 'Drama', 2003, 'Riverhead Books', 14.99, 60),
('The Martian', 'Andy Weir', 'Science Fiction', 2011, 'Crown Publishing Group', 17.99, 45),
('The Alchemist', 'Paulo Coelho', 'Fantasy', 1988, 'Harper & Row', 12.99, 70);
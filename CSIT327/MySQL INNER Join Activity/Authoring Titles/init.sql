CREATE TABLE Authors (
    author_id INT PRIMARY KEY,
    author_name VARCHAR(100),
    nationality VARCHAR(100),
    birth_year INT
);

CREATE TABLE Books (
    book_id INT PRIMARY KEY,
    title VARCHAR(255),
    author_id INT,
    genre VARCHAR(50),
    publish_year INT,
    isbn VARCHAR(20),
    FOREIGN KEY (author_id) REFERENCES Authors(author_id)
);

INSERT INTO Authors (author_id, author_name, nationality, birth_year) VALUES
(1, 'Stephen King', 'American', 1947),
(2, 'J.K. Rowling', 'British', 1965),
(3, 'Haruki Murakami', 'Japanese', 1949),
(4, 'Agatha Christie', 'British', 1890),
(5, 'Gabriel García Márquez', 'Colombian', 1927),
(6, 'Toni Morrison', 'American', 1931);

INSERT INTO Books (book_id, title, author_id, genre, publish_year, isbn) VALUES
(1, 'The Shining', 1, 'Horror', 1977, '9780307743657'),
(2, 'Harry Potter and the Philosopher\'s Stone', 2, 'Fantasy', 1997, '9780747532743'),
(3, 'Norwegian Wood', 3, 'Fiction', 1987, '9780375704024'),
(4, 'Murder on the Orient Express', 4, 'Mystery', 1934, '9780007119318'),
(5, 'One Hundred Years of Solitude', 5, 'Magical Realism', 1967, '9780060883287'),
(6, 'Beloved', 6, 'Historical Fiction', 1987, '9781400033416'),
(7, 'It', 1, 'Horror', 1986, '9780451169518'),
(8, 'Harry Potter and the Order of the Phoenix', 2, 'Fantasy', 2003, '9780439358071'),
(9, 'Kafka on the Shore', 3, 'Surrealism', 2002, '9781400079278'),
(10, 'Death on the Nile', 4, 'Mystery', 1937, '9780062073556'),
(11, 'Love in the Time of Cholera', 5, 'Romance', 1985, '9780307389732'),
(12, 'Song of Solomon', 6, 'Literary Fiction', 1977, '9780452280625'),
(13, 'The Catcher in the Rye', 6, 'Coming-of-age', 1951, '9780316769488'),
(14, 'The Lord of the Rings', 6, 'Fantasy', 1954, '9780618640157'),
(15, 'To Kill a Mockingbird', 5, 'Fiction', 1960, '9780061120084'),
(16, '1984', 2, 'Dystopian', 1949, '9780451524935'),
(17, 'Brave New World', 3, 'Dystopian', 1932, '9780060850524'),
(18, 'The Great Gatsby', 4, 'Fiction', 1925, '9780743273565');
CREATE TABLE LibraryData (
    book_id INT PRIMARY KEY,
    title VARCHAR(255),
    author VARCHAR(100),
    genre VARCHAR(50),
    publication_year INT,
    pages INT,
    library_section VARCHAR(50),
    times_borrowed INT,
    last_borrowed_date DATE,
    borrower_id INT
);

INSERT INTO LibraryData VALUES (1, 'Galactic Odyssey', 'Neil Gaiman', 'Science Fiction', 2015, 320, 'Sci-Fi', 25, '2023-01-15', 101);
INSERT INTO LibraryData VALUES (2, 'The Lost Symbol', 'Dan Brown', 'Mystery', 2009, 509, 'Mystery', 40, '2022-12-10', 102);
INSERT INTO LibraryData VALUES (3, 'Brave New World', 'Aldous Huxley', 'Classic', 1932, 288, 'Classics', 30, '2023-02-20', 103);
INSERT INTO LibraryData VALUES (4, 'Educated', 'Tara Westover', 'Biography', 2018, 334, 'Biography', 15, '2022-11-05', 104);
INSERT INTO LibraryData VALUES (5, 'Dune', 'Frank Herbert', 'Science Fiction', 1965, 412, 'Sci-Fi', 50, '2023-03-03', 105);
INSERT INTO LibraryData VALUES (6, 'The Alchemist', 'Paulo Coelho', 'Fiction', 1988, 208, 'Fiction', 60, '2023-01-20', 106);
INSERT INTO LibraryData VALUES (7, 'The Shining', 'Stephen King', 'Horror', 1977, 447, 'Horror', 35, '2023-02-28', 107);
INSERT INTO LibraryData VALUES (8, '1984', 'George Orwell', 'Classic', 1949, 328, 'Classics', 45, '2023-02-01', 108);
INSERT INTO LibraryData VALUES (9, 'The Hobbit', 'J.R.R. Tolkien', 'Fantasy', 1937, 310, 'Fantasy', 55, '2023-03-10', 109);
INSERT INTO LibraryData VALUES (10, 'Becoming', 'Michelle Obama', 'Biography', 2018, 426, 'Biography', 20, '2023-01-30', 110);
INSERT INTO LibraryData VALUES (11, 'Inferno', 'Dan Brown', 'Mystery', 2013, 480, 'Mystery', 38, '2022-12-22', 111);
INSERT INTO LibraryData VALUES (12, 'War and Peace', 'Leo Tolstoy', 'Classic', 1869, 1225, 'Classics', 18, '2023-02-14', 112);
INSERT INTO LibraryData VALUES (13, 'Foundation', 'Isaac Asimov', 'Science Fiction', 1951, 255, 'Sci-Fi', 42, '2023-02-07', 113);
INSERT INTO LibraryData VALUES (14, 'To Kill a Mockingbird', 'Harper Lee', 'Fiction', 1960, 281, 'Fiction', 65, '2023-03-15', 114);
INSERT INTO LibraryData VALUES (15, 'Dracula', 'Bram Stoker', 'Horror', 1897, 418, 'Horror', 32, '2023-02-21', 115);
INSERT INTO LibraryData VALUES (16, 'Pride and Prejudice', 'Jane Austen', 'Classic', 1813, 279, 'Classics', 50, '2023-01-25', 116);
INSERT INTO LibraryData VALUES (17, 'The Lord of the Rings', 'J.R.R. Tolkien', 'Fantasy', 1954, 1178, 'Fantasy', 48, '2023-03-05', 117);
INSERT INTO LibraryData VALUES (18, 'Steve Jobs', 'Walter Isaacson', 'Biography', 2011, 656, 'Biography', 22, '2022-10-30', 118);
INSERT INTO LibraryData VALUES (19, 'Angels & Demons', 'Dan Brown', 'Mystery', 2000, 616, 'Mystery', 43, '2022-12-15', 119);
INSERT INTO LibraryData VALUES (20, 'Anna Karenina', 'Leo Tolstoy', 'Classic', 1877, 864, 'Classics', 25, '2023-02-18', 120);
INSERT INTO LibraryData VALUES (21, 'Neuromancer', 'William Gibson', 'Science Fiction', 1984, 271, 'Sci-Fi', 37, '2023-02-11', 121);
INSERT INTO LibraryData VALUES (22, 'The Great Gatsby', 'F. Scott Fitzgerald', 'Fiction', 1925, 218, 'Fiction', 70, '2023-03-18', 122);
INSERT INTO LibraryData VALUES (23, 'Frankenstein', 'Mary Shelley', 'Horror', 1818, 280, 'Horror', 29, '2023-02-24', 123);
INSERT INTO LibraryData VALUES (24, 'Moby Dick', 'Herman Melville', 'Classic', 1851, 635, 'Classics', 40, '2023-01-28', 124);
INSERT INTO LibraryData VALUES (25, 'The Chronicles of Narnia', 'C.S. Lewis', 'Fantasy', 1956, 767, 'Fantasy', 52, '2023-03-08', 125);
INSERT INTO LibraryData VALUES (26, 'The Autobiography of Benjamin Franklin', 'Benjamin Franklin', 'Biography', 1791, 312, 'Biography', 18, '2022-11-10', 126);
INSERT INTO LibraryData VALUES (27, 'The Da Vinci Code', 'Dan Brown', 'Mystery', 2003, 454, 'Mystery', 47, '2022-12-18', 127);
INSERT INTO LibraryData VALUES (28, 'Les Misérables', 'Victor Hugo', 'Classic', 1862, 1463, 'Classics', 23, '2023-02-16', 128);
INSERT INTO LibraryData VALUES (29, 'Snow Crash', 'Neal Stephenson', 'Science Fiction', 1992, 480, 'Sci-Fi', 34, '2023-02-04', 129);
INSERT INTO LibraryData VALUES (30, 'The Catcher in the Rye', 'J.D. Salinger', 'Fiction', 1951, 214, 'Fiction', 55, '2023-03-13', 130);
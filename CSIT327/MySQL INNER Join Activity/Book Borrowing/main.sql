SELECT Books.title, Books.author, Borrowers.first_name, Borrowers.last_name, Borrowers.borrow_date
FROM Books INNER JOIN Borrowers ON Books.book_id = Borrowers.book_id
WHERE Books.available = FALSE
ORDER BY Borrowers.borrow_date;

SELECT Books.title, Books.author, Borrowers.first_name, Borrowers.last_name
FROM Books INNER JOIN Borrowers ON Books.book_id = Borrowers.book_id
WHERE Borrowers.borrow_date > '2023-01-01'
ORDER BY Books.author;

SELECT Books.title, Books.author, Borrowers.first_name, Borrowers.last_name, Borrowers.return_date
FROM Books INNER JOIN Borrowers ON Books.book_id = Borrowers.book_id
WHERE Borrowers.return_date IS NULL;

SELECT Books.title, Books.genre, Borrowers.first_name, Borrowers.last_name
FROM Books INNER JOIN Borrowers ON Books.book_id = Borrowers.book_id
WHERE Books.genre = "Fiction" AND Borrowers.last_name = "Smith"
ORDER BY Books.title;

SELECT Books.title, Borrowers.first_name, Borrowers.last_name, Borrowers.borrow_date, Borrowers.return_date
FROM Borrowers INNER JOIN Books ON Borrowers.book_id = Books.book_id
WHERE Books.title = "The Great Gatsby"
ORDER BY Borrowers.borrow_date;
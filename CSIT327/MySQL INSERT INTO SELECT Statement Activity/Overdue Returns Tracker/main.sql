CREATE TABLE LateReturns
(
    transaction_id INT PRIMARY KEY,
    user_id INT,
    book_title VARCHAR(255),
    transaction_date DATE,
    transaction_type VARCHAR(50)
)

INSERT INTO LateReturns
    (transaction_id, user_id, book_title, transaction_date, transaction_type)
SELECT transaction_id, user_id, book_title, transaction_date, transaction_type
FROM LibraryTransactions
WHERE transaction_type = 'Return' AND transaction_date > "2024-02-01";

CREATE TABLE HighRatedBooks
(
    review_id INT PRIMARY KEY,
    user_id INT,
    book_title VARCHAR(255),
    review_text TEXT,
    rating INT,
    review_date DATE
)

INSERT INTO HighRatedBooks
    (review_id, user_id, book_title, review_text, rating, review_date)
SELECT review_id, user_id, book_title, review_text, rating, review_date
FROM BookReviews
WHERE rating >= 4;
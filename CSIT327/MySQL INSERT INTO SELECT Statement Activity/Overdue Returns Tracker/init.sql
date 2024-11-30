CREATE TABLE LibraryTransactions (
    transaction_id INT PRIMARY KEY,
    user_id INT,
    book_title VARCHAR(255),
    transaction_date DATE,
    transaction_type VARCHAR(50)
);

CREATE TABLE BookReviews (
    review_id INT PRIMARY KEY,
    user_id INT,
    book_title VARCHAR(255),
    review_text TEXT,
    rating INT,
    review_date DATE
);

INSERT INTO LibraryTransactions 
VALUES 
    (101, 1, 'To Kill a Mockingbird', '2024-01-15', 'Issue'),
    (102, 2, '1984', '2024-01-20', 'Issue'),
    (103, 3, 'The Great Gatsby', '2024-02-01', 'Return'),
    (104, 4, 'Brave New World', '2024-02-18', 'Issue'),
    (105, 5, 'Animal Farm', '2024-02-05', 'Return'),
    (106, 1, 'Pride and Prejudice', '2024-02-15', 'Issue'),
    (107, 2, 'The Catcher in the Rye', '2024-02-11', 'Return'),
    (108, 3, 'To Kill a Mockingbird', '2024-02-26', 'Issue'),
    (109, 4, '1984', '2024-03-02', 'Return'),
    (110, 5, 'The Great Gatsby', '2024-03-08', 'Issue');

INSERT INTO BookReviews 
VALUES 
    (201, 1, 'To Kill a Mockingbird', 'A classic that everyone should read!', 5, '2024-01-16'),
    (202, 2, '1984', 'Thought-provoking and relevant to today''s society.', 4, '2024-01-21'),
    (203, 3, 'The Great Gatsby', 'Captivating story with beautiful prose.', 5, '2024-02-02'),
    (204, 4, 'Brave New World', 'Fascinating exploration of a dystopian future.', 4, '2024-02-19'),
    (205, 5, 'Animal Farm', 'Brilliant allegory for political systems.', 5, '2024-02-06'),
    (206, 1, 'Pride and Prejudice', 'Classic romance with memorable characters.', 5, '2024-02-16'),
    (207, 2, 'The Catcher in the Rye', 'Iconic coming-of-age novel.', 4, '2024-02-12'),
    (208, 3, 'To Kill a Mockingbird', 'Powerful themes and unforgettable characters.', 5, '2024-02-27'),
    (209, 4, '1984', 'A chilling warning about surveillance and authoritarianism.', 5, '2024-03-03'),
    (210, 5, 'The Great Gatsby', 'Beautifully written and emotionally resonant.', 4, '2024-03-09');
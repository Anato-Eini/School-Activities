CREATE TABLE Users (
    user_id INT PRIMARY KEY,
    username VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL
);

CREATE TABLE Posts (
    post_id INT PRIMARY KEY,
    user_id INT,
    post_content TEXT NOT NULL,
    post_date DATE NOT NULL,
    FOREIGN KEY (user_id) REFERENCES Users(user_id)
);

CREATE TABLE Likes (
    like_id INT PRIMARY KEY,
    user_id INT,
    post_id INT,
    like_date DATE NOT NULL,
    FOREIGN KEY (user_id) REFERENCES Users(user_id),
    FOREIGN KEY (post_id) REFERENCES Posts(post_id)
);

INSERT INTO Users (user_id, username, email) VALUES
(1, 'user1', 'user1@example.com'),
(2, 'user2', 'user2@example.com'),
(3, 'user3', 'user3@example.com'),
(4, 'user4', 'user4@example.com'),
(5, 'user5', 'user5@example.com');

INSERT INTO Posts (post_id, user_id, post_content, post_date) VALUES
(1, 1, 'This is post 1', '2024-01-01'),
(2, 2, 'This is post 2', '2024-01-02'),
(3, 3, 'This is post 3', '2024-01-03'),
(4, 4, 'This is post 4', '2024-01-04'),
(5, 5, 'This is post 5', '2024-01-05'),
(6, 1, 'This is post 6', '2024-01-06'),
(7, 2, 'This is post 7', '2024-01-07'),
(8, 3, 'This is post 8', '2024-01-08'),
(9, 4, 'This is post 9', '2024-01-09'),
(10, 5, 'This is post 10', '2024-01-10'),
(11, 1, 'This is post 11', '2024-01-11'),
(12, 2, 'This is post 12', '2024-01-12'),
(13, 3, 'This is post 13', '2024-01-13'),
(14, 4, 'This is post 14', '2024-01-14'),
(15, 5, 'This is post 15', '2024-01-15');

INSERT INTO Likes (like_id, user_id, post_id, like_date) VALUES
(1, 1, 1, '2024-01-01'),
(2, 2, 2, '2024-01-02'),
(3, 3, 3, '2024-01-03'),
(4, 4, 4, '2024-01-04'),
(5, 5, 5, '2024-01-05');
CREATE TABLE ForumPosts (
    post_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT,
    post_content TEXT,
    likes INT,
    post_date DATE
);

INSERT INTO ForumPosts (user_id, post_content, likes, post_date) 
VALUES 
(1, 'This is a great forum!', 150, '2024-02-10'),
(2, 'I need help with SQL queries.', 80, '2024-02-15'),
(3, 'What are the best programming languages for beginners?', 120, '2024-02-20'),
(4, 'Check out my latest project!', 30, '2024-02-25'),
(5, 'Hello everyone!', 200, '2024-02-28'),
(6, 'I love coding!', 10, '2024-02-29'),
(7, 'Anyone interested in a coding challenge?', 60, '2024-02-29'),
(8, 'Tips for improving coding skills?', 5, '2024-02-29');
CREATE TABLE BlogPosts (
    post_id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT,
    title VARCHAR(100),
    content TEXT,
    post_date DATE
);

INSERT INTO BlogPosts (user_id, title, content, post_date) 
VALUES 
(1, 'Introduction to SQL', 'This is a beginner\'s guide to SQL.', '2024-02-24'),
(2, 'Python Tips', 'Learn useful tips and tricks for Python programming.', '2024-02-25'),
(1, 'Advanced SQL Queries', 'Explore advanced SQL queries and techniques.', '2024-02-26'),
(3, 'JavaScript Basics', 'Get started with JavaScript programming.', '2024-02-27'),
(2, 'Data Visualization in Python', 'Learn how to create visualizations in Python.', '2024-02-28');
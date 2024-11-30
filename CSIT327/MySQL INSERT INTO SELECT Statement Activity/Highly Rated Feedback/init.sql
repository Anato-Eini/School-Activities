CREATE TABLE CourseEnrollment (
    enrollment_id INT PRIMARY KEY,
    student_id INT,
    course_name VARCHAR(255),
    enrollment_date DATE
);

CREATE TABLE StudentFeedback (
    feedback_id INT PRIMARY KEY,
    student_id INT,
    course_name VARCHAR(255),
    feedback_text TEXT,
    rating INT,
    feedback_date DATE
);

INSERT INTO CourseEnrollment 
VALUES 
    (101, 1, 'Introduction to Programming', '2024-01-15'),
    (102, 2, 'Data Science Fundamentals', '2024-01-20'),
    (103, 3, 'Machine Learning Basics', '2024-02-01'),
    (104, 4, 'Web Development Essentials', '2024-02-18'),
    (105, 5, 'Digital Marketing Fundamentals', '2024-02-05'),
    (106, 1, 'Introduction to Machine Learning', '2024-02-15'),
    (107, 2, 'Advanced Python Programming', '2024-02-11'),
    (108, 3, 'Deep Learning Concepts', '2024-02-26'),
    (109, 4, 'Full Stack Web Development', '2024-03-02'),
    (110, 5, 'Social Media Marketing Strategies', '2024-03-08');

INSERT INTO StudentFeedback 
VALUES 
    (201, 1, 'Introduction to Programming', 'Great course for beginners!', 5, '2024-01-16'),
    (202, 2, 'Data Science Fundamentals', 'Excellent content and clear explanations.', 4, '2024-01-21'),
    (203, 3, 'Machine Learning Basics', 'Really enjoyed learning about ML algorithms.', 5, '2024-02-02'),
    (204, 4, 'Web Development Essentials', 'Very helpful for building basic websites.', 4, '2024-02-19'),
    (205, 5, 'Digital Marketing Fundamentals', 'Informative and engaging lectures.', 5, '2024-02-06'),
    (206, 1, 'Introduction to Machine Learning', 'The instructor explains concepts very well.', 5, '2024-02-16'),
    (207, 2, 'Advanced Python Programming', 'Challenging but rewarding course.', 4, '2024-02-12'),
    (208, 3, 'Deep Learning Concepts', 'Thorough coverage of deep learning topics.', 5, '2024-02-27'),
    (209, 4, 'Full Stack Web Development', 'Practical projects help reinforce learning.', 5, '2024-03-03'),
    (210, 5, 'Social Media Marketing Strategies', 'Useful strategies for promoting businesses online.', 4, '2024-03-09');
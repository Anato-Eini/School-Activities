CREATE TABLE StudentScores (
    student_id INT AUTO_INCREMENT PRIMARY KEY,
    student_name VARCHAR(100),
    subject VARCHAR(50),
    score DECIMAL(5, 2),
    exam_date DATE
);

INSERT INTO StudentScores (student_name, subject, score, exam_date) 
VALUES
('Alice', 'Math', 95.50, '2024-01-10'),
('Bob', 'Science', 92.00, '2024-02-15'),
('Charlie', 'History', 85.00, '2024-03-20'),
('David', 'English', 88.00, '2024-04-05'),
('Alice', 'Biology', 91.00, '2024-05-10'),
('Bob', 'Physics', 90.50, '2024-06-15'),
('Eva', 'Chemistry', 82.00, '2024-07-20'),
('Frank', 'Math', 89.00, '2024-08-05'),
('Alice', 'Algebra', 94.00, '2024-09-10'),
('Bob', 'Statistics', 87.00, '2024-10-15');
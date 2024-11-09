CREATE TABLE StudentScores (
    student_id INT AUTO_INCREMENT PRIMARY KEY,
    student_name VARCHAR(255),
    subject VARCHAR(255),
    score DECIMAL(10, 2),
    exam_date DATE
);

INSERT INTO StudentScores (student_name, subject, score, exam_date)
VALUES
('John Doe', 'Mathematics', 95.00, '2023-05-15'),
('Jane Smith', 'Science', 88.50, '2023-08-20'),
('Emily Johnson', 'Mathematics', 91.00, '2023-06-10'),
('Michael Brown', 'History', 78.00, '2023-07-01'),
('Sarah Davis', 'Physics', 85.50, '2023-09-05'),
('Daniel Martinez', 'Mathematics', 92.75, '2023-10-15'),
('Lisa White', 'Chemistry', 82.25, '2023-11-20'),
('Kevin Wilson', 'Biology', 89.00, '2023-12-01'),
('Olivia Taylor', 'Mathematics', 94.25, '2024-01-15'),
('James Anderson', 'Physics', 87.00, '2024-02-10'),
('Jessica Thomas', 'Chemistry', 90.75, '2024-03-05');

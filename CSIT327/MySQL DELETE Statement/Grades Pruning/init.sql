CREATE TABLE StudentGrades (
    student_id INT AUTO_INCREMENT PRIMARY KEY,
    student_name VARCHAR(100),
    subject VARCHAR(100),
    grade DECIMAL(5, 2),
    exam_date DATE
);

INSERT INTO StudentGrades (student_name, subject, grade, exam_date)
VALUES
('Alice', 'Math', 85.50, '2023-12-10'),
('Bob', 'History', 78.00, '2023-12-08'),
('Charlie', 'Science', 92.75, '2023-12-09'),
('David', 'Literature', 65.25, '2023-12-10'),
('Alice', 'Physics', 88.00, '2023-12-07');

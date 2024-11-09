CREATE TABLE Students (
    student_id INT AUTO_INCREMENT PRIMARY KEY,
    student_name VARCHAR(100),
    age INT,
    grade VARCHAR(10),
    city VARCHAR(100)
);

INSERT INTO Students (student_name, age, grade, city) VALUES
('John Smith', 17, 'A', 'New York'),
('Jane Doe', 18, 'B', 'Los Angeles'),
('Michael Johnson', 20, 'A', 'Chicago'),
('Emily Brown', 17, 'C', 'Houston'),
('David Wilson', 19, 'B', 'Phoenix'),
('Jennifer Lee', 21, 'A', 'Philadelphia');

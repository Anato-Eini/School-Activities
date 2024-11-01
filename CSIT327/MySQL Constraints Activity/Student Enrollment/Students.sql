CREATE TABLE Students (
    student_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    age INT CHECK (age >= 18 AND age <= 25),
    major VARCHAR(255) NOT NULL,
    enrollment_date DATE DEFAULT "2024-01-01"
);


INSERT INTO Students (name, age, major, enrollment_date) VALUES 
    ("John Doe", 20, "Computer Science", "2024-02-22"),
    ("Jane Smith", 22, "Mathematics", "2024-02-22"),
    ("Alex Johnson", 21, "Biology", "2024-02-22");

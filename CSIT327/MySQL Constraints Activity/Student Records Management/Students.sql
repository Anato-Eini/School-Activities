CREATE TABLE Students (
    student_id INT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(255) NOT NULL UNIQUE,
    name VARCHAR(255) NOT NULL,
    major VARCHAR(255) NOT NULL,
    gpa DECIMAL(3, 2) CHECK (gpa between 0.00 and 4.00),
    enrollment_date DATE DEFAULT "2024-01-01"
);

INSERT INTO Students (email, name, major, gpa, enrollment_date) VALUES ("test@example.com", "Test Student", "Computer Science", 3.50, "2024-02-22"), ("john@example.com", "John Doe", "Mathematics", 3.20, "2024-02-22"), ("jane@example.com", "Jane Smith", "Biology", 3.70, "2024-02-22"), ("alex@example.com", "Alex Johnson", "Chemistry", 3.90, "2024-02-22"), ("emily@example.com", "Emily Taylor", "Physics", 3.60, "2024-02-22");


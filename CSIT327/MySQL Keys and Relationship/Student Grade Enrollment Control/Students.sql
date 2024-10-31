CREATE TABLE Students (
    student_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100),
    index(name),
    grade VARCHAR(10),
    UNIQUE (name, grade)
);

INSERT INTO Students (name, grade) VALUES ("John Doe", "12");

INSERT INTO Students (name, grade) VALUES ("Jane Doe", "11");

INSERT INTO Students (name, grade) VALUES ("Mike Smith", "10");
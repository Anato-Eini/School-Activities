CREATE TABLE Universities (
    university_id INT PRIMARY KEY,
    university_name VARCHAR(100),
    location VARCHAR(100),
    foundation_year INT
);

CREATE TABLE Students (
    student_id INT PRIMARY KEY,
    first_name VARCHAR(50),
    last_name VARCHAR(50),
    university_id INT,
    major VARCHAR(100),
    enrollment_year INT,
    FOREIGN KEY (university_id) REFERENCES Universities(university_id)
);

INSERT INTO Universities (university_id, university_name, location, foundation_year) VALUES
(1, 'Harvard University', 'Cambridge, MA', 1636),
(2, 'University of Oxford', 'Oxford, England', 1096),
(3, 'Stanford University', 'Stanford, CA', 1885),
(4, 'University of Tokyo', 'Tokyo, Japan', 1877),
(5, 'University of Cape Town', 'Cape Town, South Africa', 1829),
(6, 'University of Sydney', 'Sydney, Australia', 1850);

INSERT INTO Students (student_id, first_name, last_name, university_id, major, enrollment_year) VALUES
(1, 'John', 'Doe', 1, 'Computer Science', 2019),
(2, 'Jane', 'Smith', 2, 'History', 2020),
(3, 'Michael', 'Brown', 3, 'Electrical Engineering', 2018),
(4, 'Maria', 'Garcia', 4, 'Physics', 2017),
(5, 'David', 'Lee', 5, 'Biology', 2019),
(6, 'Ana', 'Silva', 6, 'Psychology', 2020),
(7, 'Ahmed', 'Khan', 1, 'Economics', 2016),
(8, 'Olga', 'Ivanova', 2, 'Mathematics', 2017),
(9, 'Juan', 'Lopez', 3, 'Computer Engineering', 2019),
(10, 'Fatima', 'Mohamed', 4, 'Chemistry', 2018),
(11, 'Emily', 'Anderson', 5, 'Medicine', 2020),
(12, 'Pedro', 'Santos', 6, 'Business Administration', 2017),
(13, 'Sophia', 'Kim', 1, 'Political Science', 2018),
(14, 'Mohammed', 'Ali', 2, 'Literature', 2019),
(15, 'Isabella', 'Martinez', 3, 'Mechanical Engineering', 2016),
(16, 'Mark', 'Pausanos', 1, 'Computer Engineering', 2015),
(17, 'David', 'Maranga', 2, 'Civil Engineering', 2016),
(18, 'Johnray', 'Acojedo', 3, 'Mechanical Engineering', 2017);
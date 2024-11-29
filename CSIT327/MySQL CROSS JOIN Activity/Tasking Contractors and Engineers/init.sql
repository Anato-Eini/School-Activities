CREATE TABLE Tasks (
    task_id INT PRIMARY KEY,
    task_name VARCHAR(255) NOT NULL,
    priority VARCHAR(100) NOT NULL,
    deadline DATE NOT NULL
);

CREATE TABLE Contractors (
    contractor_id INT PRIMARY KEY,
    contractor_name VARCHAR(255) NOT NULL,
    location VARCHAR(100) NOT NULL,
    rating DECIMAL(3,1)
);

CREATE TABLE Engineers (
    engineer_id INT PRIMARY KEY,
    engineer_name VARCHAR(255) NOT NULL,
    specialization VARCHAR(255) NOT NULL,
    experience_years INT NOT NULL
);

INSERT INTO Tasks (task_id, task_name, priority, deadline) VALUES
(1, 'Design website layout', 'High', '2024-03-15'),
(2, 'Develop backend logic', 'High', '2024-03-20'),
(3, 'Create database schema', 'High', '2024-03-18'),
(4, 'Test website functionality', 'Medium', '2024-03-22'),
(5, 'Optimize performance', 'Medium', '2024-03-25'),
(6, 'Write user documentation', 'Low', '2024-03-28'),
(7, 'Prepare deployment plan', 'High', '2024-03-30'),
(8, 'Implement security measures', 'Medium', '2024-03-25'),
(9, 'Perform load testing', 'High', '2024-03-23'),
(10, 'Conduct user acceptance testing', 'Medium', '2024-03-27'),
(11, 'Prepare release notes', 'Low', '2024-03-29'),
(12, 'Configure server settings', 'Medium', '2024-03-21'),
(13, 'Update software dependencies', 'High', '2024-03-26'),
(14, 'Fix bugs reported by QA', 'High', '2024-03-24'),
(15, 'Create automated tests', 'Medium', '2024-03-20'),
(16, 'Deploy website to production', 'High', '2024-03-31'),
(17, 'Monitor website performance', 'Low', '2024-04-02'),
(18, 'Backup website data', 'Low', '2024-04-05'),
(19, 'Implement caching mechanisms', 'Medium', '2024-04-03'),
(20, 'Handle user support requests', 'Medium', '2024-04-08'),
(21, 'Update website content', 'Low', '2024-04-10');

INSERT INTO Contractors (contractor_id, contractor_name, location, rating) VALUES
(1, 'ABC Construction', 'New York', 4.5),
(2, 'XYZ Builders', 'Los Angeles', 4.2),
(3, 'PQR Developers', 'Chicago', 4.6),
(4, 'LMN Contractors', 'Houston', 4.3),
(5, 'DEF Engineering', 'San Francisco', 4.8),
(6, 'GHI Construction', 'Miami', 4.4),
(7, 'JKL Builders', 'Seattle', 4.1);

INSERT INTO Engineers (engineer_id, engineer_name, specialization, experience_years) VALUES
(1, 'John Smith', 'Software Development', 8),
(2, 'Emily Davis', 'Network Security', 6),
(3, 'Michael Johnson', 'Database Administration', 9),
(4, 'Jessica Brown', 'Web Development', 7),
(5, 'Andrew Wilson', 'Machine Learning', 5),
(6, 'Sarah Martinez', 'Embedded Systems', 8),
(7, 'Kevin Taylor', 'Cloud Computing', 6);
CREATE TABLE Projects (
    project_id INT PRIMARY KEY,
    project_name VARCHAR(255) NOT NULL,
    start_date DATE NOT NULL,
    end_date DATE NOT NULL
);

CREATE TABLE Teams (
    team_id INT PRIMARY KEY,
    team_name VARCHAR(255) NOT NULL,
    location VARCHAR(100) NOT NULL,
    number_of_members INT NOT NULL
);

CREATE TABLE Managers (
    manager_id INT PRIMARY KEY,
    manager_name VARCHAR(255) NOT NULL,
    department VARCHAR(255) NOT NULL,
    years_of_experience INT NOT NULL
);

INSERT INTO Projects (project_id, project_name, start_date, end_date) VALUES
(1, 'Website Redesign', '2024-03-01', '2024-06-30'),
(2, 'Marketing Campaign', '2024-04-15', '2024-07-15'),
(3, 'Product Launch', '2024-05-10', '2024-09-30'),
(4, 'Customer Survey', '2024-06-01', '2024-08-31'),
(5, 'Software Upgrade', '2024-07-15', '2024-10-31'),
(6, 'Event Planning', '2024-08-01', '2024-11-30'),
(7, 'Training Program', '2024-09-15', '2024-12-31'),
(8, 'Market Research', '2024-10-01', '2025-01-31'),
(9, 'Quality Assurance', '2024-11-15', '2025-03-15'),
(10, 'Strategic Planning', '2024-12-01', '2025-04-30'),
(11, 'Budget Analysis', '2025-01-15', '2025-05-31'),
(12, 'Customer Support', '2025-02-01', '2025-06-30'),
(13, 'Inventory Management', '2025-03-15', '2025-07-31'),
(14, 'Product Development', '2025-04-01', '2025-08-31'),
(15, 'Sales Promotion', '2025-05-15', '2025-09-30');

INSERT INTO Teams (team_id, team_name, location, number_of_members) VALUES
(1, 'Marketing Team', 'New York', 10),
(2, 'Development Team', 'San Francisco', 15),
(3, 'Sales Team', 'Chicago', 8),
(4, 'Customer Service Team', 'Los Angeles', 12),
(5, 'Research Team', 'Seattle', 7);

INSERT INTO Managers (manager_id, manager_name, department, years_of_experience) VALUES
(1, 'John Smith', 'Marketing', 8),
(2, 'Emily Davis', 'Development', 10),
(3, 'Michael Johnson', 'Sales', 7),
(4, 'Jessica Brown', 'Customer Service', 9),
(5, 'Andrew Wilson', 'Research', 6);
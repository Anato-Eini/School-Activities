CREATE TABLE Employees (
    employee_id INT PRIMARY KEY,
    employee_name VARCHAR(100),
    department VARCHAR(50),
    position VARCHAR(50)
);

CREATE TABLE WorkHours (
    workhour_id INT PRIMARY KEY,
    employee_id INT,
    date DATE,
    hours_worked DECIMAL(5,2),
    FOREIGN KEY (employee_id) REFERENCES Employees(employee_id)
);

INSERT INTO Employees (employee_id, employee_name, department, position) VALUES
(101, 'John Doe', 'Sales', 'Sales Associate'),
(102, 'Jane Smith', 'Marketing', 'Marketing Specialist'),
(103, 'Michael Johnson', 'IT', 'Software Developer'),
(104, 'Emily Brown', 'HR', 'HR Coordinator'),
(105, 'David Lee', 'Finance', 'Financial Analyst'),
(106, 'Sarah White', 'Sales', 'Sales Manager'),
(107, 'Chris Wilson', 'IT', 'System Administrator'),
(108, 'Jessica Taylor', 'Marketing', 'Marketing Manager'),
(109, 'Kevin Martinez', 'Finance', 'Finance Manager'),
(110, 'Amanda Jones', 'HR', 'HR Manager');

INSERT INTO WorkHours (workhour_id, employee_id, date, hours_worked) VALUES
(201, 101, '2023-01-01', 8.5),
(202, 102, '2023-01-01', 7.5),
(203, 103, '2023-01-01', 9),
(204, 104, '2023-01-01', 8),
(205, 105, '2023-01-01', 7.5),
(206, 106, '2023-01-01', 9),
(207, 107, '2023-01-01', 8.5),
(208, 108, '2023-01-01', 7.5),
(209, 109, '2023-01-01', 9),
(210, 110, '2023-01-01', 8.5),
(211, 101, '2023-01-02', 7.5),
(212, 102, '2023-01-02', 8),
(213, 103, '2023-01-02', 8),
(214, 104, '2023-01-02', 7),
(215, 105, '2023-01-02', 8.5),
(216, 106, '2023-01-02', 9),
(217, 107, '2023-01-02', 7.5),
(218, 108, '2023-01-02', 8),
(219, 109, '2023-01-02', 8.5),
(220, 110, '2023-01-02', 7),
(221, 101, '2023-01-03', 9),
(222, 102, '2023-01-03', 7),
(223, 103, '2023-01-03', 8),
(224, 104, '2023-01-03', 8.5),
(225, 105, '2023-01-03', 7),
(226, 106, '2023-01-03', 7.5),
(227, 107, '2023-01-03', 8),
(228, 108, '2023-01-03', 8.5),
(229, 109, '2023-01-03', 9),
(230, 110, '2023-01-03', 7),
(231, 101, '2023-01-04', 8),
(232, 102, '2023-01-04', 8.5),
(233, 103, '2023-01-04', 7),
(234, 104, '2023-01-04', 9),
(235, 105, '2023-01-04', 7.5),
(236, 106, '2023-01-04', 8),
(237, 107, '2023-01-04', 9),
(238, 108, '2023-01-04', 7),
(239, 109, '2023-01-04', 8.5),
(240, 110, '2023-01-04', 8);
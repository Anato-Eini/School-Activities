CREATE TABLE EmployeeSalaries (
    employee_id INT AUTO_INCREMENT PRIMARY KEY,
    employee_name VARCHAR(100),
    department VARCHAR(50),
    salary DECIMAL(10, 2)
);

INSERT INTO EmployeeSalaries (employee_name, department, salary)
VALUES
('John Doe', 'Engineering', 85000.00),
('Emily Clark', 'Sales', 75000.00),
('Michael Smith', 'Finance', 95000.00),
('Anna Johnson', 'Sales', 70000.00),
('Robert Brown', 'Engineering', 80000.00),
('Laura Wilson', 'Human Resources', 90000.00),
('Linda Taylor', 'Sales', 68000.00),
('James Moore', 'Customer Service', 60000.00),
('Patricia Jones', 'Finance', 105000.00),
('David Miller', 'Management', 120000.00);

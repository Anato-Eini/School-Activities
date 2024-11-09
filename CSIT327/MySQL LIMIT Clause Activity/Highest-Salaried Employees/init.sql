CREATE TABLE EmployeeData (
    employee_id INT AUTO_INCREMENT PRIMARY KEY,
    employee_name VARCHAR(255),
    department VARCHAR(255),
    salary DECIMAL(10, 2),
    hire_date DATE
);

INSERT INTO EmployeeData (employee_name, department, salary, hire_date)
VALUES
('John Doe', 'Engineering', 85000.00, '2023-05-15'),
('Jane Smith', 'Marketing', 75000.00, '2023-08-20'),
('Emily Johnson', 'Engineering', 90000.00, '2023-06-10'),
('Michael Brown', 'Sales', 70000.00, '2023-07-01'),
('Sarah Davis', 'Engineering', 82000.00, '2023-09-05'),
('Daniel Martinez', 'Marketing', 80000.00, '2023-10-15'),
('Lisa White', 'Sales', 78000.00, '2023-11-20'),
('Kevin Wilson', 'Engineering', 95000.00, '2023-12-01'),
('Olivia Taylor', 'Marketing', 83000.00, '2024-01-15'),
('James Anderson', 'Sales', 76000.00, '2024-02-10'),
('Jessica Thomas', 'Engineering', 88000.00, '2024-03-05'),
('William Garcia', 'Marketing', 87000.00, '2024-04-20');

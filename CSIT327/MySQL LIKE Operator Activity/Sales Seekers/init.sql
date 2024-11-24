CREATE TABLE Employees (
    employee_id INT AUTO_INCREMENT PRIMARY KEY,
    employee_name VARCHAR(255),
    department VARCHAR(100),
    salary DECIMAL(10, 2),
    hire_date DATE
);

INSERT INTO Employees (employee_name, department, salary, hire_date) 
VALUES 
('John Doe', 'Sales', 55000.00, '2022-05-15'),
('Jane Smith', 'Marketing', 60000.00, '2023-01-10'),
('Michael Johnson', 'Sales', 65000.00, '2023-03-20'),
('Emily Brown', 'IT', 70000.00, '2022-11-30'),
('David Manager', 'Sales', 75000.00, '2023-06-25'),
('Sarah Lee', 'Finance', 80000.00, '2021-09-05'),
('Alexa Manager', 'HR', 85000.00, '2023-12-15'),
('Ryan Green', 'Sales', 90000.00, '2022-08-10'),
('Olivia Taylor', 'IT', 95000.00, '2023-09-20'),
('Daniel Clark', 'Sales', 100000.00, '2022-04-01');
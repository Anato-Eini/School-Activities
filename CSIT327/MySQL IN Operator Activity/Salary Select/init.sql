CREATE TABLE EmployeeRecords (
    employee_id INT AUTO_INCREMENT PRIMARY KEY,
    employee_name VARCHAR(100),
    department VARCHAR(100),
    position VARCHAR(100),
    salary DECIMAL(10, 2),
    hire_date DATE
);

INSERT INTO EmployeeRecords (employee_id, employee_name, department, position, salary, hire_date) 
VALUES
(1, 'John Smith', 'Sales', 'Manager', 70000.00, '2022-12-10'),
(2, 'Alice Johnson', 'Marketing', 'Assistant', 55000.00, '2023-01-15'),
(3, 'Kevin Brown', 'Sales', 'Assistant', 45000.00, '2023-02-20'),
(4, 'Lisa Garcia', 'HR', 'HR Manager', 80000.00, '2023-03-25'),
(5, 'Michael Lee', 'Engineering', 'Engineer', 65000.00, '2023-04-30'),
(6, 'Karen Davis', 'Marketing', 'Manager', 60000.00, '2023-05-05'),
(7, 'Jake Miller', 'HR', 'Assistant', 38000.00, '2023-06-10'),
(8, 'Laura Martinez', 'Sales', 'Assistant', 50000.00, '2023-07-15');
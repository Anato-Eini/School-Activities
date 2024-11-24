CREATE TABLE Employees (
    employee_id INT AUTO_INCREMENT PRIMARY KEY,
    full_name VARCHAR(255),
    email VARCHAR(255),
    department VARCHAR(100),
    hire_date DATE,
    salary DECIMAL(10, 2),
    manager_id INT
);

INSERT INTO Employees (full_name, email, department, hire_date, salary, manager_id) 
VALUES 
('John Smith', 'john.smith@company.com', 'Sales', '2022-05-10', 65000.00, NULL),
('Emily Johnson', 'emily.johnson@email.com', 'HR', '2023-01-15', 55000.00, 1),
('Michael Brown', 'michael.brown@company.com', 'Sales', '2023-04-20', 70000.00, 1),
('Sarah Davis', 'sarah.davis@company.com', 'Engineering', '2023-06-30', 60000.00, 3),
('David Wilson', 'david.wilson@email.com', 'Sales', '2024-02-10', 60000.00, 1),
('Jessica Martinez', 'jessica.martinez@company.com', 'Marketing', '2022-09-05', 48000.00, 5),
('Daniel Taylor', 'daniel.taylor@email.com', 'Engineering', '2024-01-20', 68000.00, 3),
('Emma Anderson', 'emma.anderson@company.com', 'Sales', '2022-11-08', 62000.00, 4);
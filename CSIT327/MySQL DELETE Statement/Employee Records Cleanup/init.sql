CREATE TABLE EmployeeRecords (
    employee_id INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(50),
    last_name VARCHAR(50),
    department VARCHAR(50),
    position VARCHAR(50),
    hire_date DATE,
    salary DECIMAL(10, 2),
    email VARCHAR(100),
    phone_number VARCHAR(20)
);

INSERT INTO EmployeeRecords (first_name, last_name, department, position, hire_date, salary, email, phone_number)
VALUES
('John', 'Doe', 'Sales', 'Sales Associate', '2020-01-15', 50000.00, 'john.doe@example.com', '555-1234'),
('Jane', 'Smith', 'Marketing', 'Marketing Manager', '2018-05-20', 75000.00, 'jane.smith@example.com', '555-5678'),
('Michael', 'Johnson', 'IT', 'Software Engineer', '2019-08-10', 80000.00, 'michael.johnson@example.com', '555-9012'),
('Emily', 'Williams', 'HR', 'HR Specialist', '2021-03-05', 60000.00, 'emily.williams@example.com', '555-3456'),
('David', 'Brown', 'Finance', 'Financial Analyst', '2022-02-28', 70000.00, 'david.brown@example.com', '555-7890');

CREATE TABLE EmployeeRecords (
    employee_id INT AUTO_INCREMENT PRIMARY KEY,
    full_name VARCHAR(100),
    birth_date DATE,
    hire_date DATE,
    job_title VARCHAR(50),
    department VARCHAR(50),
    salary DECIMAL(10, 2),
    email VARCHAR(100),
    phone VARCHAR(15)
);

INSERT INTO EmployeeRecords (full_name, birth_date, hire_date, job_title, department, salary, email, phone)
VALUES
('John Doe', '1985-03-15', '2010-07-20', 'Software Engineer', 'Engineering', 75000.00, 'john.doe@example.com', '123456789'),
('Emily Clark', '1991-12-10', '2015-05-12', 'Marketing Manager', 'Marketing', 85000.00, 'emily.clark@example.com', '987654321'),
('Michael Smith', '1978-06-25', '2005-03-08', 'Senior Analyst', 'Finance', 90000.00, 'michael.smith@example.com', '555555555'),
('Anna Johnson', '1989-09-30', '2018-08-14', 'Sales Representative', 'Sales', 65000.00, 'anna.johnson@example.com', '111111111'),
('Robert Brown', '1995-02-28', '2020-11-02', 'Junior Developer', 'Engineering', 60000.00, NULL, '999999999'),
('Laura Wilson', '1982-11-20', '2012-09-05', 'HR Manager', 'Human Resources', 80000.00, 'laura.wilson@example.com', '777777777'),
('Linda Taylor', '1987-07-05', '2017-04-30', 'Sales Associate', 'Sales', 55000.00, 'linda.taylor@example.com', NULL),
('James Moore', '1993-04-18', '2019-10-10', 'Customer Support Specialist', 'Customer Service', 50000.00, 'james.moore@example.com', '888888888'),
('Patricia Jones', '1986-08-12', '2014-02-22', 'Financial Analyst', 'Finance', 85000.00, 'patricia.jones@example.com', '444444444'),
('David Miller', '1975-10-29', '2008-12-15', 'Senior Manager', 'Management', 120000.00, 'david.miller@example.com', '666666666');

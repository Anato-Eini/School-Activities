CREATE TABLE EmployeeData (
    employee_id INT AUTO_INCREMENT PRIMARY KEY,
    full_name VARCHAR(100),
    department VARCHAR(50),
    salary DECIMAL(10,2),
    hire_date DATE,
    manager_id INT
);

INSERT INTO EmployeeData (full_name, department, salary, hire_date, manager_id) VALUES
('John Smith', 'Sales', 75000.00, '2017-05-15', NULL),
('Jane Doe', 'Marketing', 85000.00, '2018-03-20', NULL),
('Michael Johnson', 'Sales', 90000.00, '2016-08-10', 1),
('Emily Brown', 'HR', 65000.00, '2019-01-05', NULL),
('David Wilson', 'Marketing', 80000.00, '2017-10-25', 2),
('Jennifer Lee', 'IT', 95000.00, '2015-06-30', NULL),
('Christopher Davis', 'Sales', 70000.00, '2020-02-12', 3),
('Jessica Martinez', 'IT', 105000.00, '2014-09-18', 6),
('Andrew Taylor', 'Marketing', 75000.00, '2018-07-15', 2),
('Elizabeth Anderson', 'HR', 60000.00, '2020-04-01', 4),
('Daniel Thomas', 'IT', 98000.00, '2017-12-10', 6),
('Sarah White', 'Sales', 82000.00, '2019-08-05', 1),
('Kevin Garcia', 'HR', 70000.00, '2016-03-08', 5),
('Laura Martinez', 'Marketing', 188000.00, '2017-04-22', 4),
('Robert Lopez', 'IT', 193000.00, '2018-11-15', 9),
('Amanda Harris', 'Sales', 128000.00, '2018-09-30', 1);

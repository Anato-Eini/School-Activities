CREATE TABLE EmployeeSalaries (
    employee_id INT AUTO_INCREMENT PRIMARY KEY,
    employee_name VARCHAR(100),
    department VARCHAR(50),
    salary DECIMAL(10,2),
    hire_date DATE
);

INSERT INTO EmployeeSalaries (employee_name, department, salary, hire_date) VALUES
('John Smith', 'Sales', 75000.00, '2017-05-15'),
('Jane Doe', 'Marketing', 85000.00, '2018-03-20'),
('Michael Johnson', 'Sales', 90000.00, '2016-08-10'),
('Emily Brown', 'HR', 65000.00, '2019-01-05'),
('David Wilson', 'Marketing', 80000.00, '2017-10-25'),
('Jennifer Lee', 'IT', 95000.00, '2015-06-30'),
('Christopher Davis', 'Sales', 70000.00, '2020-02-12'),
('Jessica Martinez', 'IT', 105000.00, '2014-09-18'),
('Andrew Taylor', 'Marketing', 75000.00, '2018-07-15'),
('Elizabeth Anderson', 'HR', 60000.00, '2020-04-01'),
('Daniel Thomas', 'IT', 98000.00, '2017-12-10'),
('Sarah White', 'Sales', 82000.00, '2019-08-05'),
('Kevin Garcia', 'HR', 70000.00, '2016-03-08'),
('Laura Martinez', 'Marketing', 88000.00, '2017-04-22'),
('Robert Lopez', 'IT', 93000.00, '2018-11-15'),
('Amanda Harris', 'Sales', 78000.00, '2018-09-30');

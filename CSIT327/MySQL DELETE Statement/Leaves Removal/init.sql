CREATE TABLE EmployeeLeaves (
    leave_id INT AUTO_INCREMENT PRIMARY KEY,
    employee_name VARCHAR(100),
    leave_type VARCHAR(50),
    start_date DATE,
    end_date DATE
);

INSERT INTO EmployeeLeaves (employee_name, leave_type, start_date, end_date)
VALUES
('Alice', 'Vacation', '2023-12-10', '2023-12-15'),
('Bob', 'Sick Leave', '2023-12-08', '2023-12-09'),
('Charlie', 'Maternity Leave', '2023-12-05', '2023-12-15'),
('David', 'Vacation', '2023-12-20', '2023-12-25'),
('Alice', 'Sick Leave', '2023-12-01', '2023-12-02');

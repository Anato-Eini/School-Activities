CREATE TABLE EmployeeAttendance (
    attendance_id INT AUTO_INCREMENT PRIMARY KEY,
    employee_id INT,
    date DATE,
    status VARCHAR(20)
);

INSERT INTO EmployeeAttendance (employee_id, date, status) 
VALUES 
(1, '2024-02-24', 'Present'),
(2, '2024-02-24', 'Absent'),
(3, '2024-02-24', 'Present'),
(1, '2024-02-25', 'Absent'),
(2, '2024-02-25', 'Present'),
(3, '2024-02-25', 'Present'),
(1, '2024-02-26', 'Present'),
(2, '2024-02-26', 'Absent'),
(3, '2024-02-26', 'Present'),
(1, '2024-02-27', 'Present'),
(2, '2024-02-27', 'Absent'),
(3, '2024-02-27', 'Present'),
(1, '2024-02-28', 'Absent'),
(2, '2024-02-28', 'Absent'),
(3, '2024-02-28', 'Absent'),
(1, '2024-02-29', 'Absent'),
(2, '2024-02-29', 'Absent'),
(3, '2024-02-29', 'Absent');
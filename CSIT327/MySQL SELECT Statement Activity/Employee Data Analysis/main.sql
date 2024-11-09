SELECT full_name, salary FROM EmployeeData ORDER BY salary DESC;

SELECT AVG(salary) AS average_salary FROM EmployeeData WHERE salary > 70000;

SELECT full_name FROM EmployeeData WHERE salary > 100000;

SELECT COUNT(*) AS num_employees FROM EmployeeData;


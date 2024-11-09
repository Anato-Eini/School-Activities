UPDATE EmployeeRecords SET job_title = "Senior Developer" WHERE employee_id = 5;

UPDATE EmployeeRecords SET salary = salary * 1.1 WHERE department = "Sales";

UPDATE EmployeeRecords SET hire_date = "1990-01-01" WHERE birth_date < "1990-01-01";

UPDATE EmployeeRecords SET phone = "N/A" WHERE email IS NUll;

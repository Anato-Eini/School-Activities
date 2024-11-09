UPDATE EmployeeSalaries SET salary = 70000 WHERE employee_id = 5;

UPDATE EmployeeSalaries SET salary = salary * 1.05 WHERE department = "Sales";

UPDATE EmployeeSalaries SET department = "Executive" WHERE salary > 100000;

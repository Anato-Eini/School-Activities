SELECT *
FROM EmployeeRecords
WHERE department IN ("Sales", "Marketing") AND position IN ("Manager", "Assistant");

SELECT *
FROM EmployeeRecords
WHERE salary IN (50000, 60000) AND hire_date IN (SELECT hire_date
    FROM EmployeeRecords
    WHERE hire_date LIKE "2023%");

SELECT *
FROM EmployeeRecords
WHERE position IN ("Engineer", "Technician");

SELECT *
FROM EmployeeRecords
WHERE department IN ("HR") AND salary IN (SELECT salary
    FROM EmployeeRecords
    WHERE salary < 40000 OR salary > 70000);
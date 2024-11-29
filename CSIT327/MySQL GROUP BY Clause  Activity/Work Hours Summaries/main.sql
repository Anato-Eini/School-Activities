SELECT Employees.department, SUM(WorkHours.hours_worked) AS total_hours_worked
FROM Employees JOIN WorkHours ON Employees.employee_id = WorkHours.employee_id
GROUP BY Employees.department;

SELECT Employees.position, AVG(WorkHours.hours_worked) AS avg_hours_worked
FROM Employees JOIN WorkHours ON Employees.employee_id = WorkHours.employee_id
GROUP BY Employees.position;
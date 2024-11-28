SELECT *
FROM EmployeeAttendance a
WHERE status = "Present" AND EXISTS (SELECT *
    FROM EmployeeAttendance b
    WHERE a.date = b.date AND b.status = "Absent");

SELECT *
FROM EmployeeAttendance a
WHERE status = "Absent" AND EXISTS (SELECT *
    FROM EmployeeAttendance b
    WHERE a.employee_id = b.employee_id AND b.status = "Present");
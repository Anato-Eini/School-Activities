SELECT Students.student_id, Students.first_name, Students.last_name, Courses.course_name
FROM Registrations LEFT JOIN Courses ON Registrations.course_id = Courses.course_id LEFT JOIN Students ON Registrations.student_id = Students.student_id;

SELECT Students.student_id, Students.first_name, Students.last_name, Courses.course_name
FROM Registrations LEFT JOIN Courses ON Registrations.course_id = Courses.course_id LEFT JOIN Students ON Registrations.student_id = Students.student_id
WHERE Students.major = "Psychology";

SELECT Students.student_id, Students.first_name, Students.last_name, Courses.course_name
FROM Registrations LEFT JOIN Courses ON Registrations.course_id = Courses.course_id LEFT JOIN Students ON Registrations.student_id = Students.student_id
WHERE Students.year_enrolled = 2021;

SELECT Students.student_id, Students.first_name, Students.last_name, Courses.course_name, Registrations.grade
FROM Registrations LEFT JOIN Courses ON Registrations.course_id = Courses.course_id LEFT JOIN Students ON Registrations.student_id = Students.student_id
WHERE Registrations.grade = "A";

SELECT Courses.course_id, Courses.course_name, Students.first_name, Students.last_name
FROM Registrations LEFT JOIN Courses ON Registrations.course_id = Courses.course_id LEFT JOIN Students ON Registrations.student_id = Students.student_id
WHERE semester = "Fall 2023" ORDER BY Courses.course_id;
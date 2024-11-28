SELECT Universities.university_name, Students.first_name, Students.last_name
FROM Universities INNER JOIN Students ON Universities.university_id = Students.university_id
ORDER BY Universities.university_name;

SELECT Students.first_name, Students.last_name, Students.major
FROM Students INNER JOIN Universities ON Students.university_id = Universities.university_id
WHERE Universities.foundation_year < 1800
ORDER BY Students.last_name;

SELECT Universities.university_name, Universities.location, Students.first_name, Students.last_name
FROM Universities INNER JOIN Students ON Universities.university_id = Students.university_id
WHERE Universities.foundation_year < 1900
ORDER BY Universities.location;
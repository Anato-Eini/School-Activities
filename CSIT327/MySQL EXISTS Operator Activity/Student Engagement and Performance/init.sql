SELECT *
FROM StudentRecords
WHERE EXISTS (SELECT *
FROM StudentRecords
WHERE attendance_rate = 90) AND grade_level = 10;

SELECT *
FROM StudentRecords
WHERE EXISTS (SELECT *
FROM StudentRecords
WHERE gpa > 3.8) AND extracurricular_activity IS NOT NULL;

SELECT *
FROM StudentRecords a
WHERE a.class_id LIKE "1%" AND EXISTS (SELECT *
    FROM StudentRecords b
    WHERE b.class_id LIKE "1%");

SELECT *
FROM StudentRecords 
WHERE gpa > 3.5 AND EXISTS (SELECT *
    FROM StudentRecords 
    WHERE extracurricular_activity = "Drama Club");

SELECT *
FROM StudentRecords
WHERE gender = "F" AND EXISTS (SELECT *
    FROM StudentRecords
    WHERE gender = "M" AND grade_level = 12);
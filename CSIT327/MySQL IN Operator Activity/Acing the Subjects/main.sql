SELECT *
FROM StudentScores
WHERE subject IN ("Math", "Science") AND score > 90.00;

SELECT *
FROM StudentScores
WHERE exam_date IN 
(SELECT exam_date
FROM StudentScores
WHERE 
exam_date LIKE "%-01-%" OR exam_date LIKE "%-02-%" OR exam_date LIKE "%-03-%");

SELECT *
FROM StudentScores
WHERE subject IN (SELECT subject
    FROM StudentScores
    WHERE subject LIKE "A%" OR subject LIKE "B%" OR subject LIKE "C%") AND student_name IN ("Alice", "Bob");
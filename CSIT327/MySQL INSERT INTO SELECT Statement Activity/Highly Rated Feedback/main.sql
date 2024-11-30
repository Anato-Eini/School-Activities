CREATE TABLE PositiveFeedback
(
    feedback_id INT PRIMARY KEY,
    student_id INT,
    course_name VARCHAR(255),
    feedback_text TEXT,
    rating INT,
    feedback_date DATE
)

INSERT INTO PositiveFeedback
    (feedback_id, student_id, course_name, feedback_text, rating, feedback_date)
SELECT feedback_id, student_id, course_name, feedback_text, rating, feedback_date
FROM StudentFeedback
WHERE rating >= 4;
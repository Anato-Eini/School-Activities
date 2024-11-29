SELECT membership_type
FROM GymData
GROUP BY membership_type
HAVING AVG(workout_duration_minutes) > 60;

SELECT trainer_name
FROM GymData
GROUP BY trainer_name
HAVING COUNT(trainer_name) < 5;

SELECT session_type
FROM GymData
GROUP BY session_type
HAVING AVG(calories_burned) > 200;
SELECT event_type
FROM EventInfo
GROUP BY event_type
HAVING AVG(attendee_id) > 50;

SELECT year(event_date) AS conference_year
FROM EventInfo
WHERE event_type = 'Conference'
GROUP BY conference_year;

SELECT attendee_id, attendee_name
FROM EventInfo
GROUP BY attendee_id, attendee_name
HAVING COUNT(attendee_id) < 5;

SELECT event_name, MAX(registration_date) AS latest_registration
FROM EventInfo GROUP BY event_name
HAVING latest_registration > '2023-01-01';
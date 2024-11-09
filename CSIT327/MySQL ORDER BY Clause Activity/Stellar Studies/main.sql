SELECT * FROM AstronomyObservations ORDER BY observation_date;

SELECT * FROM AstronomyObservations ORDER BY observation_time DESC;

SELECT * FROM AstronomyObservations ORDER BY celestial_object, observation_date;

SELECT * FROM AstronomyObservations ORDER BY equipment, observation_time;

SELECT * FROM AstronomyObservations ORDER BY observer_name, observation_date DESC;

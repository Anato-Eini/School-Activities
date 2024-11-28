SELECT Movies.title, Movies.director, Movies.genre, Actors.actor_name
FROM Movies LEFT JOIN Actors ON Movies.movie_id = Actors.movie_id;

SELECT Actors.actor_name, Actors.role, Movies.title AS movie_title
FROM Actors LEFT JOIN Movies ON Actors.movie_id = Movies.movie_id;
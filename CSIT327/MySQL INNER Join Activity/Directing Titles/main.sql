SELECT Movies.title, Actors.actor_name
FROM Movies INNER JOIN Actors ON Actors.movie_id = Movies.movie_id
ORDER BY Movies.title;
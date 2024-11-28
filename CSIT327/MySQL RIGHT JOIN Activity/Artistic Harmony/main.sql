SELECT Albums.album_id, Albums.album_title, Albums.total_tracks, Albums.release_year, Artists.name AS artist_name
FROM Albums RIGHT JOIN Artists ON Artists.artist_id = Albums.artist_id;

SELECT Songs.song_id, Songs.title, Songs.release_year, Songs.duration, Albums.album_title, Artists.name AS artist_name
FROM Songs RIGHT JOIN Albums ON Songs.album_id = Albums.album_id RIGHT JOIN Artists ON Artists.artist_id = Songs.artist_id
ORDER BY Songs.song_id;

SELECT Artists.artist_id, Artists.name, Songs.song_id, Songs.title, Songs.release_year
FROM Songs RIGHT JOIN Albums ON Songs.album_id = Albums.album_id RIGHT JOIN Artists ON Artists.artist_id = Songs.artist_id
WHERE Songs.release_year > 2010;

SELECT Albums.album_id, Albums.album_title, Albums.release_year, Artists.name AS artist_name
FROM Albums RIGHT JOIN Artists ON Artists.artist_id = Albums.artist_id
WHERE Albums.release_year < 2000;

SELECT Songs.song_id, Songs.title, Albums.album_title, Artists.name AS artist_name, Artists.genre
FROM Songs RIGHT JOIN Albums ON Songs.album_id = Albums.album_id RIGHT JOIN Artists ON Artists.artist_id = Songs.artist_id
WHERE Artists.genre = "Pop";
SELECT Albums.album_title, AVG(Songs.duration) AS average_duration
FROM Albums JOIN Songs ON Albums.album_id = Songs.album_id
GROUP BY Albums.album_title;

SELECT Artists.artist_name, COUNT(Albums.album_id) AS album_count
FROM Artists JOIN Albums ON Artists.artist_id = Albums.artist_id 
GROUP BY Artists.artist_name;

SELECT Albums.album_title, COUNT(Songs.song_id) AS song_count
FROM Albums JOIN Songs ON Albums.album_id = Songs.album_id
GROUP BY Albums.album_title;

SELECT Artists.genre, AVG(Songs.duration) AS average_duration
FROM Artists JOIN Albums ON Artists.artist_id = Albums.artist_id JOIN Songs ON Albums.album_id = Songs.album_id
GROUP BY Artists.genre;
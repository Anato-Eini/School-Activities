SELECT Albums.album_name, Albums.artist, Albums.genre, Albums.release_year
FROM Albums LEFT JOIN Tracks ON Albums.album_id = Tracks.album_id;

SELECT Tracks.track_name, Tracks.duration, Albums.album_name
FROM Tracks LEFT JOIN Albums ON Tracks.album_id = Albums.album_id
WHERE Tracks.duration > "00:04:00";

SELECT Tracks.track_name, Tracks.duration, Albums.album_name
FROM Albums LEFT JOIN Tracks ON Albums.album_id = Tracks.album_id;
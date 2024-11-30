SELECT CONCAT(track_title, "/", artist_name) AS track_artist_concat
FROM MusicTracks;

SELECT track_id, LENGTH(album_title) AS album_title_length
FROM MusicTracks;

SELECT track_id, LEFT(track_title, 20) AS short_track_title
FROM MusicTracks;

SELECT UPPER(genre) AS uppercase_genre
FROM MusicTracks;
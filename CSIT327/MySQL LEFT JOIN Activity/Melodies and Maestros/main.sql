SELECT Songs.title, Songs.duration, Songs.genre, Artists.artist_name
FROM Songs LEFT JOIN Artists ON Songs.song_id = Artists.song_id;
CREATE TABLE MusicTracks (
    track_id INT PRIMARY KEY,
    track_title VARCHAR(255),
    artist_name VARCHAR(100),
    album_title VARCHAR(255),
    genre VARCHAR(50),
    duration TIME
);

INSERT INTO MusicTracks (track_id, track_title, artist_name, album_title, genre, duration) VALUES
(1, 'Bohemian Rhapsody', 'Queen', 'A Night at the Opera', 'rock', '00:05:55'),
(2, 'Billie Jean', 'Michael Jackson', 'Thriller', 'pop', '00:04:54'),
(3, 'Hotel California', 'Eagles', 'Hotel California', 'rock', '00:06:30'),
(4, 'Stairway to Heaven', 'Led Zeppelin', 'Led Zeppelin IV', 'rock', '00:08:02'),
(5, 'Thriller', 'Michael Jackson', 'Thriller', 'pop', '00:05:57'),
(6, 'Like a Rolling Stone', 'Bob Dylan', 'Highway 61 Revisited', 'rock', '00:06:13'),
(7, 'Imagine', 'John Lennon', 'Imagine', 'rock', '00:03:03'),
(8, 'Smells Like Teen Spirit', 'Nirvana', 'Nevermind', 'grunge', '00:05:01'),
(9, 'Yesterday', 'The Beatles', 'Help!', 'rock', '00:02:05'),
(10, 'Bohemian Rhapsody', 'Queen', 'A Night at the Opera', 'rock', '00:05:55'),
(11, 'Born to Run', 'Bruce Springsteen', 'Born to Run', 'rock', '00:04:30'),
(12, 'Hotel California', 'Eagles', 'Hotel California', 'rock', '00:06:30'),
(13, 'The Sound of Silence', 'Simon & Garfunkel', 'Wednesday Morning, 3 A.M.', 'folk', '00:03:05'),
(14, 'What a Wonderful World', 'Louis Armstrong', 'What a Wonderful World', 'jazz', '00:02:21'),
(15, 'Like a Prayer', 'Madonna', 'Like a Prayer', 'pop', '00:05:41');
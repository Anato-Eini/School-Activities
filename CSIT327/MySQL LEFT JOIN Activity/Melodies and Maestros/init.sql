CREATE TABLE Songs (
    song_id INT PRIMARY KEY,
    title VARCHAR(100),
    duration TIME,
    genre VARCHAR(50),
    release_year INT
);

CREATE TABLE Artists (
    artist_id INT PRIMARY KEY,
    song_id INT,
    artist_name VARCHAR(100),
    debut_year INT,
    FOREIGN KEY (song_id) REFERENCES Songs(song_id)
);


INSERT INTO Songs (song_id, title, duration, genre, release_year) VALUES
(1, 'Shape of You', '00:03:53', 'Pop', 2017),
(2, 'Bohemian Rhapsody', '00:05:55', 'Rock', 1975),
(3, 'Billie Jean', '00:04:54', 'Pop', 1982),
(4, 'Hey Jude', '00:07:11', 'Rock', 1968),
(5, 'Hotel California', '00:06:30', 'Rock', 1976),
(6, 'Imagine', '00:03:03', 'Rock', 1971);

INSERT INTO Artists (artist_id, song_id, artist_name, debut_year) VALUES
(101, 1, 'Ed Sheeran', 2004),
(102, 2, 'Queen', 1970),
(103, 3, 'Michael Jackson', 1964),
(104, 4, 'The Beatles', 1960),
(105, 5, 'Eagles', 1971),
(106, 6, 'John Lennon', 1969),
(107, 1, 'Beyoncé', 1997),
(108, 2, 'Freddie Mercury', 1969),
(109, 3, 'Stevie Wonder', 1961),
(110, 4, 'Paul McCartney', 1957),
(111, 5, 'Don Henley', 1970),
(112, 6, 'Paul McCartney', 1957),
(113, 1, 'Beyoncé', 1997),
(114, 2, 'Brian May', 1965),
(115, 3, 'Paul McCartney', 1957),
(116, 4, 'John Lennon', 1969),
(117, 5, 'Glenn Frey', 1970),
(118, 6, 'Yoko Ono', 1966);
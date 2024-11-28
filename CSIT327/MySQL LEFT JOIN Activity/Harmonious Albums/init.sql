CREATE TABLE Albums (
    album_id INT PRIMARY KEY,
    album_name VARCHAR(100),
    artist VARCHAR(100),
    genre VARCHAR(50),
    release_year INT
);

CREATE TABLE Tracks (
    track_id INT PRIMARY KEY,
    album_id INT,
    track_name VARCHAR(100),
    duration TIME,
    track_number INT,
    FOREIGN KEY (album_id) REFERENCES Albums(album_id)
);

INSERT INTO Albums (album_id, album_name, artist, genre, release_year) VALUES
(1, 'Thriller', 'Michael Jackson', 'Pop', 1982),
(2, 'Dark Side of the Moon', 'Pink Floyd', 'Progressive Rock', 1973),
(3, 'Back in Black', 'AC/DC', 'Hard Rock', 1980),
(4, 'Rumours', 'Fleetwood Mac', 'Rock', 1977),
(5, 'Abbey Road', 'The Beatles', 'Rock', 1969);

INSERT INTO Tracks (track_id, album_id, track_name, duration, track_number) VALUES
(101, 1, 'Wanna Be Startin’ Somethin’', '00:06:03', 1),
(102, 1, 'Baby Be Mine', '00:04:20', 2),
(103, 1, 'The Girl Is Mine', '00:03:42', 3),
(104, 2, 'Speak to Me', '00:01:07', 1),
(105, 2, 'Breathe', '00:02:44', 2),
(106, 2, 'On the Run', '00:03:35', 3),
(107, 3, 'Hells Bells', '00:05:10', 1),
(108, 3, 'Shoot to Thrill', '00:05:17', 2),
(109, 3, 'What Do You Do for Money Honey', '00:03:35', 3),
(110, 4, 'Second Hand News', '00:02:56', 1),
(111, 4, 'Dreams', '00:04:14', 2),
(112, 4, 'Never Going Back Again', '00:02:14', 3),
(113, 5, 'Come Together', '00:04:20', 1),
(114, 5, 'Something', '00:03:03', 2),
(115, 5, 'Maxwell’s Silver Hammer', '00:03:27', 3);
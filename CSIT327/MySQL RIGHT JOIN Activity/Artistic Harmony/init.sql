CREATE TABLE Artists (
    artist_id INT PRIMARY KEY,
    name VARCHAR(255),
    genre VARCHAR(50),
    country VARCHAR(50)
);

CREATE TABLE Albums (
    album_id INT PRIMARY KEY,
    album_title VARCHAR(255),
    artist_id INT,
    release_year INT,
    total_tracks INT,
    FOREIGN KEY (artist_id) REFERENCES Artists(artist_id)
);

CREATE TABLE Songs (
    song_id INT PRIMARY KEY,
    title VARCHAR(255),
    artist_id INT,
    album_id INT, 
    release_year INT,
    duration INT,
    FOREIGN KEY (artist_id) REFERENCES Artists(artist_id),
    FOREIGN KEY (album_id) REFERENCES Albums(album_id)
);

INSERT INTO Artists (artist_id, name, genre, country) VALUES
(1, 'The Midnight', 'Synthwave', 'USA'),
(2, 'Daft Punk', 'Electronic', 'France'),
(3, 'Adele', 'Pop', 'UK'),
(4, 'Rammstein', 'Metal', 'Germany'),
(5, 'Shakira', 'Pop', 'Colombia'),
(6, 'Nujabes', 'Hip Hop', 'Japan'),
(7, 'Sigur Rós', 'Post-Rock', 'Iceland'),
(8, 'Fela Kuti', 'Afrobeat', 'Nigeria'),
(9, 'Sia', 'Pop', 'Australia'),
(10, 'Gustavo Santaolalla', 'Film Score', 'Argentina');

INSERT INTO Albums (album_id, album_title, artist_id, total_tracks, release_year) VALUES
(1, 'Endless Summer', 1, 12, 2016),
(2, 'Discovery', 2, 14, 2001),
(3, '25', 3, 11, 2015),
(4, 'Mutter', 4, 11, 2001),
(5, 'Oral Fixation, Vol. 2', 5, 12, 2005),
(6, 'Modal Soul', 6, 14, 2005),
(7, 'Takk...', 7, 11, 2005),
(8, 'Expensive Shit', 8, 6, 1975),
(9, '1000 Forms of Fear', 9, 12, 2014),
(10, 'Ronroco', 10, 13, 1998);

INSERT INTO Songs (song_id, title, artist_id, release_year, duration, album_id) VALUES
(1, 'Lost & Found', 1, 2019, 240, 1),
(2, 'Vampires', 1, 2018, 215, 1),
(3, 'One More Time', 2, 2000, 320, 2),
(4, 'Digital Love', 2, 2001, 305, 2),
(5, 'Hello', 3, 2015, 295, 3),
(6, 'Rolling in the Deep', 3, 2011, 228, 3),
(7, 'Sonne', 4, 2001, 280, 4),
(8, 'Ich Will', 4, 2001, 255, 4),
(9, 'Hips Don''t Lie', 5, 2006, 218, 5),
(10, 'Waka Waka', 5, 2010, 230, 5),
(11, 'Feather', 6, 2005, 180, 6),
(12, 'Luv (Sic) Pt3', 6, 2005, 210, 6),
(13, 'Hoppípolla', 7, 2005, 265, 7),
(14, 'Sæglópur', 7, 2006, 278, 7),
(15, 'Water No Get Enemy', 8, 1975, 360, 8),
(16, 'Lady', 8, 1972, 330, 8),
(17, 'Chandelier', 9, 2014, 216, 9),
(18, 'Cheap Thrills', 9, 2016, 211, 9),
(19, 'De Ushuaia a La Quiaca', 10, 2005, 190, 10),
(20, 'Pampa', 10, 2007, 210, 10),
(21, 'Gloria', 1, 2020, 225, 1),
(22, 'America 2', 1, 2019, 230, 1),
(23, 'Revolution 909', 2, 1998, 320, 2),
(24, 'Aerodynamic', 2, 2001, 210, 2),
(25, 'Skyfall', 3, 2012, 285, 3),
(26, 'Set Fire to the Rain', 3, 2011, 242, 3),
(27, 'Engel', 4, 1997, 270, 4),
(28, 'Mutter', 4, 2001, 260, 4),
(29, 'Whenever, Wherever', 5, 2001, 200, 5),
(30, 'Try Everything', 5, 2016, 194, 5);
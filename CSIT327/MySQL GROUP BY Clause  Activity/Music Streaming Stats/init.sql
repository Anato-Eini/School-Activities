CREATE TABLE Artists (
    artist_id INT PRIMARY KEY,
    artist_name VARCHAR(100),
    genre VARCHAR(100)
);

CREATE TABLE Albums (
    album_id INT PRIMARY KEY,
    album_title VARCHAR(100),
    release_year INT,
    artist_id INT,
    FOREIGN KEY (artist_id) REFERENCES Artists(artist_id)
);

CREATE TABLE Songs (
    song_id INT PRIMARY KEY,
    song_title VARCHAR(100),
    duration INT,
    album_id INT,
    FOREIGN KEY (album_id) REFERENCES Albums(album_id)
);

INSERT INTO Artists (artist_id, artist_name, genre) VALUES
(101, 'John Legend', 'R&B'),
(102, 'Coldplay', 'Alternative Rock'),
(103, 'Taylor Swift', 'Pop'),
(104, 'Eminem', 'Hip Hop'),
(105, 'Ed Sheeran', 'Pop'),
(106, 'Beyoncé', 'R&B'),
(107, 'Linkin Park', 'Alternative Rock'),
(108, 'Ariana Grande', 'Pop'),
(109, 'Kendrick Lamar', 'Hip Hop'),
(110, 'Shawn Mendes', 'Pop'),
(111, 'Adele', 'Soul'),
(112, 'Maroon 5', 'Pop'),
(113, 'Drake', 'Hip Hop'),
(114, 'Panic! At The Disco', 'Pop'),
(115, 'Nicki Minaj', 'Hip Hop');

INSERT INTO Albums (album_id, album_title, release_year, artist_id) VALUES
(201, 'Get Lifted', 2004, 101),
(202, 'Parachutes', 2000, 102),
(203, 'Fearless', 2008, 103),
(204, 'The Marshall Mathers LP', 2000, 104),
(205, '÷', 2017, 105),
(206, 'Dangerously in Love', 2003, 106),
(207, 'Hybrid Theory', 2000, 107),
(208, 'Sweetener', 2018, 108),
(209, 'good kid, m.A.A.d city', 2012, 109),
(210, 'Illuminate', 2016, 110),
(211, '21', 2011, 111),
(212, 'Songs About Jane', 2002, 112),
(213, 'Scorpion', 2018, 113),
(214, 'Pray for the Wicked', 2018, 114),
(215, 'Pink Friday', 2010, 115);

INSERT INTO Songs (song_id, song_title, duration, album_id) VALUES
(301, 'Ordinary People', 263, 201),
(302, 'Yellow', 266, 202),
(303, 'Love Story', 235, 203),
(304, 'Stan', 356, 204),
(305, 'Shape of You', 233, 205),
(306, 'Crazy in Love', 235, 206),
(307, 'In the End', 216, 207),
(308, 'No Tears Left to Cry', 220, 208),
(309, 'Swimming Pools (Drank)', 275, 209),
(310, 'Treat You Better', 187, 210),
(311, 'Rolling in the Deep', 228, 211),
(312, 'She Will Be Loved', 257, 212),
(313, 'God''s Plan', 198, 213),
(314, 'High Hopes', 191, 214),
(315, 'Super Bass', 205, 215);
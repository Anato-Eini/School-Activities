CREATE TABLE Bands (
    band_id INT PRIMARY KEY,
    band_name VARCHAR(255),
    genre VARCHAR(50)
);

CREATE TABLE Musicians (
    musician_id INT PRIMARY KEY,
    full_name VARCHAR(255),
    instrument VARCHAR(50),
    band_id INT,
    FOREIGN KEY (band_id) REFERENCES Bands(band_id)
);

INSERT INTO Bands (band_id, band_name, genre) VALUES
(1, 'The Rockers', 'Rock'),
(2, 'Jazz Ensemble', 'Jazz'),
(3, 'EDM Masters', 'Electronic'),
(4, 'Country Crew', 'Country'),
(5, 'Metal Masters', 'Metal'),
(6, 'Blues Band', 'Blues'),
(7, 'Funk Fusion', 'Funk'),
(8, 'Reggae Rebels', 'Reggae'),
(9, 'Pop Sensation', 'Pop'),
(10, 'Acoustic Avenue', 'Acoustic');

INSERT INTO Musicians (musician_id, full_name, instrument, band_id) VALUES
(1, 'John Doe', 'Guitar', 1),
(2, 'Jane Smith', 'Bass', 1),
(3, 'Michael Johnson', 'Drums', 1),
(4, 'Emily Brown', 'Vocals', 2),
(5, 'David Wilson', 'Saxophone', 2),
(6, 'Sarah Martinez', 'Trumpet', 2),
(7, 'Christopher Lee', 'Synthesizer', 3),
(8, 'Amanda Taylor', 'DJ', 3),
(9, 'Daniel Garcia', 'Keyboard', 3),
(10, 'Robert Miller', 'Guitar', 4),
(11, 'Jessica Turner', 'Banjo', 4),
(12, 'Ryan Harris', 'Fiddle', 4),
(13, 'Matthew Scott', 'Lead Guitar', 5),
(14, 'Laura Adams', 'Bass Guitar', 5),
(15, 'Eric Thompson', 'Drums', 5),
(16, 'Sophia Young', 'Harmonica', 6),
(17, 'William White', 'Vocals', 6),
(18, 'Olivia Clark', 'Guitar', 6),
(19, 'Nathan King', 'Bass', 7),
(20, 'Alexis Green', 'Keyboard', 7),
(21, 'Benjamin Brown', 'Saxophone', 7),
(22, 'Madison White', 'Trumpet', 8),
(23, 'Ethan Harris', 'Trombone', 8),
(24, 'Zoe Robinson', 'Guitar', 8),
(25, 'Chloe Martinez', 'Piano', 9),
(26, 'David Baker', 'Guitar', 9),
(27, 'Gabriel Perez', 'Drums', 9),
(28, 'Hailey Rodriguez', 'Vocals', 10),
(29, 'Jackson Carter', 'Guitar', 10),
(30, 'Victoria Flores', 'Violin', 10);
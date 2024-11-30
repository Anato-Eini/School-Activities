CREATE TABLE ArtCollection (
    artwork_id INT PRIMARY KEY,
    artwork_title VARCHAR(255),
    artist_name VARCHAR(100),
    medium VARCHAR(50),
    year_created YEAR
);

INSERT INTO ArtCollection (artwork_id, artwork_title, artist_name, medium, year_created) VALUES
(1, 'The Persistence of Memory', 'Salvador Dalí', 'Oil on Canvas', 1931),
(2, 'Guernica', 'Pablo Picasso', 'Oil on Canvas', 1937),
(3, 'The Son of Man', 'René Magritte', 'Oil on Canvas', 1964),
(4, 'Campbell''s Soup Cans', 'Andy Warhol', 'Synthetic Polymer Paint on Canvas', 1962),
(5, 'Three Musicians', 'Pablo Picasso', 'Oil on Canvas', 1921),
(6, 'The Starry Night', 'Vincent van Gogh', 'Oil on Canvas', 1953),
(7, 'Girl with a Pearl Earring', 'Johannes Vermeer', 'Oil on Canvas', 1923),
(8, 'The Last Supper', 'Leonardo da Vinci', 'Fresco', 1931),
(9, 'The Birth of Venus', 'Sandro Botticelli', 'Tempera on Canvas', 1923),
(10, 'The Creation of Adam', 'Michelangelo', 'Fresco', 1922);
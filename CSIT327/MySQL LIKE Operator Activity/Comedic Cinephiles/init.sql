CREATE TABLE Movies (
    movie_id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255),
    director VARCHAR(255),
    genre VARCHAR(100),
    release_year INT
);

INSERT INTO Movies (title, director, genre, release_year) 
VALUES 
('The Hangover', 'Todd Phillips', 'Comedy', 2009),
('Pulp Fiction', 'Quentin Tarantino', 'Crime', 1994),
('Forrest Gump Movie', 'Robert Zemeckis', 'Drama', 1994),
('The Shawshank Redemption', 'Frank Darabont', 'Drama', 1994),
('The Dark Knight', 'Christopher Nolan', 'Action', 2008),
('Inception Movie', 'Christopher Nolan', 'Sci-Fi', 2010),
('The Matrix', 'Lana Wachowski, Lilly Wachowski', 'Sci-Fi', 1999),
('The Godfather', 'Francis Ford Coppola', 'Crime', 1972),
('The Godfather: Part II', 'Francis Ford Coppola', 'Crime', 1974),
('Interstellar Movie', 'Christopher Nolan', 'Sci-Fi', 2014);
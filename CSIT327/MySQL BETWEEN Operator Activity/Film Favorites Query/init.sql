CREATE TABLE MovieCollection (
    movie_id INT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(100),
    director VARCHAR(100),
    release_year INT,
    genre VARCHAR(50),
    duration_min INT,
    rating DECIMAL(3, 1),
    production_company VARCHAR(100)
);

INSERT INTO MovieCollection (title, director, release_year, genre, duration_min, rating, production_company) 
VALUES 
('Inception', 'Christopher Nolan', 2010, 'Sci-Fi', 148, 8.8, 'Warner Bros.'),
('The Shawshank Redemption', 'Frank Darabont', 1994, 'Drama', 142, 9.3, 'Castle Rock Entertainment'),
('The Godfather', 'Francis Ford Coppola', 1972, 'Crime', 175, 9.2, 'Paramount Pictures'),
('The Dark Knight', 'Christopher Nolan', 2008, 'Action', 152, 9.0, 'Warner Bros.'),
('Pulp Fiction', 'Quentin Tarantino', 1994, 'Crime', 154, 8.9, 'Miramax Films'),
('Forrest Gump', 'Robert Zemeckis', 1994, 'Drama', 142, 8.8, 'Paramount Pictures'),
('The Matrix', 'Lana Wachowski', 1999, 'Action', 136, 8.7, 'Warner Bros.');
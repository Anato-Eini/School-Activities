CREATE TABLE Movies (
    movie_id INT PRIMARY KEY,
    title VARCHAR(255),
    genre VARCHAR(50),
    release_year INT,
    director VARCHAR(100)
);

CREATE TABLE Actors (
    actor_id INT PRIMARY KEY,
    actor_name VARCHAR(100),
    gender VARCHAR(10),
    birth_year INT,
    nationality VARCHAR(100),
    movie_id INT,
    FOREIGN KEY (movie_id) REFERENCES Movies(movie_id)
);

INSERT INTO Movies (movie_id, title, genre, release_year, director) VALUES
(1, 'Inception', 'Sci-Fi', 2010, 'Christopher Nolan'),
(2, 'The Shawshank Redemption', 'Drama', 1994, 'Frank Darabont'),
(3, 'The Godfather', 'Crime', 1972, 'Francis Ford Coppola'),
(4, 'Pulp Fiction', 'Crime', 1994, 'Quentin Tarantino'),
(5, 'The Dark Knight', 'Action', 2008, 'Christopher Nolan');

INSERT INTO Actors (actor_id, actor_name, gender, birth_year, nationality, movie_id) VALUES
(1, 'Leonardo DiCaprio', 'Male', 1974, 'American', 1),
(2, 'Tom Hardy', 'Male', 1977, 'British', 1),
(3, 'Morgan Freeman', 'Male', 1937, 'American', 2),
(4, 'Tim Robbins', 'Male', 1958, 'American', 2),
(5, 'Marlon Brando', 'Male', 1924, 'American', 3),
(6, 'Al Pacino', 'Male', 1940, 'American', 3),
(7, 'John Travolta', 'Male', 1954, 'American', 4),
(8, 'Uma Thurman', 'Female', 1970, 'American', 4),
(9, 'Christian Bale', 'Male', 1974, 'British', 5),
(10, 'Heath Ledger', 'Male', 1979, 'Australian', 5),
(11, 'Anne Hathaway', 'Female', 1982, 'American', 1),
(12, 'Joseph Gordon-Levitt', 'Male', 1981, 'American', 1),
(13, 'Morgan Freeman', 'Male', 1937, 'American', 5),
(14, 'Gary Oldman', 'Male', 1958, 'British', 5),
(15, 'Michael Caine', 'Male', 1933, 'British', 5);
CREATE TABLE Movies (
    movie_id INT PRIMARY KEY,
    title VARCHAR(100),
    director VARCHAR(100),
    genre VARCHAR(50),
    release_year INT
);

CREATE TABLE Actors (
    actor_id INT PRIMARY KEY,
    movie_id INT,
    actor_name VARCHAR(100),
    role VARCHAR(50),
    birth_year INT,
    FOREIGN KEY (movie_id) REFERENCES Movies(movie_id)
);

INSERT INTO Movies (movie_id, title, director, genre, release_year) VALUES
(1, 'The Shawshank Redemption', 'Frank Darabont', 'Drama', 1994),
(2, 'The Godfather', 'Francis Ford Coppola', 'Crime', 1972),
(3, 'The Dark Knight', 'Christopher Nolan', 'Action', 2008),
(4, 'Pulp Fiction', 'Quentin Tarantino', 'Crime', 1994),
(5, 'Forrest Gump', 'Robert Zemeckis', 'Drama', 1994),
(6, 'Schindler''s List', 'Steven Spielberg', 'Biography', 1993),
(7, 'The Lord of the Rings: The Return of the King', 'Peter Jackson', 'Fantasy', 2003);


INSERT INTO Actors (actor_id, movie_id, actor_name, role, birth_year) VALUES
(101, 1, 'Tim Robbins', 'Andy Dufresne', 1958),
(102, 1, 'Morgan Freeman', 'Ellis Boyd "Red" Redding', 1937),
(103, 2, 'Marlon Brando', 'Don Vito Corleone', 1924),
(104, 2, 'Al Pacino', 'Michael Corleone', 1940),
(105, 3, 'Christian Bale', 'Bruce Wayne / Batman', 1974),
(106, 3, 'Heath Ledger', 'Joker', 1979),
(107, 4, 'John Travolta', 'Vincent Vega', 1954),
(108, 4, 'Uma Thurman', 'Mia Wallace', 1970),
(109, 5, 'Tom Hanks', 'Forrest Gump', 1956),
(110, 5, 'Robin Wright', 'Jenny Curran', 1966),
(111, 6, 'Liam Neeson', 'Oskar Schindler', 1952),
(112, 6, 'Ben Kingsley', 'Itzhak Stern', 1943),
(113, 7, 'Elijah Wood', 'Frodo Baggins', 1981),
(114, 7, 'Ian McKellen', 'Gandalf', 1939),
(115, 7, 'Viggo Mortensen', 'Aragorn', 1958),
(116, 7, 'Sean Astin', 'Samwise Gamgee', 1971),
(117, 7, 'Orlando Bloom', 'Legolas', 1977),
(118, 7, 'Liv Tyler', 'Arwen', 1977),
(119, 7, 'Sean Bean', 'Boromir', 1959),
(120, 7, 'Andy Serkis', 'Gollum', 1964),
(121, 7, 'Christopher Lee', 'Saruman', 1922);
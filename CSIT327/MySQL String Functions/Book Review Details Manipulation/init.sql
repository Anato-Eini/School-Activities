CREATE TABLE BookReviews (
    review_id INT PRIMARY KEY,
    reviewer_name VARCHAR(100),
    book_title VARCHAR(255),
    review_text TEXT,
    review_date DATE,
    rating INT
);

INSERT INTO BookReviews (review_id, reviewer_name, book_title, review_text, review_date, rating) VALUES
(1, 'Alice Smith', 'The Great Adventure', 'An intriguing and captivating novel.', '2024-01-10', 4),
(2, 'Bob Johnson', 'Journey Through Time', 'A fascinating look into history.', '2024-01-15', 5),
(3, 'Carol White', 'Mystery of the Ancients', 'Kept me on the edge of my seat!', '2024-01-20', 5),
(4, 'David Brown', 'The Future World', 'A compelling vision of the future.', '2024-01-25', 4),
(5, 'Eva Green', 'Secrets of the Ocean', 'A deep dive into the mysteries of the deep sea.', '2024-02-01', 3),
(6, 'Frank Garcia', 'Stars Above', 'An inspiring journey through space.', '2024-02-05', 5),
(7, 'Grace Hall', 'Lost in the Wilderness', 'A survival story that is both thrilling and educational.', '2024-02-10', 4),
(8, 'Henry Wilson', 'The World Below', 'An underground adventure full of surprises.', '2024-02-15', 4),
(9, 'Isla Martinez', 'The Enchanted Forest', 'A magical tale that delights.', '2024-02-20', 5),
(10, 'Jack Davis', 'Beyond the Horizon', 'Explores the limits of imagination.', '2024-02-25', 4),
(11, 'Karen Lee', 'The Unseen Path', 'A journey full of unexpected twists.', '2024-03-02', 4),
(12, 'Liam Young', 'Echoes of the Past', 'A historical novel that brings the past to life.', '2024-03-05', 5),
(13, 'Mia Thompson', 'Under the Sun', 'A heartwarming story about life and love.', '2024-03-10', 4),
(14, 'Noah King', 'Through the Storm', 'A tale of overcoming adversity.', '2024-03-15', 5),
(15, 'Olivia Wright', 'In the Shadow', 'A suspenseful thriller that keeps you guessing.', '2024-03-20', 5),
(16, 'Peyton Taylor', 'The Lost City', 'An archaeological adventure that uncovers ancient secrets.', '2024-03-25', 4),
(17, 'Quinn Anderson', 'Flight of Dreams', 'An exhilarating story about chasing your dreams.', '2024-04-01', 3),
(18, 'Ryan Moore', 'The Silent Voice', 'A powerful story about finding your voice.', '2024-04-05', 5),
(19, 'Sophia Clark', 'Echoes in the Wind', 'A beautiful tale of love and loss.', '2024-04-10', 4),
(20, 'Tyler Harris', 'Among the Stars', 'A science fiction adventure that is out of this world.', '2024-04-15', 5);
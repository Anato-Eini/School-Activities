CREATE TABLE UserPosts (
    post_id INT PRIMARY KEY,
    user_id INT,
    post_content TEXT,
    post_date DATE
);

CREATE TABLE PostLikes (
    like_id INT PRIMARY KEY,
    post_id INT,
    user_id INT,
    like_date DATE
);

INSERT INTO UserPosts 
VALUES 
    (101, 1, 'What are your favorite hobbies?', '2024-01-15'),
    (102, 2, 'Looking for recommendations on good books to read.', '2024-01-20'),
    (103, 3, 'Excited to share my new recipe for homemade pizza!', '2024-02-01'),
    (104, 4, 'Discussing latest technology trends.', '2024-02-18'),
    (105, 5, 'Travel experiences - share your best moments!', '2024-02-05'),
    (106, 1, 'Tips for maintaining a healthy lifestyle.', '2024-02-15'),
    (107, 2, 'Movie recommendations for the weekend?', '2024-02-11'),
    (108, 3, 'Exploring new hiking trails.', '2024-02-26'),
    (109, 4, 'Thoughts on climate change and its impact.', '2024-03-02'),
    (110, 5, 'Fitness goals for the new year.', '2024-03-08');

INSERT INTO PostLikes 
VALUES 
    (201, 101, 1, '2024-01-16'),
    (202, 101, 1, '2024-01-21'),
    (203, 101, 1, '2024-02-02'),
    (204, 101, 1, '2024-02-19'),
    (205, 105, 1, '2024-02-06'),
    (206, 106, 2, '2024-02-16'),
    (207, 107, 3, '2024-02-12'),
    (208, 108, 4, '2024-02-27'),
    (209, 109, 5, '2024-03-03'),
    (210, 110, 1, '2024-03-09'),
    (211, 101, 3, '2024-01-17'),
    (212, 102, 4, '2024-01-22'),
    (213, 103, 5, '2024-02-03'),
    (214, 104, 1, '2024-02-20'),
    (215, 105, 2, '2024-02-07'),
    (216, 106, 3, '2024-02-17'),
    (217, 107, 4, '2024-02-13'),
    (218, 108, 5, '2024-02-28'),
    (219, 109, 1, '2024-03-04'),
    (220, 110, 2, '2024-03-10'),
    (221, 101, 4, '2024-01-18'),
    (222, 102, 5, '2024-01-23'),
    (223, 103, 1, '2024-02-04'),
    (224, 104, 2, '2024-02-21'),
    (225, 105, 3, '2024-02-08'),
    (226, 106, 4, '2024-02-18'),
    (227, 107, 5, '2024-02-14'),
    (228, 108, 1, '2024-03-01'),
    (229, 109, 2, '2024-03-05'),
    (230, 110, 3, '2024-03-11'),
    (231, 101, 2, '2024-01-16'),
    (232, 102, 3, '2024-01-21'),
    (233, 103, 4, '2024-02-02'),
    (234, 104, 5, '2024-02-19'),
    (235, 105, 1, '2024-02-06'),
    (236, 106, 2, '2024-02-16'),
    (237, 107, 3, '2024-02-12'),
    (238, 108, 4, '2024-02-27'),
    (239, 109, 5, '2024-03-03'),
    (240, 110, 1, '2024-03-09'),
    (241, 101, 3, '2024-01-17'),
    (242, 102, 4, '2024-01-22'),
    (243, 103, 5, '2024-02-03'),
    (244, 104, 1, '2024-02-20'),
    (245, 105, 2, '2024-02-07'),
    (246, 106, 3, '2024-02-17'),
    (247, 107, 4, '2024-02-13'),
    (248, 108, 5, '2024-02-28'),
    (249, 109, 1, '2024-03-04'),
    (250, 110, 2, '2024-03-10'),
    (251, 101, 4, '2024-01-18'),
    (252, 102, 5, '2024-01-23'),
    (253, 103, 1, '2024-02-04'),
    (254, 104, 2, '2024-02-21'),
    (255, 105, 3, '2024-02-08'),
    (256, 106, 4, '2024-02-18'),
    (257, 107, 5, '2024-02-14'),
    (258, 108, 1, '2024-03-01'),
    (259, 109, 2, '2024-03-05'),
    (260, 110, 3, '2024-03-11'),
    (261, 101, 5, '2024-01-19'),
    (262, 102, 1, '2024-01-24'),
    (263, 103, 2, '2024-02-05'),
    (264, 104, 3, '2024-02-22'),
    (265, 105, 4, '2024-02-09'),
    (266, 106, 5, '2024-02-19'),
    (267, 107, 1, '2024-02-15'),
    (268, 108, 2, '2024-03-02'),
    (269, 109, 3, '2024-03-06'),
    (270, 110, 4, '2024-03-12');
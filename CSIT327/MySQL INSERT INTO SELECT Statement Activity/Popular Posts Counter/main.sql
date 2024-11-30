CREATE TABLE PopularPosts
(
    post_id INT PRIMARY KEY NOT NULL,
    total_likes INT
);

INSERT INTO PopularPosts
    (post_id, total_likes)
SELECT UserPosts.post_id, COUNT(UserPosts.post_id)
FROM UserPosts JOIN PostLikes ON UserPosts.post_id = PostLikes.post_id
GROUP BY UserPosts.post_id;

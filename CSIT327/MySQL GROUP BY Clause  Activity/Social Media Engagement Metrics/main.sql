SELECT Posts.post_id, COUNT(Comments.comment_id) AS total_comments
FROM Posts JOIN Comments ON Posts.post_id = Comments.post_id
GROUP BY Posts.post_id;

SELECT Posts.post_id, AVG(char_length(Comments.comment_text)) AS average_comment_length
FROM Posts JOIN Comments ON Posts.post_id = Comments.post_id
GROUP BY Posts.post_id;

SELECT Posts.user_id, COUNT(Posts.post_id) AS total_posts
FROM Posts
GROUP BY Posts.user_id;
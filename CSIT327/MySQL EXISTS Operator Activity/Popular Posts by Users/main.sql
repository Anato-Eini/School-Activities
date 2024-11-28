SELECT *
FROM ForumPosts
WHERE likes > 100 AND EXISTS (SELECT *
    FROM ForumPosts
    WHERE user_id = 5);

SELECT *
FROM ForumPosts
WHERE EXISTS (SELECT *
FROM ForumPosts
WHERE likes > 150);

SELECT *
FROM ForumPosts a
WHERE likes > 50 AND EXISTS (SELECT *
    FROM ForumPosts b
    WHERE a.user_id = b.user_id AND b.likes > 10);
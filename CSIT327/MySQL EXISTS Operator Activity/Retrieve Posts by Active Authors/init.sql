SELECT *
FROM BlogPosts a
WHERE EXISTS (SELECT *
FROM BlogPosts b
WHERE b.user_id = a.user_id AND (SELECT COUNT(*)
    FROM BlogPosts
    WHERE user_id = b.user_id ) > 1);

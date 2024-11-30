SELECT CONCAT(reviewer_name, ' - ', book_title) AS review_summary
FROM BookReviews;

SELECT review_id, LENGTH(review_text) AS review_length
FROM BookReviews;

SELECT review_id, LEFT(review_text, 100) AS review_snippet
FROM BookReviews;

SELECT review_id, LOWER(book_title) AS lowercase_title
FROM BookReviews;

SELECT review_id, UPPER(reviewer_name) AS uppercase_reviewer
FROM BookReviews;
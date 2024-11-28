SELECT Books.title, Authors.author_name
FROM Books INNER JOIN Authors ON Books.author_id = Authors.author_id
ORDER BY Books.title;

SELECT Authors.author_name, Authors.nationality
FROM Authors INNER JOIN Books ON Books.author_id = Authors.author_id
WHERE Books.publish_year < "2000-01-01"
ORDER BY Authors.author_name;
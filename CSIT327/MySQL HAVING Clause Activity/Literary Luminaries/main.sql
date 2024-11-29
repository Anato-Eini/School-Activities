SELECT genre, AVG(pages) AS avg_pages
FROM LibraryData
GROUP BY genre
HAVING avg_pages > 300 AND SUM(times_borrowed) > 50;

SELECT publication_year
FROM LibraryData
WHERE genre = 'Science Fiction'
GROUP BY publication_year;

SELECT author
FROM LibraryData
GROUP BY author
HAVING AVG(times_borrowed) > 15 AND AVG(pages) > 250;

SELECT genre
FROM LibraryData
GROUP BY genre
HAVING MAX(publication_year);

SELECT author
FROM LibraryData
WHERE pages > 200
GROUP BY author
HAVING COUNT(author) > 3;
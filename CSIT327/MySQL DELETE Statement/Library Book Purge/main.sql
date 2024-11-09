DELETE FROM LibraryCatalog WHERE author = "George Orwell";

DELETE FROM LibraryCatalog WHERE published_year < 1950;

DELETE FROM LibraryCatalog WHERE genre = "Dystopian" AND pages < 300;

DELETE FROM LibraryCatalog WHERE 2024 - published_year > 70;

DELETE FROM LibraryCatalog WHERE available_copies < 3;

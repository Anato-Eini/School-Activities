CREATE TABLE TravelLog (
    log_id INT PRIMARY KEY,
    destination VARCHAR(255),
    traveler_id INT,
    traveler_name VARCHAR(255),
    travel_date DATE,
    duration_days INT,
    expenses DECIMAL(10, 2),
    guide_id INT,
    guide_name VARCHAR(255)
);

INSERT INTO TravelLog VALUES (1, 'Paris', 101, 'John Doe', '2023-01-10', 5, 1200.00, 201, 'Tour Guide A');
INSERT INTO TravelLog VALUES (2, 'Tokyo', 102, 'Alice Smith', '2023-01-15', 7, 1500.00, 202, 'Tour Guide B');
INSERT INTO TravelLog VALUES (3, 'Rome', 103, 'Emma Johnson', '2023-01-20', 4, 800.00, 203, 'Tour Guide C');
INSERT INTO TravelLog VALUES (4, 'London', 104, 'Sarah Johnson', '2023-01-25', 6, 1100.00, 204, 'Tour Guide D');
INSERT INTO TravelLog VALUES (5, 'Sydney', 105, 'Michael Brown', '2023-02-05', 8, 1800.00, 205, 'Tour Guide E');
INSERT INTO TravelLog VALUES (6, 'New York', 106, 'Jessica White', '2023-02-10', 4, 950.00, 206, 'Tour Guide F');
INSERT INTO TravelLog VALUES (7, 'Barcelona', 107, 'David Lee', '2023-02-15', 5, 1200.00, 207, 'Tour Guide G');
INSERT INTO TravelLog VALUES (8, 'Rio de Janeiro', 108, 'Jennifer Miller', '2023-02-20', 7, 1400.00, 208, 'Tour Guide H');
INSERT INTO TravelLog VALUES (9, 'Cairo', 109, 'Matthew Taylor', '2023-03-05', 6, 1300.00, 209, 'Tour Guide I');
INSERT INTO TravelLog VALUES (10, 'Bangkok', 110, 'Emily Wilson', '2023-03-10', 5, 1150.00, 210, 'Tour Guide J');
INSERT INTO TravelLog VALUES (11, 'Dubai', 111, 'Daniel Clark', '2023-03-15', 4, 900.00, 211, 'Tour Guide K');
INSERT INTO TravelLog VALUES (12, 'Santorini', 112, 'Olivia Anderson', '2023-03-20', 3, 700.00, 212, 'Tour Guide L');
INSERT INTO TravelLog VALUES (13, 'Amsterdam', 113, 'Ethan Martinez', '2023-03-25', 5, 1100.00, 213, 'Tour Guide M');
INSERT INTO TravelLog VALUES (14, 'Bali', 114, 'Ava Thomas', '2023-04-05', 7, 1600.00, 214, 'Tour Guide N');
INSERT INTO TravelLog VALUES (15, 'Athens', 115, 'Noah Garcia', '2023-04-10', 6, 1250.00, 215, 'Tour Guide O');
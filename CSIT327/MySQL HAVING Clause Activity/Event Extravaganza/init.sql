CREATE TABLE EventInfo (
    event_id INT PRIMARY KEY,
    event_name VARCHAR(255),
    event_type VARCHAR(100),
    event_date DATE,
    venue VARCHAR(255),
    attendee_id INT,
    attendee_name VARCHAR(255),
    registration_date DATE
);

INSERT INTO EventInfo VALUES (1, 'Tech Summit', 'Conference', '2023-05-20', 'Convention Center', 101, 'John Smith', '2023-04-15');
INSERT INTO EventInfo VALUES (2, 'Art Exhibition', 'Exhibition', '2023-09-10', 'Art Gallery', 102, 'Alice Johnson', '2023-08-25');
INSERT INTO EventInfo VALUES (3, 'Music Festival', 'Festival', '2023-07-15', 'Outdoor Arena', 103, 'David Brown', '2023-06-30');
INSERT INTO EventInfo VALUES (4, 'Leadership Retreat', 'Conference', '2024-03-08', 'Mountain Resort', 104, 'Emily White', '2024-02-20');
INSERT INTO EventInfo VALUES (5, 'Fashion Show', 'Showcase', '2023-11-30', 'Fashion House', 105, 'Sophia Lee', '2023-11-15');
INSERT INTO EventInfo VALUES (6, 'Food Expo', 'Exhibition', '2024-02-18', 'Convention Center', 106, 'Michael Davis', '2024-01-25');
INSERT INTO EventInfo VALUES (7, 'Startup Fair', 'Conference', '2023-04-05', 'Tech Hub', 107, 'Emma Wilson', '2023-03-20');
INSERT INTO EventInfo VALUES (8, 'Film Screening', 'Screening', '2023-08-28', 'Cinema Hall', 108, 'Daniel Taylor', '2023-08-10');
INSERT INTO EventInfo VALUES (9, 'Fashion Week', 'Fashion Show', '2024-09-10', 'Spring Studios', 201, 'Jessica Miller', '2024-08-25');
INSERT INTO EventInfo VALUES (10, 'Film Festival', 'Festival', '2024-11-05', 'Tribeca Film Center', 202, 'Matthew Harris', '2024-10-20');
INSERT INTO EventInfo VALUES (11, 'Tech Expo', 'Exhibition', '2024-06-20', 'Javits Center', 203, 'Andrew Wilson', '2024-06-05');
INSERT INTO EventInfo VALUES (12, 'Art Auction', 'Auction', '2024-10-15', 'Sotheby''s', 204, 'Olivia Thompson', '2024-09-30');
INSERT INTO EventInfo VALUES (13, 'Concert', 'Concert', '2024-07-08', 'Madison Square Garden', 205, 'Christopher Martinez', '2024-06-23');
INSERT INTO EventInfo VALUES (14, 'Business Conference', 'Conference', '2024-04-18', 'Marriott Marquis', 206, 'Sophie Anderson', '2024-04-03');
INSERT INTO EventInfo VALUES (15, 'Charity Ball', 'Ball', '2024-12-05', 'Metropolitan Club', 207, 'Alexander Thompson', '2024-11-20');
CREATE TABLE GymData (
    member_id INT PRIMARY KEY,
    member_name VARCHAR(255),
    membership_type VARCHAR(100),
    workout_date DATE,
    workout_duration_minutes INT,
    calories_burned INT,
    trainer_id INT,
    trainer_name VARCHAR(255),
    session_type VARCHAR(100)
);

INSERT INTO GymData VALUES (1, 'John Doe', 'Premium', '2023-01-10', 75, 300, 101, 'Trainer A', 'Weightlifting');
INSERT INTO GymData VALUES (2, 'Alice Smith', 'Standard', '2023-01-15', 45, 180, 102, 'Trainer B', 'Cardio');
INSERT INTO GymData VALUES (3, 'Emma Johnson', 'Premium', '2023-01-20', 90, 400, 103, 'Trainer C', 'HIIT');
INSERT INTO GymData VALUES (4, 'Sarah Johnson', 'Standard', '2023-01-25', 60, 220, 104, 'Trainer D', 'Yoga');
INSERT INTO GymData VALUES (5, 'Michael Brown', 'Premium', '2023-02-05', 70, 280, 105, 'Trainer E', 'Pilates');
INSERT INTO GymData VALUES (6, 'Jessica White', 'Standard', '2023-02-10', 55, 200, 106, 'Trainer F', 'Crossfit');
INSERT INTO GymData VALUES (7, 'David Lee', 'Premium', '2023-02-15', 80, 350, 107, 'Trainer G', 'Zumba');
INSERT INTO GymData VALUES (8, 'Jennifer Miller', 'Standard', '2023-02-20', 65, 240, 108, 'Trainer H', 'Kickboxing');
INSERT INTO GymData VALUES (9, 'Matthew Taylor', 'Premium', '2023-03-05', 75, 300, 109, 'Trainer I', 'Spin Class');
INSERT INTO GymData VALUES (10, 'Emily Wilson', 'Standard', '2023-03-10', 50, 180, 110, 'Trainer J', 'Boxing');
INSERT INTO GymData VALUES (11, 'Daniel Clark', 'Premium', '2023-03-15', 85, 370, 111, 'Trainer K', 'Functional Training');
INSERT INTO GymData VALUES (12, 'Olivia Anderson', 'Standard', '2023-03-20', 70, 260, 112, 'Trainer L', 'Aerobics');
INSERT INTO GymData VALUES (13, 'Ethan Martinez', 'Premium', '2023-03-25', 90, 400, 113, 'Trainer M', 'TRX Suspension Training');
INSERT INTO GymData VALUES (14, 'Ava Thomas', 'Standard', '2023-04-05', 55, 200, 114, 'Trainer N', 'Circuit Training');
INSERT INTO GymData VALUES (15, 'Noah Garcia', 'Premium', '2023-04-10', 75, 320, 115, 'Trainer O', 'Barre');
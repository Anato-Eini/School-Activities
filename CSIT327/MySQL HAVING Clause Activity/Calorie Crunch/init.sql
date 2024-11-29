CREATE TABLE FitnessData (
    activity_id INT PRIMARY KEY,
    activity_name VARCHAR(255),
    participant_id INT,
    participant_name VARCHAR(255),
    activity_date DATE,
    duration_minutes INT,
    calories_burned INT,
    coach_id INT,
    coach_name VARCHAR(255)
);

INSERT INTO FitnessData VALUES (1, 'Yoga', 101, 'John Doe', '2023-01-10', 60, 300, 201, 'Trainer A');
INSERT INTO FitnessData VALUES (2, 'Crossfit', 102, 'Alice Smith', '2023-01-15', 45, 180, 202, 'Trainer B');
INSERT INTO FitnessData VALUES (3, 'Zumba', 103, 'Emma Johnson', '2023-01-20', 90, 400, 203, 'Trainer C');
INSERT INTO FitnessData VALUES (4, 'Pilates', 104, 'Sarah Johnson', '2023-01-25', 60, 250, 204, 'Trainer D');
INSERT INTO FitnessData VALUES (5, 'Kickboxing', 105, 'Michael Brown', '2023-02-05', 70, 300, 205, 'Trainer E');
INSERT INTO FitnessData VALUES (6, 'Spin Class', 106, 'Jessica White', '2023-02-10', 55, 200, 206, 'Trainer F');
INSERT INTO FitnessData VALUES (7, 'Boxing', 107, 'David Lee', '2023-02-15', 80, 350, 207, 'Trainer G');
INSERT INTO FitnessData VALUES (8, 'Functional Training', 108, 'Jennifer Miller', '2023-02-20', 65, 260, 208, 'Trainer H');
INSERT INTO FitnessData VALUES (9, 'Aerobics', 109, 'Matthew Taylor', '2023-03-05', 75, 320, 209, 'Trainer I');
INSERT INTO FitnessData VALUES (10, 'TRX Suspension Training', 110, 'Emily Wilson', '2023-03-10', 50, 180, 210, 'Trainer J');
INSERT INTO FitnessData VALUES (11, 'Circuit Training', 111, 'Daniel Clark', '2023-03-15', 85, 370, 211, 'Trainer K');
INSERT INTO FitnessData VALUES (12, 'Barre', 112, 'Olivia Anderson', '2023-03-20', 70, 280, 212, 'Trainer L');
INSERT INTO FitnessData VALUES (13, 'Yoga', 113, 'Ethan Martinez', '2023-03-25', 90, 400, 213, 'Trainer M');
INSERT INTO FitnessData VALUES (14, 'Crossfit', 114, 'Ava Thomas', '2023-04-05', 55, 200, 214, 'Trainer N');
INSERT INTO FitnessData VALUES (15, 'Zumba', 115, 'Noah Garcia', '2023-04-10', 75, 320, 215, 'Trainer O');
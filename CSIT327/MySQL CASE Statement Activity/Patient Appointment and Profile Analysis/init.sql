CREATE TABLE Patients (
    patient_id INT PRIMARY KEY,
    first_name VARCHAR(100),
    last_name VARCHAR(100),
    dob DATE,
    gender VARCHAR(50),
    insurance_provider VARCHAR(100)
);

CREATE TABLE Appointments (
    appointment_id INT PRIMARY KEY,
    patient_id INT,
    date DATE,
    time TIME,
    doctor_name VARCHAR(100),
    specialization VARCHAR(100),
    status VARCHAR(50),
    FOREIGN KEY (patient_id) REFERENCES Patients(patient_id)
);

INSERT INTO Patients (patient_id, first_name, last_name, dob, gender, insurance_provider) VALUES
(1, 'John', 'Doe', '1980-01-01', 'Male', 'HealthCare Inc.'),
(2, 'Jane', 'Smith', '1995-05-15', 'Female', 'Wellness Group'),
(3, 'Emily', 'Jones', '2010-07-20', 'Female', NULL),
(4, 'Michael', 'Brown', '1945-11-03', 'Male', 'MediCare'),
(5, 'Sarah', 'Davis', '1988-03-30', 'Female', 'HealthCare Inc.'),
(6, 'William', 'Miller', '2003-09-10', 'Male', NULL),
(7, 'Emma', 'Wilson', '1975-12-05', 'Female', 'Wellness Group'),
(8, 'James', 'Moore', '1950-06-22', 'Male', 'MediCare'),
(9, 'Olivia', 'Taylor', '1965-04-17', 'Female', 'HealthCare Inc.'),
(10, 'Liam', 'Anderson', '2008-02-28', 'Male', NULL);

INSERT INTO Appointments (appointment_id, patient_id, date, time, doctor_name, specialization, status) VALUES
(1, 1, '2024-03-01', '09:00', 'Dr. Green', 'General Practitioner', 'Scheduled'),
(2, 2, '2024-03-01', '10:30', 'Dr. Brown', 'Cardiology', 'Completed'),
(3, 3, '2024-03-01', '11:00', 'Dr. Black', 'Pediatrics', 'Scheduled'),
(4, 4, '2024-03-02', '09:30', 'Dr. White', 'Neurology', 'Cancelled'),
(5, 5, '2024-03-02', '10:00', 'Dr. Green', 'General Practitioner', 'Completed'),
(6, 1, '2024-03-05', '14:00', 'Dr. Adams', 'Dermatology', 'Scheduled'),
(7, 6, '2024-03-06', '11:30', 'Dr. Clark', 'Orthopedics', 'Completed'),
(8, 7, '2024-03-07', '08:45', 'Dr. Lewis', 'Cardiology', 'Scheduled'),
(9, 8, '2024-03-08', '16:00', 'Dr. Hall', 'Endocrinology', 'Cancelled'),
(10, 2, '2024-03-09', '13:30', 'Dr. Young', 'Gastroenterology', 'Scheduled'),
(11, 10, '2024-03-10', '10:15', 'Dr. Walker', 'Oncology', 'Scheduled'),
(12, 9, '2024-03-11', '09:50', 'Dr. King', 'Neurology', 'Completed'),
(13, 5, '2024-03-12', '15:30', 'Dr. Wright', 'Urology', 'Scheduled'),
(14, 3, '2024-03-13', '14:20', 'Dr. Green', 'Pediatrics', 'Cancelled'),
(15, 4, '2024-03-14', '08:00', 'Dr. Harris', 'General Practitioner', 'Scheduled'),
(16, 7, '2024-03-15', '11:00', 'Dr. Nelson', 'Cardiology', 'Scheduled'),
(17, 1, '2024-03-16', '10:45', 'Dr. Carter', 'Rheumatology', 'Completed'),
(18, 6, '2024-03-17', '16:30', 'Dr. Phillips', 'General Practitioner', 'Scheduled'),
(19, 8, '2024-03-18', '12:00', 'Dr. Campbell', 'Orthopedics', 'Completed'),
(20, 9, '2024-03-19', '11:15', 'Dr. Parker', 'Dermatology', 'Scheduled'),
(21, 10, '2024-03-20', '09:30', 'Dr. Evans', 'Pediatrics', 'Cancelled'),
(22, 2, '2024-03-21', '15:00', 'Dr. Edwards', 'Neurology', 'Scheduled'),
(23, 5, '2024-03-22', '14:10', 'Dr. Collins', 'Gastroenterology', 'Completed'),
(24, 3, '2024-03-23', '08:20', 'Dr. Stewart', 'Pediatrics', 'Scheduled'),
(25, 4, '2024-03-24', '17:00', 'Dr. Sanchez', 'Endocrinology', 'Scheduled'),
(26, 6, '2024-03-25', '10:00', 'Dr. Morris', 'Ophthalmology', 'Cancelled'),
(27, 7, '2024-03-26', '13:45', 'Dr. Rogers', 'Cardiology', 'Scheduled'),
(28, 1, '2024-03-27', '15:30', 'Dr. Reed', 'Urology', 'Completed'),
(29, 8, '2024-03-28', '11:00', 'Dr. Cook', 'General Practitioner', 'Scheduled'),
(30, 10, '2024-03-29', '16:15', 'Dr. Morgan', 'Pediatrics', 'Scheduled');
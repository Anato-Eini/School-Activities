CREATE TABLE ClinicAppointments (
    appointment_id INT PRIMARY KEY,
    patient_name VARCHAR(100),
    doctor_name VARCHAR(100),
    appointment_date DATE,
    appointment_time TIME,
    consultation_fee FLOAT
);

INSERT INTO ClinicAppointments (appointment_id, patient_name, doctor_name, appointment_date, appointment_time, consultation_fee) VALUES
(1, 'Alice', 'Dr. Smith', '2024-03-15', '08:00:00', 50.00),
(2, 'Bob', 'Dr. Johnson', '2024-03-15', '09:00:00', 60.00),
(3, 'Carol', 'Dr. Smith', '2024-03-15', '10:00:00', 50.00),
(4, 'David', 'Dr. Martinez', '2024-03-15', '11:00:00', 70.00),
(5, 'Eve', 'Dr. Johnson', '2024-03-15', '12:00:00', 60.00),
(6, 'Frank', 'Dr. Smith', '2024-03-15', '13:00:00', 50.00),
(7, 'Grace', 'Dr. Martinez', '2024-03-15', '14:00:00', 70.00),
(8, 'Helen', 'Dr. Johnson', '2024-03-15', '15:00:00', 60.00),
(9, 'Ivy', 'Dr. Smith', '2024-03-15', '16:00:00', 50.00),
(10, 'Jack', 'Dr. Johnson', '2024-03-15', '17:00:00', 60.00),
(11, 'Kevin', 'Dr. Lee', '2024-03-15', '08:30:00', 55.00),
(12, 'Linda', 'Dr. Garcia', '2024-03-15', '09:30:00', 65.00),
(13, 'Michael', 'Dr. Kim', '2024-03-15', '10:30:00', 75.00),
(14, 'Nancy', 'Dr. Patel', '2024-03-15', '11:30:00', 85.00),
(15, 'Olivia', 'Dr. Wong', '2024-03-15', '12:30:00', 95.00);
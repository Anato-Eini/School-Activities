SELECT SUM(consultation_fee) AS total_fee_collected
FROM ClinicAppointments
WHERE appointment_date = "2024-03-15";

SELECT doctor_name, COUNT(*) AS appointment_count
FROM ClinicAppointments
GROUP BY doctor_name;
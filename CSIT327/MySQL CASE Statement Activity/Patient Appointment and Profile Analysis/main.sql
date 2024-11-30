SELECT
    patient_id,
    first_name,
    last_name,
    CASE
        WHEN timestampdiff (year, dob, "2024-01-01") < 18 THEN 'Child'
        WHEN timestampdiff (year, dob, "2024-01-01") <= 65 THEN "Adult"
        ELSE "Senior"
    END AS age_group
FROM
    Patients;

SELECT
    appointment_id,
    patient_id,
    date,
    time,
    doctor_name,
    specialization,
    CASE status
        WHEN "Scheduled" THEN "Upcoming"
        WHEN "Completed" THEN "Finished"
        ELSE "Not Active"
    END AS appointment_status
FROM
    Appointments;

SELECT
    patient_id,
    first_name,
    last_name,
    insurance_provider,
    CASE
        WHEN insurance_provider IS NULL THEN "Not Covered"
        ELSE "Covered"
    END AS insurance_status
FROM
    Patients;

SELECT
    doctor_name,
    specialization,
    CASE
        WHEN specialization = "Cardiology"
        OR specialization = "Neurology" THEN "Specialist"
        WHEN specialization = "General Practitioner" THEN "GP"
        ELSE "Other"
    END AS specialization_category
FROM
    Appointments;

SELECT
    appointment_id,
    patient_id,
    date,
    time,
    CASE
        WHEN hour (time) < 12 THEN "Morning"
        WHEN hour (time) <= 18 THEN "Afternoon"
        ELSE "Evening"
    END AS time_of_day
FROM
    Appointments;
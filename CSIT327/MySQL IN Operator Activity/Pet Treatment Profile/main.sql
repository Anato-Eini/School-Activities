SELECT *
FROM VeterinaryRecords
WHERE treatment_type IN ("Vaccination","Dental Cleaning") AND species IN ("Dog", "Cat");

SELECT *
FROM VeterinaryRecords
WHERE treatment_cost IN (75, 80, 120) AND treatment_date IN (SELECT treatment_date
    FROM VeterinaryRecords
    WHERE treatment_date LIKE "2024-01%" OR treatment_date LIKE "2024-02%" OR treatment_date LIKE "2024-03%");

SELECT *
FROM VeterinaryRecords
WHERE age IN (1, 2, 3) AND owner_name IN (SELECT owner_name
    FROM VeterinaryRecords
    WHERE owner_name LIKE "A%" OR owner_name LIKE "B%" OR owner_name LIKE "C%");

SELECT *
FROM VeterinaryRecords
WHERE treatment_type IN ("Checkup", "Surgery", "Spaying") AND breed IN ("Persian", "Siamese", "Bengal");

SELECT *
FROM VeterinaryRecords
WHERE treatment_type IN ("Vaccination", "Dental Cleaning", "Allergy Treatment") AND owner_name IN (SELECT owner_name
    FROM VeterinaryRecords
    WHERE owner_name LIKE "%son");
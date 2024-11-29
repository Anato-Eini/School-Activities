SELECT Pets.pet_type, Activities.activity_type, COUNT(Activities.activity_type) AS activity_count
FROM Pets JOIN Activities ON Pets.pet_id = Activities.pet_id
GROUP BY Pets.pet_type, Activities.activity_type
ORDER BY Pets.pet_type, activity_count DESC;
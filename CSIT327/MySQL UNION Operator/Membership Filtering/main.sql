    SELECT *
    FROM GymMembers
    WHERE age < 30
UNION
    SELECT *
    FROM GymMembers
    WHERE membership_type = 'Premium';
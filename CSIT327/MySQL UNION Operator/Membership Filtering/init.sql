CREATE TABLE GymMembers (
    member_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100),
    age INT,
    gender VARCHAR(10),
    membership_type VARCHAR(20),
    registration_date DATE
);

INSERT INTO GymMembers (name, age, gender, membership_type, registration_date) 
VALUES 
('Alice', 25, 'Female', 'Basic', '2022-01-15'),
('Bob', 35, 'Male', 'Premium', '2021-11-20'),
('Charlie', 28, 'Male', 'Basic', '2022-02-10'),
('Diana', 20, 'Female', 'Premium', '2022-03-05'),
('Eva', 40, 'Female', 'Basic', '2021-12-30'),
('Frank', 29, 'Male', 'Basic', '2022-01-05'),
('Grace', 22, 'Female', 'Premium', '2021-10-12'),
('Henry', 32, 'Male', 'Basic', '2022-02-25'),
('Ivy', 27, 'Female', 'Basic', '2021-09-18'),
('Jack', 31, 'Male', 'Premium', '2021-08-07');
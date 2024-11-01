CREATE TABLE Employees (
    employee_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255),
    department VARCHAR(255),
    salary DECIMAL(10, 2)
);

INSERT INTO Employees (name, department, salary) VALUES ("John Doe", "Engineering", 50000), ("Jane Smith", "Marketing", 55000), ("Alex Johnson", "Finance", 60000);
CREATE TABLE Employees (
    employee_id INT AUTO_INCREMENT PRIMARY KEY,
    email VARCHAR(255) NOT NULL UNIQUE,
    name VARCHAR(255) NOT NULL,
    department VARCHAR(255) NOT NULL,
    salary DECIMAL(10, 2) CHECK (salary BETWEEN 30000 AND 200000),
    start_date DATE DEFAULT "2024-01-01"
);

INSERT INTO Employees (email, name, department, salary, start_date) VALUES 
("john@example.com", "John Doe", "Sales", 60000.00, "2024-02-22"), 
("jane@example.com", "Jane Smith", "Marketing", 55000.00, "2024-02-22"), 
("alex@example.com", "Alex Johnson", "Finance", 65000.00, "2024-02-22"), 
("mike@example.com", "Mike Brown", "Customer Support", 45000.00, "2024-02-22"), 
("david@example.com", "David Wilson", "Research and Development", 58000.00, "2024-02-22"), 
("lisa@example.com", "Lisa Anderson", "Quality Assurance", 49000.00, "2024-02-22"), 
("kevin@example.com", "Kevin Martinez", "Product Management", "62000.00", "2024-02-22");


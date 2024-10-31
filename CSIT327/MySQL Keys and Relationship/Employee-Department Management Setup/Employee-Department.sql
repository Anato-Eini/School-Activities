CREATE TABLE Employees (
    employee_id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100)
);

CREATE TABLE Departments (
    department_id INT AUTO_INCREMENT PRIMARY KEY,
    department_name VARCHAR(100),
    manager_id INT,
    FOREIGN KEY (manager_id) REFERENCES Employees(employee_id)
);

INSERT INTO Employees (name) VALUES ("John Doe"), ("Jane Doe");

INSERT INTO Departments (department_name, manager_id) VALUES ("IT", 1);

INSERT INTO Departments (department_name, manager_id) VALUES ("HR", 2);
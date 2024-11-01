ALTER TABLE Employees ADD COLUMN department VARCHAR(100);

ALTER TABLE Employees MODIFY COLUMN salary DECIMAL(12, 2);

ALTER TABLE Employees DROP COLUMN department;

ALTER TABLE Employees RENAME COLUMN last_name TO surname;
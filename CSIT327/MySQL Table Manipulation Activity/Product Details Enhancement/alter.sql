ALTER TABLE Products ADD COLUMN category VARCHAR(50);

ALTER TABLE Products MODIFY COLUMN price DECIMAL(15, 2);

ALTER TABLE Products DROP COLUMN category;

ALTER TABLE Products ADD COLUMN quantity INT;
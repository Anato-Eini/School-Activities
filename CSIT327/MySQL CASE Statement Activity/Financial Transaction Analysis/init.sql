CREATE TABLE FinancialTransactions (
    transaction_id INT PRIMARY KEY,
    transaction_type VARCHAR(100),
    amount DECIMAL(10,2),
    transaction_date DATE
);

INSERT INTO FinancialTransactions (transaction_id, transaction_type, amount, transaction_date) VALUES
(1, 'Income', 500.00, '2024-01-15'),
(2, 'Expense', 200.00, '2024-01-20'),
(3, 'Transfer', 100.00, '2024-01-25'),
(4, 'Income', 700.00, '2024-02-10'),
(5, 'Expense', 300.00, '2024-02-15'),
(6, 'Transfer', 150.00, '2024-02-20'),
(7, 'Income', 600.00, '2024-03-05'),
(8, 'Expense', 250.00, '2024-03-10'),
(9, 'Transfer', 200.00, '2024-03-15');
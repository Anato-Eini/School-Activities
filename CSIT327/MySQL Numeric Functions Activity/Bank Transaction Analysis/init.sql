CREATE TABLE BankTransactions (
    transaction_id INT PRIMARY KEY,
    account_number VARCHAR(100),
    transaction_type VARCHAR(100),
    amount FLOAT,
    transaction_date DATE
);

INSERT INTO BankTransactions (transaction_id, account_number, transaction_type, amount, transaction_date) VALUES
(1, '123456789', 'Deposit', 1000.00, '2024-03-15'),
(2, '987654321', 'Withdrawal', 200.00, '2024-03-15'),
(3, '123456789', 'Deposit', 1500.00, '2024-03-16'),
(4, '987654321', 'Deposit', 300.00, '2024-03-17'),
(5, '123456789', 'Withdrawal', 500.00, '2024-03-18'),
(6, '987654321', 'Deposit', 1000.00, '2024-03-19'),
(7, '123456789', 'Deposit', 2000.00, '2024-03-20'),
(8, '987654321', 'Withdrawal', 700.00, '2024-03-21'),
(9, '123456789', 'Deposit', 2500.00, '2024-03-22'),
(10, '987654321', 'Deposit', 400.00, '2024-03-23'),
(11, '2468101214', 'Deposit', 1500.00, '2024-03-15'),
(12, '1357924680', 'Withdrawal', 300.00, '2024-03-15'),
(13, '1122334455', 'Deposit', 800.00, '2024-03-15'),
(14, '9876543210', 'Withdrawal', 700.00, '2024-03-15'),
(15, '9876543210', 'Deposit', 2000.00, '2024-03-15'),
(16, '2468101214', 'Withdrawal', 500.00, '2024-03-15'),
(17, '1122334455', 'Deposit', 1000.00, '2024-03-15'),
(18, '1357924680', 'Withdrawal', 400.00, '2024-03-15'),
(19, '9876543210', 'Deposit', 3000.00, '2024-03-15'),
(20, '2468101214', 'Withdrawal', 200.00, '2024-03-15');
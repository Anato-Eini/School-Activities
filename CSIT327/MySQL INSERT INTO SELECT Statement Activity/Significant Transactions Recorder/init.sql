CREATE TABLE TransactionDetails (
    transaction_id INT PRIMARY KEY,
    account_id INT,
    transaction_date DATE,
    amount DECIMAL(10, 2),
    transaction_type VARCHAR(50)
);

CREATE TABLE AccountHolders (
    account_id INT PRIMARY KEY,
    account_holder_name VARCHAR(255),
    account_type VARCHAR(50),
    account_balance DECIMAL(10, 2)
);

INSERT INTO AccountHolders 
VALUES 
    (1, 'John Doe', 'Savings', 1500.00),
    (2, 'Jane Smith', 'Checking', 2500.00),
    (3, 'Alice Johnson', 'Investment', 5000.00),
    (4, 'Bob Williams', 'Savings', 2000.00),
    (5, 'Emily Brown', 'Checking', 3000.00);

INSERT INTO TransactionDetails 
VALUES 
    (101, 1, '2024-01-15', 500.00, 'Withdrawal'),
    (102, 2, '2024-01-20', 1200.00, 'Withdrawal'),
    (103, 3, '2024-02-01', 3000.00, 'Deposit'),
    (104, 4, '2024-02-18', 150.00, 'Withdrawal'),
    (105, 5, '2024-02-05', 2000.00, 'Withdrawal'),
    (106, 1, '2024-02-15', 700.00, 'Deposit'),
    (107, 2, '2024-02-11', 1000.00, 'Deposit'),
    (108, 3, '2024-02-26', 800.00, 'Withdrawal'),
    (109, 4, '2024-03-02', 600.00, 'Deposit'),
    (110, 5, '2024-03-08', 750.00, 'Withdrawal'),
    (111, 1, '2024-03-15', 300.00, 'Withdrawal'),
    (112, 2, '2024-03-22', 400.00, 'Deposit'),
    (113, 3, '2024-04-01', 700.00, 'Withdrawal'),
    (114, 4, '2024-04-10', 200.00, 'Deposit'),
    (115, 5, '2024-04-20', 500.00, 'Withdrawal');
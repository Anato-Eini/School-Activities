SELECT SUM(amount) AS total_deposits
FROM BankTransactions
WHERE transaction_type = 'Deposit' AND transaction_date = '2024-03-15';

SELECT transaction_type, COUNT(*) AS transaction_count
FROM BankTransactions
GROUP BY transaction_type;

SELECT account_number, ROUND(AVG(amount), 2) AS average_transaction_amount
FROM BankTransactions
GROUP BY account_number;
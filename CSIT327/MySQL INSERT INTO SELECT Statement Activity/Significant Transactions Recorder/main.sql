CREATE TABLE LargeTransactions
(
    transaction_id INT PRIMARY KEY,
    account_id INT,
    transaction_date DATE,
    amount DECIMAL(10, 2)
)

INSERT INTO LargeTransactions
    (transaction_id, account_id, transaction_date, amount)
SELECT transaction_id, account_id, transaction_date, amount
FROM TransactionDetails
WHERE amount > 1000;

CREATE TABLE UpdatedAccountBalances
(
    account_id INT PRIMARY KEY,
    new_balance DECIMAL(10, 2)
)

INSERT INTO UpdatedAccountBalances
    (account_id, new_balance)
SELECT AccountHolders.account_id, (SUM(TransactionDetails.amount) + AccountHolders.account_balance) AS new_balance
FROM TransactionDetails JOIN AccountHolders ON TransactionDetails.account_id = AccountHolders.account_id
GROUP BY AccountHolders.account_id;

CREATE TABLE DepositTransactions
(
    transaction_id INT PRIMARY KEY,
    account_id INT,
    transaction_date DATE,
    amount DECIMAL(10, 2)
)

INSERT INTO DepositTransactions
    (transaction_id, account_id, transaction_date, amount)
SELECT transaction_id, account_id, transaction_date, amount
FROM TransactionDetails
WHERE transaction_type = "Deposit";
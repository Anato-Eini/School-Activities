SELECT
    CASE
        WHEN amount > 500 THEN 'High'
        WHEN amount > 100 THEN 'Medium'
        ELSE 'Low'
    END AS amount_range,
    COUNT(transaction_id) AS transaction_count
FROM
    FinancialTransactions
GROUP BY
    amount_range;

SELECT
    monthname (transaction_date) AS month,
    SUM(amount) AS total_amount
FROM
    FinancialTransactions
GROUP BY
    month;
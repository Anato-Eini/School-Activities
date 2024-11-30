SELECT
    transaction_type,
    SUM(
        CASE transaction_type
            WHEN 'Purchase' THEN quantity
            ELSE 0
        END
    ) AS purchase_total,
    SUM(
        CASE transaction_type
            WHEN 'Sale' THEN quantity
            ELSE 0
        END
    ) AS sale_total,
    SUM(
        CASE transaction_type
            WHEN 'Return' THEN quantity
            ELSE 0
        END
    ) AS return_total,
    SUM(
        CASE transaction_type
            WHEN 'Adjustment' THEN quantity
            ELSE 0
        END
    ) AS adjustment_total
FROM
    InventoryTransactions
GROUP BY
    transaction_type;

SELECT
    product_name,
    SUM(quantity) AS total_quantity,
    'Electronic' AS category
FROM
    InventoryTransactions
GROUP BY
    product_name;

SELECT
    CASE
        WHEN month (transaction_date) <= 6 THEN 'First Half'
        ELSE 'Second Half'
    END AS month_half,
    SUM(quantity) AS total_quantity
FROM
    InventoryTransactions
GROUP BY
    month_half;
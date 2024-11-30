SELECT
    cust_id,
    first_name,
    last_name,
    CASE
        WHEN (2040 - year (dob)) < 30 THEN 'Young'
        WHEN (2040 - year (dob)) <= 60 THEN 'Middle-aged'
        ELSE 'Senior'
    END AS age_group
FROM
    CustomersData;

SELECT
    order_id,
    cust_id,
    order_date,
    order_time,
    product_name,
    quantity,
    CASE order_status
        WHEN "Pending" THEN "Waiting"
        WHEN "Delivered" THEN "Completed"
        ELSE "In Progress"
    END AS order_status
FROM
    OrdersInfo;

SELECT
    cust_id,
    first_name,
    last_name,
    email,
    CASE
        WHEN email LIKE "%@gmail.com" THEN "Gmail"
        WHEN email LIKE "%@yahoo.com" THEN "Yahoo"
        ELSE "Other"
    END AS email_domain
FROM
    CustomersData;

SELECT
    OrdersInfo.order_id,
    CustomersData.cust_id,
    OrdersInfo.product_name,
    CASE
        WHEN OrdersInfo.product_name LIKE "Smart%" THEN "Smart gadgets"
        WHEN OrdersInfo.product_name LIKE "%shirt%" THEN "Fashion"
        ELSE "Other"
    END AS product_category
FROM
    OrdersInfo
    JOIN CustomersData ON OrdersInfo.cust_id = CustomersData.cust_id
ORDER BY
    OrdersInfo.order_id;
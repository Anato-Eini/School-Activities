CREATE TABLE Orders(
    order_id INT,
    customer_id INT,
    order_date DATE,
    total_amount DECIMAL(10, 2)
);


----------------------------------------------------------------------------------------------------

INSERt INTO Orders (order_id, customer_id, order_date, total_amount) VALUES (1, 2, '2017-06-15', 25.55);

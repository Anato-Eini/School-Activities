SELECT * FROM CustomerOrders WHERE customer_name IN ("Alice", "Bob") AND unit_price > 50;

SELECT * FROM CustomerOrders WHERE order_date LIKE "%-01-%" OR order_date LIKE "%-02-%" OR order_date LIKE "%-03-%";
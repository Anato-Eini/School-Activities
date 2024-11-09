SELECT product_name, unit_price * quantity_sold AS total_revenue FROM ProductSales ORDER BY total_revenue DESC;

SELECT SUM(unit_price * quantity_sold) as total_revenue FROM ProductSales WHERE (unit_price * quantity_sold) > 10000;

SELECT product_name FROM ProductSales WHERE quantity_sold > 100;

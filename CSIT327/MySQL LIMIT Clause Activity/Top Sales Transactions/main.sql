SELECT * FROM ProductSales ORDER BY quantity_sold * unit_price DESC LIMIT 5;

SELECT * FROM ProductSales WHERE product_name = "laptop" ORDER BY sale_date DESC LIMIT 3;

SELECT * FROM ProductSales ORDER BY quantity_sold DESC LIMIT 7;

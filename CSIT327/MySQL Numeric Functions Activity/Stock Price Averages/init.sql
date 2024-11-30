CREATE TABLE StockPrices (
    price_id INT PRIMARY KEY,
    stock_symbol VARCHAR(100),
    price_date DATE,
    closing_price FLOAT,
    volume_traded INT
);

INSERT INTO StockPrices (price_id, stock_symbol, price_date, closing_price, volume_traded) VALUES
(1, 'AAPL', '2024-03-15', 150.50, 100000),
(2, 'GOOGL', '2024-03-15', 2000.75, 75000),
(3, 'MSFT', '2024-03-15', 250.25, 50000),
(4, 'AMZN', '2024-03-15', 3000.00, 100000),
(5, 'NFLX', '2024-03-15', 400.50, 60000),
(6, 'TSLA', '2024-03-15', 700.25, 90000),
(7, 'FB', '2024-03-15', 250.75, 80000),
(8, 'NVDA', '2024-03-15', 600.50, 70000),
(9, 'DIS', '2024-03-15', 150.00, 55000),
(10, 'PYPL', '2024-03-15', 250.00, 85000);
CREATE TABLE CustomerFeedback (
    feedback_id INT AUTO_INCREMENT PRIMARY KEY,
    customer_name VARCHAR(255),
    product_id INT,
    rating INT,
    comment TEXT,
    feedback_date DATE
);

INSERT INTO CustomerFeedback (customer_name, product_id, rating, comment, feedback_date)
VALUES
('Alice', 1, 5, 'Great product, highly recommended!', '2023-01-10'),
('Bob', 2, 4, 'Good quality, satisfied with the purchase.', '2023-01-15'),
('Carol', 3, 3, 'Average product, could be better.', '2023-02-05'),
('David', 4, 5, 'Excellent service and product.', '2023-01-20'),
('Emma', 5, 2, 'Disappointed with the product quality.', '2023-01-25'),
('Frank', 6, 4, 'Impressed with the fast delivery.', '2023-02-10'),
('Grace', 7, 5, 'Love it! Will buy again.', '2023-01-30');

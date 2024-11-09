CREATE TABLE CustomerFeedback (
    feedback_id INT AUTO_INCREMENT PRIMARY KEY,
    customer_name VARCHAR(100),
    feedback_text TEXT,
    feedback_date DATE
);

INSERT INTO CustomerFeedback (customer_name, feedback_text, feedback_date)
VALUES
('Alice', 'Great service! Really satisfied with my purchase.', '2023-12-10'),
('Bob', 'The product quality could be improved.', '2023-12-08'),
('Charlie', 'Excellent customer support.', '2023-12-05'),
('David', 'Fast delivery and good packaging.', '2023-12-20'),
('Alice', 'I had a problem with my order, but it was quickly resolved by the support team.', '2023-12-01');

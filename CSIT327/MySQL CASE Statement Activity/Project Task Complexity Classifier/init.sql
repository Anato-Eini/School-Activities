CREATE TABLE ProjectTasks (
    task_id INT PRIMARY KEY,
    project_name VARCHAR(100),
    task_description TEXT,
    deadline_date DATE,
    task_status VARCHAR(50)
);

INSERT INTO ProjectTasks (task_id, project_name, task_description, deadline_date, task_status) VALUES
(1, 'Project X', 'Implement feature A', '2024-01-15', 'Pending'),
(2, 'Project Y', 'Design UI for module B', '2024-02-10', 'InProgress'),
(3, 'Project X', 'Bug fixing', '2024-03-05', 'Completed'),
(4, 'Project Z', 'Write documentation', '2024-04-20', 'InProgress'),
(5, 'Project Y', 'Optimize database queries', '2024-05-15', 'Pending'),
(6, 'Project Z', 'Testing phase', '2024-06-30', 'InProgress'),
(7, 'Project X', 'Implement feature B', '2024-07-10', 'InProgress'),
(8, 'Project Y', 'Write backend logic', '2024-08-20', 'Completed'),
(9, 'Project Z', 'Deployment', '2024-09-05', 'Pending'),
(10, 'Project X', 'Performance tuning', '2024-10-15', 'InProgress'),
(11, 'Project Y', 'QA testing', '2024-11-10', 'InProgress'),
(12, 'Project Z', 'Client demo preparation', '2024-12-20', 'Completed'),
(13, 'Project X', 'Final review', '2025-01-10', 'Completed'),
(14, 'Project Y', 'Deployment', '2025-02-05', 'InProgress'),
(15, 'Project Z', 'Bug fixing', '2025-03-15', 'Pending');
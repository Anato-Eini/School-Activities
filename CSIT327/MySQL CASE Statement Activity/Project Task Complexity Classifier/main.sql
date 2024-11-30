SELECT
    project_name,
    CASE
        WHEN COUNT(project_name) > 10 THEN 'High'
        WHEN COUNT(project_name) >= 5 THEN 'Medium'
        ELSE 'Low'
    END AS task_complexity
FROM
    ProjectTasks
GROUP BY
    project_name;
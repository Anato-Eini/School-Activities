SELECT Tasks.task_name, Contractors.contractor_name
FROM Tasks, Contractors;

SELECT Engineers.engineer_name, Tasks.task_name
FROM Tasks, Engineers;

SELECT Engineers.engineer_name, Contractors.contractor_name
FROM Engineers, Contractors;
CREATE TABLE ClinicalTrials (
    trial_id INT PRIMARY KEY,
    trial_name VARCHAR(100),
    phase VARCHAR(50),
    start_date DATE,
    end_date DATE,
    lead_scientist VARCHAR(100),
    budget DECIMAL(10,2),
    status VARCHAR(50),
    participants_count INT,
    result_summary TEXT
);

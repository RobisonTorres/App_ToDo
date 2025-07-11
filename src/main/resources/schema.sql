-- This file is used to create the table in the database.

CREATE TABLE IF NOT EXISTS sectors (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(80) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS tasks (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(80) NOT NULL,
    date DATE,
    status VARCHAR(40),
    priority VARCHAR(40),
    sector_id INT,
    FOREIGN KEY (sector_id) REFERENCES sectors(id)
);

INSERT INTO sectors (name) VALUES ('Development'), ('QA'), ('Management');

INSERT INTO tasks (name, date, status, priority, sector_id) VALUES
('Finish project report', '2025-07-10', 'In_Progress', 'High', 3),       
('Team meeting', '2025-07-07', 'Completed', 'Medium', 3),                
('Code review', '2025-07-08', 'In_Progress', 'High', 2),                 
('Update documentation', '2025-07-09', 'Suspended', 'Low', 1),           
('Fix login bug', '2025-07-06', 'Completed', 'Critical', 1),             
('Plan sprint', '2025-07-11', 'In_Progress', 'Medium', 3);               
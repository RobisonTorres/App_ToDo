CREATE TABLE IF NOT EXISTS tasks (
    id INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(80) NOT NULL,
    date DATE,
    status VARCHAR(40),
    priority VARCHAR(40)
);

INSERT INTO tasks (name, date, status, priority) VALUES
('Finish project report', '2025-07-10', 'IN_PROGRESS', 'High'),
('Team meeting', '2025-07-07', 'COMPLETED', 'Medium'),
('Code review', '2025-07-08', 'IN_PROGRESS', 'High'),
('Update documentation', '2025-07-09', 'SUSPENDED', 'Low'),
('Fix login bug', '2025-07-06', 'COMPLETED', 'Critical'),
('Plan sprint', '2025-07-11', 'IN_PROGRESS', 'Medium');
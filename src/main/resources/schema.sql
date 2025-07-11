-- This file is used to create the tables in the database.
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
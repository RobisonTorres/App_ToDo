# App ToDo

The main goal of this project is to facilitate task management through a simple and intuitive interface. 

This application uses **HTML, CSS, and JavaScript** on the frontend, and **Java with Spring Boot** on the backend. It allows users to **create, read, update, and delete** tasks (CRUD), providing a practical way to keep track of their to-do items.

## ✨ Features
- List all available tasks.
- Add a new task.
- Update task.
- Delete task from the database.
- Search and order task by its information.

## Update README - New Scope

The project has undergone a slight change in scope. Now, each task is associated with a specific sector of the company, helping to ensure a more robust and organized management system. This association allows for better categorization and prioritization of tasks, ultimately enhancing overall productivity.

## Methodology
This project follows the agile methodology, using **Kanban** for task management.

## 🛠️ Tech Stack & Prerequisites

### Prerequisites
- **Java 17 or higher**
- **Maven** (to build and run the project)

### Tech Stack
- **Spring Boot** – for building the backend
- **Spring Data JDBC** – for interacting with the database
- **H2 Database** – in-memory testing database (can be replaced with a persistent one)
- **ModelMapper** – for mapping DTOs to entities

## 🚀 Setup Instructions

1. **Clone the repository**
   ```bash
   git clone https://github.com/RobisonTorres/App_ToDo.git
   cd App_ToDo
   ```

2. **Build the project**
   ```bash
   mvn clean install
   ```

3. **Run the application**
   ```bash
   mvn spring-boot:run
   ```

4. **Access the app web**
   ```
   http://localhost:8080/apptodo.html
   ```

## 📚 Controller - Endpoints

```
| HTTP Method |            Endpoint            |            Description             |
|-------------|--------------------------------|------------------------------------|
| GET         | `/get_tasks`                   | List all tasks                     |
| GET         | `/get_task/{id}`               | Retrieve a specific task by ID     |
| POST        | `/create_task`                 | Create a new task                  |
| PUT         | `/update_task/{id}`            | Update a task by ID                |
| DELETE      | `/delete_task/{id}`            | Delete a task by ID                |
```
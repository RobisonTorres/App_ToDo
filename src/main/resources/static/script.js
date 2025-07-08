document.getElementById('addNewTask').addEventListener('click', displayForm);
function displayForm() {

    // This function...
    const form = document.getElementById('popForm');
    return form.style.display = 'block';
}

function showAllTask() {

    // This function...
    fetch('http://localhost:8080/get_tasks', {
        method: 'GET',
        headers: { 'Content-Type': 'application/json' } 
    })
    .then(response=> response.json())
    .then(data => {
        let output = ``;
        data.forEach(task => {
            output += `
                    <div class="card mb-3">
                        <div class="card-body">
                            <h5 class="card-title">${task.name}</h5>
                            <p class="card-text"><strong>Date:</strong> ${task.date}</p>
                            <p class="card-text"><strong>Status:</strong> ${task.status}</p>
                            <p class="card-text"><strong>Priority:</strong> ${task.priority}</p>
                            <button onclick="editTask(${task.id})">Edit</button>
                            <button onclick="deleteTask(${task.id})">Delete</button>
                        </div>
                    </div>
                    `;});
        document.getElementById('showAllTasks').innerHTML = output;
    }).catch(error => {
        console.error('Error fetching tasks:', error);
        document.getElementById('showAllTasks').innerHTML = '<div>Error loading tasks.</div>';
    })
}

function deleteTask(id) {

    // This function...
    if(!confirm("Are you sure you want to delete this task?")) {
        return;
    };
    let taskId = parseInt(id, 10);
    fetch(`http://localhost:8080/delete_task/${taskId}`, {
        method: 'DELETE'
    })
    .then(response => {
        if (response.ok) {
            alert("Task deleted successfully.");
            showAllTask();
        } else {
            alert("Failed to delete task.");
        }
    });
}

showAllTask();
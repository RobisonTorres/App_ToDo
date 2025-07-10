document.getElementById('addNewTask').addEventListener('click', displayForm);
function displayForm() {

    // This function displays the form for adding a new task.
    closeFormUpdate(event);
    const form = document.getElementById('popForm');
    return form.style.display = 'block';
}

document.getElementById('closeForm').addEventListener('click', closeForm);
function closeForm(event) {

    // This function closes the form for adding a new task.
    event.preventDefault(); // It prevents form submission if button is in a form.
    const form = document.getElementById('popForm');
    return form.style.display = 'none';
}

function displayFormUpdate() {

    // This function displays the form for updating on existing task.
    closeForm(event);
    const form = document.getElementById('popFormUpdate');
    return form.style.display = 'block';
}

document.getElementById('closeFormUpdate').addEventListener('click', closeFormUpdate);
function closeFormUpdate(event) {

    // This function closes the form for updating on existing task.
    event.preventDefault();
    const form = document.getElementById('popFormUpdate');
    return form.style.display = 'none';
}

function showAllTask() {

    // This function fetches all tasks from the server and displays them.
    return fetch('http://localhost:8080/get_tasks', {
        method: 'GET',
        headers: { 'Content-Type': 'application/json' } 
    })
    .then(response=> response.json())
    .then(data => {
        let output = ``;
        data.forEach(task => {
            output += `
                        <div id="cards" class="col-md-6 col-lg-4">
                        <div class="card shadow h-100 border-secondary">
                            <div class="card-body d-flex flex-column">
                            <h5 class="card-title text-center text-primary mb-4">
                                <i class="fas fa-tasks me-2"></i>${task.name}
                            </h5>

                            <p class="card-text mb-2">
                                <i class="far fa-calendar-alt me-2 text-muted"></i><strong>Date:</strong> ${task.date}
                            </p>
                            <p class="card-text mb-2">
                                <i class="fas fa-info-circle me-2 text-muted"></i><strong>Status:</strong> ${task.status}
                            </p>
                            <p class="card-text mb-3">
                                <i class="fas fa-exclamation-triangle me-2 text-muted"></i><strong>Priority:</strong> ${task.priority}
                            </p>

                            <div class="d-flex justify-content-center gap-2 mt-auto pt-3">
                                <button class="btn btn-outline-primary flex-grow-1" onclick="editTaskLoad(${task.id})">
                                <i class="fas fa-pen me-1"></i> Edit
                                </button>
                                <button class="btn btn-outline-danger flex-grow-1" onclick="deleteTask(${task.id})">
                                <i class="fas fa-trash me-1"></i> Delete
                                </button>
                            </div>
                            </div>
                        </div>
                        </div>
                    `;});
        document.getElementById('showAllTasks').innerHTML = output;
    }).catch(error => {
        console.error('Error fetching tasks:', error);
        document.getElementById('showAllTasks').innerHTML = '<div>Error loading tasks.</div>';
    })
}

function addTask() {

    // This function adds a new task by sending a POST request to the server.
    event.preventDefault();
    const taskDto = {
        name: document.getElementById('name').value,
        date: document.getElementById('date').value,   
        status: document.getElementById('status').value,
        priority: document.getElementById('priority').value 
    }
    if (!taskDto.name || !taskDto.date || !taskDto.status || !taskDto.priority) {
        alert("Please fill in all fields.");
        return;
    }
    fetch('http://localhost:8080/create_task', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(taskDto)
    })
    .then(response => {
        if (response.ok) {
            alert('Task created successfully!');
            document.querySelector('form').reset();
            location.reload(true);
        } else {
            alert('Failed to create task.');
        }
    })
    .catch(error => {
        console.error('Error:', error);
        alert('Error creating task.');
    });
}

function editTaskLoad(id) {

    // This function loads the task data into the update form for editing.
    displayFormUpdate();
    let taskId = parseInt(id, 10);
    fetch(`http://localhost:8080/get_task/${taskId}`, {
        method: 'GET',
        headers: { 'Content-Type': 'application/json' }
    })
    .then(response => response.json())
    .then(task => {
        document.getElementById('taskId').value = task.id;
        document.getElementById('nameUpdate').value = task.name;
        document.getElementById('dateUpdate').value = task.date;
        document.getElementById('statusUpdate').value = task.status;
        document.getElementById('priorityUpdate').value = task.priority;
    })
    .catch(error => {
        console.error('Error fetching task:', error);
        alert('Error loading task for editing.');
    })
}

function updateTask(event) {

    // This function updates on existing task by sending a PUT request to the server.
    event.preventDefault();
    const taskDto = {
        id: document.getElementById('taskId').value,
        name: document.getElementById('nameUpdate').value,
        date: document.getElementById('dateUpdate').value,
        status: document.getElementById('statusUpdate').value,
        priority: document.getElementById('priorityUpdate').value
    }
    if (!taskDto.name || !taskDto.date || !taskDto.status || !taskDto.priority) {
        alert("Please fill in all fields.");
        return;
    }
    fetch(`http://localhost:8080/update_task/${taskDto.id}`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(taskDto)
    })
    .then(response => {
        if (response.ok) {
            alert('Task updated successfully!');
            document.querySelector('form').reset();
            location.reload(true);
        } else {
            alert('Failed to update task.');
        }
    })
    .catch(error => {
        console.error('Error:', error);
        alert('Error updating task.');
    });
}

function deleteTask(id) {

    // This function deletes a task by sending a DELETE request to the server.
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
            orderTasks();
        } else {
            alert("Failed to delete task.");
        }
    });
}

async function orderTasks() {

    // This function sorts tasks by their status and date.
    await showAllTask();
    const allTasks = document.querySelector('#showAllTasks');
    const tasks = allTasks.querySelectorAll('div#cards');
    const sortedTasks = Array.from(tasks).sort((a, b) => {
        const statusA = a.querySelector('.card-text:nth-of-type(2)').textContent.toLowerCase();
        const statusB = b.querySelector('.card-text:nth-of-type(2)').textContent.toLowerCase();
        const isCompletedA = statusA.includes('completed');
        const isCompletedB = statusB.includes('completed');
        if (isCompletedA && !isCompletedB) return 1;
        if (!isCompletedA && isCompletedB) return -1;
        
        // If both are completed or both are not, keep original order or sort by date if needed
        const dateA = new Date(a.querySelector('.card-text').textContent.split(': ')[1]);
        const dateB = new Date(b.querySelector('.card-text').textContent.split(': ')[1]);
        return dateA - dateB;
    });
    allTasks.innerHTML = '';
    sortedTasks.forEach(task => allTasks.appendChild(task)); 
}

const search = document.querySelector('#searchTask');
const allTasks = document.querySelector('#showAllTasks');
search.addEventListener('input', () => {

    // This function filters tasks based on the search input.
    const searchValue = search.value.toLowerCase();
    const tasks = allTasks.querySelectorAll('div#cards');    
    tasks.forEach(task => {
        const info = task.querySelector('h5');
        
        // This line below is useful for ensuring that the search functionality does not break.
        if (info) {
            const taskName = info.textContent.toLowerCase();
            if (!taskName.includes(searchValue)) {
                task.style.display = 'none';
            } else {
                task.style.display = '';
            }
        }
    });
});

orderTasks();
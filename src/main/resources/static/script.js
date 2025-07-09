document.getElementById('addNewTask').addEventListener('click', displayForm);
function displayForm() {

    // This function...
    const form = document.getElementById('popForm');
    return form.style.display = 'block';
}

function displayFormUpdate() {

    // This function...
    const form = document.getElementById('popFormUpdate');
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
                            <button onclick="editTaskLoad(${task.id})">Edit</button>
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

function addTask() {

    // This function...
    event.preventDefault();
    const taskDto = {
        name: document.getElementById('name').value,
        date: document.getElementById('date').value,   
        status: document.getElementById('status').value,
        priority: document.getElementById('priority').value 
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
            //closeForm(event);
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

    // This function...
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
        console.log(task);
    })
    .catch(error => {
        console.error('Error fetching task:', error);
        alert('Error loading task for editing.');
    })
}

function updateTask(event) {

    // This function...
    event.preventDefault();
    const taskDto = {
        id: document.getElementById('taskId').value,
        name: document.getElementById('nameUpdate').value,
        date: document.getElementById('dateUpdate').value,
        status: document.getElementById('statusUpdate').value,
        priority: document.getElementById('priorityUpdate').value
    }
    console.log(taskDto);
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
            showAllTask();
        } else {
            alert('Failed to update car.');
        }
    })
    .catch(error => {
        console.error('Error:', error);
        alert('Error updating car.');
    });
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
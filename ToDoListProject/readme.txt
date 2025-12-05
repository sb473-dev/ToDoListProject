To do list project 

A simple To do list desktop app that is written in java.

The app lets you:
1. Create a task
2. Update a task's desc
3. Mark a task as complete
4. Delete task
5. Show tasks
6. Show complete tasks
7. Save & load tasks from txt file

Tech
1.Java
2.Gradle
3.Swing for the gui
4. Java collections for array list

How it functions
1. "id" for integer id
2. "desc" for what the task is
3. complete = true/false

Tasks are stored in taskmanager using arraylist<task>.
'TaskManager' is used for adding, updating, completing, deleting, and saving and loading tasks.

The GUI class handles the Swing interface where the user could:
1. Type a desc to add or update a task
2. Type an ID to update, complete, or delete a task
3.Click buttons to perform an action
4. View tasks in tect area

Tasks are saved to txt file called "tasks.txt".

Running
1. open the project as gradle project.
2. Make sure java is installed
3. Run 'ToDogui' class
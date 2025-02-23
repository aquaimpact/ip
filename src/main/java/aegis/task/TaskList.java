package aegis.task;

import java.util.ArrayList;
import java.util.Collections;
import java.util.stream.Collectors;

import aegis.exception.TaskInputException;

/**
 * Represents a list of tasks in the aegis.Aegis chatbot.
 * A task list allows for the management of tasks, including adding, removing, and marking tasks as done or undone.
 * It also supports sorting tasks by due date.
 */

public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructs a TaskList object with the specified list of tasks.
     *
     * @param t The list of tasks to initialize the TaskList.
     */
    public TaskList(ArrayList<Task> t) {
        tasks = t;
    }

    /**
     * Constructs an empty TaskList.
     * Initially, the list contains no tasks.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Adds a task to the task list.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }

    /**
     * Retrieves the task at the specified index.
     * Throws a TaskInputException if the index is out of bounds or if the task list is empty.
     *
     * @param index The index of the task to retrieve.
     * @return The task at the specified index.
     * @throws TaskInputException If the task list is empty or the index is invalid.
     */
    public Task getTask(int index) throws TaskInputException {
        if (tasks.isEmpty()) {
            throw new TaskInputException("No task available!");
        } else if (index < 0 || index >= tasks.size()) {
            throw new TaskInputException("Invalid task index!");
        }
        return tasks.get(index);
    }

    /**
     * Removes the task at the specified index.
     * Throws a TaskInputException if the task list is empty or the index is invalid.
     *
     * @param index The index of the task to remove.
     * @return The task that was removed.
     * @throws TaskInputException If the task list is empty or the index is invalid.
     */
    public Task removeTask(int index) throws TaskInputException {
        if (tasks.isEmpty()) {
            throw new TaskInputException("No task available!");
        }
        Task t = getTask(index); // Ensure index is valid
        tasks.remove(index);
        return t;
    }

    /**
     * Marks the task at the specified index as done.
     * Throws a TaskInputException if the task list is empty or the index is invalid.
     *
     * @param index The index of the task to mark as done.
     * @return The task that was marked as done.
     * @throws TaskInputException If the task list is empty or the index is invalid.
     */
    public Task markTaskAsDone(int index) throws TaskInputException {
        getTask(index).markAsDone();
        return getTask(index);
    }

    /**
     * Marks the task at the specified index as undone.
     * Throws a TaskInputException if the task list is empty or the index is invalid.
     *
     * @param index The index of the task to mark as undone.
     * @return The task that was marked as undone.
     * @throws TaskInputException If the task list is empty or the index is invalid.
     */
    public Task markTaskAsUndone(int index) throws TaskInputException {
        getTask(index).markAsUndone();
        return getTask(index);
    }

    /**
     * Retrieves the tasks sorted by their due dates in descending order.
     * The tasks are sorted based on their deadlines or event end times.
     *
     * @return A list of tasks sorted by due dates in descending order.
     */
    public ArrayList<Task> getSortedDueDates() {
        ArrayList<Task> sortedTasks = new ArrayList<>(tasks);
        Collections.sort(sortedTasks, Collections.reverseOrder());
        return sortedTasks;
    }

    /**
     * Retrieves the list of all tasks.
     *
     * @return The list of tasks.
     */
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    /**
     * Retrieves the number of tasks in the task list.
     *
     * @return The number of tasks in the task list.
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Searches for tasks that contain the given search string in their name.
     * The search is case-insensitive, meaning "Meeting" and "meeting" will both match "meeting".
     *
     * @param searchStr The substring to search for in task names.
     * @return A new {@code TaskList} containing tasks whose names include the search string.
     */
    public TaskList searchByName(String searchStr) {
        ArrayList<Task> searchResults = new ArrayList<>(tasks.stream()
                .filter(task -> task.getTaskName().toLowerCase().contains(searchStr.toLowerCase()))
                .collect(Collectors.toCollection(ArrayList::new)));
        return new TaskList(searchResults);
    }

    /**
     * Checks if the given task already exists in the task list.
     *
     * @param task The task to check for existence.
     * @return {@code true} if the task exists in the list, {@code false} otherwise.
     */
    public boolean checkIfExists(Task task) {
        return tasks.stream().anyMatch(t -> t.equals(task));
    }
}

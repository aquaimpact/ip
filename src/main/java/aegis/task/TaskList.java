package aegis.task;

import aegis.exception.TaskInputException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TaskList {
    private ArrayList<Task> tasks;

    public TaskList(ArrayList<Task> t){
        tasks = t;
    }

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public void addTask(Task task) {
        tasks.add(task);
    }

    public Task getTask(int index) throws TaskInputException {
        if(tasks.isEmpty()) {
            throw new TaskInputException("No task available!");
        } else if (index < 0 || index >= tasks.size()) {
            throw new TaskInputException("Invalid task index!");
        }
        return tasks.get(index);
    }

    public Task removeTask(int index) throws TaskInputException {
        if(tasks.isEmpty()) throw new TaskInputException("No task available!");
        Task t = getTask(index); // Ensure index is valid
        tasks.remove(index);
        return t;
    }

    public Task markTaskAsDone(int index) throws TaskInputException {
        getTask(index).markAsDone();
        return getTask(index);
    }

    public Task markTaskAsUndone(int index) throws TaskInputException {
        getTask(index).markAsUndone();
        return getTask(index);
    }

    public  ArrayList<Task> getSortedDueDates() {
        ArrayList<Task> sortedTasks = new ArrayList<>(tasks);
        Collections.sort(sortedTasks);
        return sortedTasks;
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public int getSize() {
        return tasks.size();
    }

    public TaskList searchByName(String searchStr) {
        ArrayList<Task> searchResults = new ArrayList<>(tasks.stream()
                .filter(task -> task.getTaskName().contains(searchStr))
                .toList());
        return new TaskList(searchResults);
    }
}

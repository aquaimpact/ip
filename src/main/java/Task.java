/**
 * Represents a generic task in the Aegis chatbot.
 * A task has a name and a completion status, which can be marked as done or undone.
 */
public class Task {
    private String taskName;
    private boolean isComplete;

    /**
     * Constructs a Task object with the specified task name.
     * The task is initially marked as not complete.
     *
     * @param taskName The name or description of the task.
     * @throws TaskInputException If the task name is empty.
     */
    public Task(String taskName) throws TaskInputException {
        if (taskName.isEmpty()) {
            throw new TaskInputException("Task name cannot be empty");
        }
        this.taskName = taskName;
        this.isComplete = false;
    }

    /**
     * Gets the status icon for the task.
     * An "X" indicates the task is complete, and a blank space indicates it is not complete.
     *
     * @return The status icon ("X" for complete, " " for not complete).
     */
    public String getStatusIcon() {
        return isComplete ? "X" : " ";
    }

    /**
     * Marks the task as done, setting its completion status to true.
     */
    public void markAsDone() {
        isComplete = true;
    }

    /**
     * Marks the task as undone, setting its completion status to false.
     */
    public void markAsUndone() {
        isComplete = false;
    }

    /**
     * Returns the string representation of the task.
     * The format includes the status icon and the task name.
     *
     * @return A string representation of the task.
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + taskName;
    }
}

/**
 * Represents a Todo task in the Aegis chatbot.
 * A Todo task is a simple task that does not have a deadline or a specific time frame.
 */
public class Todo extends Task {

    /**
     * Constructs a Todo task with the specified task name.
     *
     * @param taskName The name or description of the Todo task.
     * @throws TaskInputException If the task name is empty.
     */
    public Todo(String taskName) throws TaskInputException {
        super(taskName);
    }

    /**
     * Returns the string representation of the Todo task.
     * The format includes a "[T]" prefix followed by the task's status icon and name.
     *
     * @return A string representation of the Todo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

package aegis.task;

import aegis.exception.TaskInputException;

/**
 * Represents a aegis.task.Todo task in the aegis.Aegis chatbot.
 * A aegis.task.Todo task is a simple task that does not have a deadline or a specific time frame.
 */
public class Todo extends Task {

    /**
     * Constructs a aegis.task.Todo task with the specified task name.
     *
     * @param taskName The name or description of the aegis.task.Todo task.
     * @throws TaskInputException If the task name is empty.
     */
    public Todo(String taskName) throws TaskInputException {
        super(taskName);
    }

    /**
     * Returns the string representation of the aegis.task.Todo task.
     * The format includes a "[T]" prefix followed by the task's status icon and name.
     *
     * @return A string representation of the aegis.task.Todo task.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String toCsv() {
        return "T||" + super.toCsv();
    }
}

/**
 * Represents a deadline task in the Aegis chatbot.
 * A deadline task has a task name and a due date (by).
 */
public class Deadline extends Task {
    private String by;

    /**
     * Constructs a Deadline object with the specified task name and due date.
     *
     * @param taskName The name or description of the task.
     * @param by The due date or deadline for the task.
     * @throws TaskInputException If the task name or due date is invalid.
     */
    public Deadline(String taskName, String by) throws TaskInputException {
        super(taskName);
        this.by = by;
    }

    /**
     * Returns the string representation of the deadline task.
     * The format includes the task type, task name, and the due date.
     *
     * @return A string representation of the deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by + ")";
    }

    @Override
    public String toCSV() {
        return "D||" + super.toCSV() + "||" + by;
    }
}

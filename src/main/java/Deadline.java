import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a deadline task in the Aegis chatbot.
 * A deadline task has a task name and a due date (by).
 */
public class Deadline extends Task {
    private LocalDateTime by;
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy HHmm");
    /**
     * Constructs a Deadline object with the specified task name and due date.
     *
     * @param taskName The name or description of the task.
     * @param by The due date or deadline for the task.
     * @throws TaskInputException If the task name or due date is invalid.
     */
    public Deadline(String taskName, String by) throws TaskInputException, DateTimeParseException {
        super(taskName);
        this.by = LocalDateTime.parse(by, formatter);
    }

    /**
     * Returns the string representation of the deadline task.
     * The format includes the task type, task name, and the due date.
     *
     * @return A string representation of the deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(formatter) + ")";
    }

    @Override
    public String toCSV() {
        return "D||" + super.toCSV() + "||" + by.format(formatter);
    }
}
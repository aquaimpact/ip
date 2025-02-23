package aegis.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import aegis.exception.TaskInputException;

/**
 * Represents a deadline task in the aegis.Aegis chatbot.
 * A deadline task has a task name and a due date (by).
 */
public class Deadline extends Task implements Comparable<Task> {
    private LocalDateTime by;
    private DateTimeFormatter storeFormatter = DateTimeFormatter.ofPattern("M/d/yyyy HHmm");
    private DateTimeFormatter showFormatter = DateTimeFormatter.ofPattern("d/M/yyyy HH:mm");

    /**
     * Constructs a Deadline object with the specified task name and due date.
     *
     * @param taskName The name or description of the task.
     * @param by The due date or deadline for the task in the format "M/d/yyyy HHmm".
     * @throws TaskInputException If the task name is invalid or empty.
     * @throws DateTimeParseException If the due date is in an invalid format.
     */
    public Deadline(String taskName, String by) throws TaskInputException, DateTimeParseException {
        super(taskName);
        this.by = LocalDateTime.parse(by, storeFormatter);
    }

    public LocalDateTime getBy() {
        return by;
    }

    /**
     * Parses the input string to create a {@code Deadline} task.
     * <p>
     * The method expects the input to be formatted as:
     * {@code "deadline <task description> /by <due date>"}. It extracts the task description and due date,
     * then creates a {@code Deadline} object.
     * </p>
     *
     * @param input The raw user input containing the deadline description and due date.
     * @return A {@code Deadline} object with the extracted description and due date.
     * @throws TaskInputException If the input does not contain a valid "/by" separator.
     */
    public static Deadline formatDeadline(String input) throws TaskInputException {
        String[] deadlineParts = input.split(" /by ");
        assert deadlineParts.length >= 2 : "DEADLINE command must have a description and a due date";
        if (deadlineParts.length < 2) {
            throw new TaskInputException("You did not specify a by date!");
        }
        String deadlineDescription = deadlineParts[0].substring(8).trim();
        String deadlineDate = deadlineParts[1].trim();
        return new Deadline(deadlineDescription, deadlineDate);
    }

    /**
     * Returns the string representation of the deadline task.
     * The format includes the task type, task name, and the due date.
     *
     * @return A string representation of the deadline task.
     */
    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + by.format(showFormatter) + ")";
    }

    /**
     * Converts the Deadline task to a CSV-compatible string format.
     * The format includes the task type, completion status, task name, and the due date.
     *
     * @return A CSV-compatible string representing the deadline task.
     */
    @Override
    public String toCsv() {
        return "D||" + super.toCsv() + "||" + by.format(storeFormatter);
    }

    /**
     * Compares this deadline task with another object (another deadline task or event).
     * The comparison is based on the due date (the "by" field).
     *
     * @param o The object to be compared.
     * @return A negative integer, zero, or a positive integer if this deadline is less than,
     *         equal to, or greater than the specified object, respectively.
     */
    @Override
    public int compareTo(Task o) {
        if (o instanceof Deadline) {
            return this.by.compareTo(((Deadline) o).getBy());
        } else if (o instanceof Event) {
            return this.by.compareTo(((Event) o).getTo()); // Compare deadline date with event end date
        }
        return 0;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        return (obj instanceof Deadline other)
                && super.equals(obj)
                && other.by.equals(this.by);
    }
}

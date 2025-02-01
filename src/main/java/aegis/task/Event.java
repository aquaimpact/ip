package aegis.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import aegis.exception.TaskInputException;

/**
 * Represents an event task in the Aegis chatbot.
 * An event task has a task name, a start time (from), and an end time (to).
 */
public class Event extends Task implements Comparable<Task> {

    private DateTimeFormatter storeFormatter = DateTimeFormatter.ofPattern("M/d/yyyy HHmm");
    private DateTimeFormatter showFormatter = DateTimeFormatter.ofPattern("d/M/yyyy HH:mm");
    private LocalDateTime from;
    private LocalDateTime to;

    /**
     * Constructs an Event object with the specified task name, start time, and end time.
     *
     * @param taskName The name or description of the event.
     * @param from The start time of the event in the format "M/d/yyyy HHmm".
     * @param to The end time of the event in the format "M/d/yyyy HHmm".
     * @throws TaskInputException If the task name, start time, or end time is invalid.
     * @throws DateTimeParseException If the start time or end time is in an invalid format.
     */
    public Event(String taskName, String from, String to) throws TaskInputException, DateTimeParseException {
        super(taskName);
        this.from = LocalDateTime.parse(from, storeFormatter);
        this.to = LocalDateTime.parse(to, storeFormatter);
    }

    /**
     * Returns the string representation of the event task.
     * The format includes the task type, task name, start time, and end time.
     *
     * @return A string representation of the event task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (from: " + from.format(showFormatter) + " to: " + to.format(showFormatter) + ")";
    }

    /**
     * Converts the Event task to a CSV-compatible string format.
     * The format includes the task type, completion status, task name, start time, and end time.
     *
     * @return A CSV-compatible string representing the event task.
     */
    @Override
    public String toCsv() {
        return "E||" + super.toCsv() + "||" + from.format(storeFormatter) + "||" + to.format(storeFormatter);
    }

    /**
     * Compares this event task with another object (another event task or deadline).
     * The comparison is based on the end time (the "to" field).
     *
     * @param o The object to be compared.
     * @return A negative integer, zero, or a positive integer if this event's end time is earlier than,
     *         equal to, or later than the specified object, respectively.
     */
    @Override
    public int compareTo(Task o) {
        if (o instanceof Event) {
            return ((Event) o).to.compareTo(this.to);
        } else if (o instanceof Deadline) {
            return o.compareTo(this);
        }
        return 0;
    }
}

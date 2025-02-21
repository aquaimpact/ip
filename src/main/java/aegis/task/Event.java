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

    public LocalDateTime getTo() {
        return to;
    }

    /**
     * Parses the input string to create an {@code Event} task.
     * <p>
     * The method expects the input to be formatted as:
     * {@code "event <description> /from <start date> /to <end date>"}.
     * It extracts the description, start date, and end date, then creates an {@code Event} object.
     * </p>
     *
     * @param input The raw user input containing the event description, start date, and end date.
     * @return An {@code Event} object with the extracted description, start date, and end date.
     * @throws TaskInputException If the input does not contain both "/from" and "/to" separators.
     */
    public static Event formatEvent(String input) throws TaskInputException {
        String[] eventParts = input.split(" /from | /to ");
        assert eventParts.length >= 3 : "EVENT command must have a description, from date, and to date";
        if (eventParts.length < 3) {
            throw new TaskInputException("You did not specify a from or to date!");
        }
        String eventDescription = eventParts[0].substring(5).trim();
        String eventFrom = eventParts[1].trim();
        String eventTo = eventParts[2].trim();
        return new Event(eventDescription, eventFrom, eventTo);
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
            return this.to.compareTo(((Event) o).to);
        } else if (o instanceof Deadline) {
            return this.to.compareTo(((Deadline) o).getBy()); // Compare event end date with deadline date
        }
        return 0;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        return (obj instanceof Event other)
                && super.equals(obj)
                && other.from.equals(this.from)
                && other.to.equals(this.to);
    }
}

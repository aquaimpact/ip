import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents an event task in the Aegis chatbot.
 * An event task has a task name, a start time (from), and an end time (to).
 */
public class Event extends Task implements Comparable{
    private LocalDateTime from, to;
    private DateTimeFormatter storeFormatter = DateTimeFormatter.ofPattern("M/d/yyyy HHmm");
    private DateTimeFormatter showFormatter = DateTimeFormatter.ofPattern("d/M/yyyy HH:mm");
    /**
     * Constructs an Event object with the specified task name, start time, and end time.
     *
     * @param taskName The name or description of the event.
     * @param from The start time of the event.
     * @param to The end time of the event.
     * @throws TaskInputException If the task name, start time, or end time is invalid.
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
        return "[E]" + super.toString() + " (from: " + from.format(showFormatter) + " to: " + to.format(showFormatter) + ")";
    }

    @Override
    public String toCSV() {
        return "E||" + super.toCSV() + "||" + from.format(storeFormatter) + "||" + to.format(storeFormatter);
    }

    @Override
    public int compareTo(Object o) {
        if(o instanceof Event) {
            return ((Event) o).to.compareTo(this.to);
        } else if (o instanceof Deadline) {
            return ((Deadline) o).compareTo(this.to);
        }
        return 0;
    }
}

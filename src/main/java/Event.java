/**
 * Represents an event task in the Aegis chatbot.
 * An event task has a task name, a start time (from), and an end time (to).
 */
public class Event extends Task {
    private String from, to;

    /**
     * Constructs an Event object with the specified task name, start time, and end time.
     *
     * @param taskName The name or description of the event.
     * @param from The start time of the event.
     * @param to The end time of the event.
     * @throws TaskInputException If the task name, start time, or end time is invalid.
     */
    public Event(String taskName, String from, String to) throws TaskInputException {
        super(taskName);
        this.from = from;
        this.to = to;
    }

    /**
     * Returns the string representation of the event task.
     * The format includes the task type, task name, start time, and end time.
     *
     * @return A string representation of the event task.
     */
    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}

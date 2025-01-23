public class Event extends Task{
    private String from, to;
    public Event(String taskName, String from, String to) throws TaskInputException {
        super(taskName);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + from + " to: " + to + ")";
    }
}

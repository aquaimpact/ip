public class Deadline extends Task{
    protected String by;
    public Deadline(String taskName, String by) {
        super(taskName);
        this.by = by;
    }
}

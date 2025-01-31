public interface Command {
    public abstract void execute(TaskList tasks, FileSave fs) throws TaskInputException;
    public abstract boolean isExit();
}

import java.io.IOException;

public interface Command {
    public abstract void execute(TaskList tasks, FileSave fs) throws TaskInputException, IOException;
    public abstract boolean isExit();
}

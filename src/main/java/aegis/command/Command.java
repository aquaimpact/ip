package aegis.command;

import aegis.exception.TaskInputException;
import aegis.storage.FileSave;
import aegis.task.TaskList;

import java.io.IOException;

public interface Command {
    public abstract void execute(TaskList tasks, FileSave fs) throws TaskInputException, IOException;
    public abstract boolean isExit();
}

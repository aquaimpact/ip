package aegis.command;

import java.io.IOException;

import aegis.exception.TaskInputException;
import aegis.storage.FileSave;
import aegis.task.TaskList;

/**
 * Represents a command that can be executed in the task management system.
 */
public interface Command {

    /**
     * Executes the command, modifying the task list and saving changes if necessary.
     *
     * @param tasks The task list to modify.
     * @param fs    The file storage handler for saving changes.
     * @throws TaskInputException If there is an issue with task input.
     * @throws IOException        If an error occurs while accessing the file system.
     */
    void execute(TaskList tasks, FileSave fs) throws TaskInputException, IOException;

    /**
     * Determines whether executing this command will cause the application to exit.
     *
     * @return {@code true} if this command causes an exit, otherwise {@code false}.
     */
    boolean isExit();
}

package aegis.command;

import aegis.exception.TaskInputException;
import aegis.storage.FileSave;
import aegis.task.TaskList;
import aegis.ui.UIManager;

/**
 * Represents a command that terminates the application gracefully.
 */
public class ExitCommand implements Command {

    /**
     * Displays a farewell message and prepares the program to exit.
     * <p>
     * This command does not modify the task list or file storage.
     *
     * @param tasks The task list (not used in this command).
     * @param fs    The file storage handler (not used in this command).
     * @throws TaskInputException This command does not throw exceptions.
     */
    @Override
    public String execute(TaskList tasks, FileSave fs) throws TaskInputException {
        return UIManager.quitMessage();
    }

    /**
     * Indicates that this command causes the program to exit.
     *
     * @return {@code true}, signaling that the application should terminate.
     */
    @Override
    public boolean isExit() {
        return true;
    }
}

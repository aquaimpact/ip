package aegis.command;

import aegis.exception.TaskInputException;
import aegis.storage.FileSave;
import aegis.task.TaskList;
import aegis.ui.UIManager;

public class ExitCommand implements Command {

    /**
     * @param tasks
     * @param fs
     * @throws TaskInputException
     */
    @Override
    public void execute(TaskList tasks, FileSave fs) throws TaskInputException {
        UIManager.quitMessage();
    }

    /**
     * @return
     */
    @Override
    public boolean isExit() {
        return true;
    }
}

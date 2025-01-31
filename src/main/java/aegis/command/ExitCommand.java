package aegis.commands;

import aegis.exceptions.TaskInputException;
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

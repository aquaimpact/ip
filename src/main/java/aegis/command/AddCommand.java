package aegis.command;

import java.io.IOException;

import aegis.exception.TaskInputException;
import aegis.storage.FileSave;
import aegis.task.Task;
import aegis.task.TaskList;
import aegis.ui.UIManager;

public class AddCommand implements Command {

    private Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * @param tasks
     * @param fs
     * @throws TaskInputException
     */
    @Override
    public void execute(TaskList tasks, FileSave fs) throws TaskInputException, IOException {
        tasks.addTask(task);
        fs.writeToFile(tasks);
        UIManager.printOnItemsAdd(task, tasks.getSize());
    }

    /**
     * @return
     */
    @Override
    public boolean isExit() {
        return false;
    }
}

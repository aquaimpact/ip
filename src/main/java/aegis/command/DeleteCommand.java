package aegis.command;

import aegis.exception.TaskInputException;
import aegis.storage.FileSave;
import aegis.task.Task;
import aegis.task.TaskList;
import aegis.ui.UIManager;

import java.io.IOException;

public class DeleteCommand implements Command {

    private int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * @param tasks
     * @param fs
     * @throws TaskInputException
     */
    @Override
    public void execute(TaskList tasks, FileSave fs) throws TaskInputException, IOException {
        if (tasks.getSize() == 0) {
            throw new TaskInputException("No task available to delete!");
        }

        // Deleting the task
        Task t = tasks.removeTask(index);
        fs.writeToFile(tasks);
        UIManager.printBorders("Noted. I've removed this task:\n" + t + "\nNow you have " + tasks.getSize() + " tasks in the list.");
    }

    /**
     * @return
     */
    @Override
    public boolean isExit() {
        return false;
    }
}

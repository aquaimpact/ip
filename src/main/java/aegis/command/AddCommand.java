package aegis.command;

import java.io.IOException;

import aegis.exception.TaskInputException;
import aegis.storage.FileSave;
import aegis.task.Task;
import aegis.task.TaskList;
import aegis.ui.UIManager;

/**
 * Represents a command to add a task to the task list.
 */
public class AddCommand implements Command {

    private Task task;

    /**
     * Constructs an AddCommand with the specified task.
     *
     * @param task The task to be added.
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Executes the command by adding the task to the task list, saving the updated list to storage,
     * and displaying a confirmation message to the user.
     *
     * @param tasks The task list to which the task will be added.
     * @param fs    The file storage system used to persist the task list.
     * @throws TaskInputException If an error occurs while handling task input.
     * @throws IOException        If an error occurs while writing to the file.
     */
    @Override
    public String execute(TaskList tasks, FileSave fs) throws TaskInputException, IOException {
        if (tasks.checkIfExists(task)) {
            return UIManager.printDuplicateWarning(task);
        }
        tasks.addTask(task);
        fs.writeToFile(tasks);
        return UIManager.printOnItemsAdd(task, tasks.getSize());
    }

    /**
     * Indicates whether this command causes the program to exit.
     *
     * @return {@code false}, since adding a task does not cause the program to exit.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}

package aegis.command;

import java.io.IOException;

import aegis.exception.TaskInputException;
import aegis.storage.FileSave;
import aegis.task.Task;
import aegis.task.TaskList;
import aegis.ui.UIManager;

/**
 * Represents a command to delete a task from the task list.
 */
public class DeleteCommand implements Command {

    private int index;

    /**
     * Constructs a DeleteCommand with the specified task index.
     *
     * @param index The index of the task to be deleted (0-based index).
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Executes the delete command by removing the task at the specified index from the task list.
     * If the task list is empty, an exception is thrown.
     * The updated task list is then saved to the file system.
     *
     * @param tasks The task list from which the task will be deleted.
     * @param fs    The file storage handler for saving the updated task list.
     * @throws TaskInputException If the task list is empty or the index is invalid.
     * @throws IOException        If an error occurs while saving to the file.
     */
    @Override
    public void execute(TaskList tasks, FileSave fs) throws TaskInputException, IOException {
        if (tasks.getSize() == 0) {
            throw new TaskInputException("No task available to delete!");
        }

        // Deleting the task
        Task t = tasks.removeTask(index);
        fs.writeToFile(tasks);
        UIManager.printBorders("Noted. I've removed this task:\n" + t
                + "\nNow you have " + tasks.getSize() + " tasks in the list.");
    }

    /**
     * Indicates whether this command causes the program to exit.
     *
     * @return false, since deleting a task does not terminate the program.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}

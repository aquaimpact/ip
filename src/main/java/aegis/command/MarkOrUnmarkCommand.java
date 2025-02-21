package aegis.command;

import java.io.IOException;

import aegis.exception.TaskInputException;
import aegis.storage.FileSave;
import aegis.task.Task;
import aegis.task.TaskList;
import aegis.ui.UiManager;

/**
 * Represents a command that either marks or unmarks a task as done.
 */
public class MarkOrUnmarkCommand implements Command {

    private final boolean isMark;
    private final int index;

    /**
     * Constructs a command to mark or unmark a task.
     *
     * @param isMark {@code true} to mark the task as done, {@code false} to unmark it.
     * @param index  The index of the task in the task list.
     */
    public MarkOrUnmarkCommand(boolean isMark, int index) {
        this.isMark = isMark;
        this.index = index;
    }

    /**
     * Executes the command to mark or unmark a task.
     *
     * @param tasks The task list containing the user's tasks.
     * @param fs    The file storage handler to save changes.
     * @throws TaskInputException If the task list is empty or the index is out of bounds.
     * @throws IOException        If an error occurs while saving to the file.
     */
    @Override
    public String execute(TaskList tasks, FileSave fs) throws TaskInputException, IOException {
        if (tasks.getSize() == 0) {
            throw new TaskInputException("No tasks available to modify!");
        }

        Task task;
        String returnString;
        if (isMark) {
            task = tasks.markTaskAsDone(index);
            returnString = UiManager.printBorders("Nice! I've marked this task as done:\n" + task);
        } else {
            task = tasks.markTaskAsUndone(index);
            returnString = UiManager.printBorders("OK, I've marked this task as not done yet:\n" + task);
        }

        fs.writeToFile(tasks);
        return returnString;
    }

    /**
     * Indicates that this command does not cause the program to exit.
     *
     * @return {@code false}, signaling that the application should continue running.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}

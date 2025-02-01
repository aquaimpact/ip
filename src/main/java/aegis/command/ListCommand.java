package aegis.command;

import aegis.exception.TaskInputException;
import aegis.storage.FileSave;
import aegis.task.Task;
import aegis.task.TaskList;
import aegis.ui.UIManager;

/**
 * Represents a command that displays all tasks in the task list.
 */
public class ListCommand implements Command {

    /**
     * Executes the command to display all tasks currently stored in the task list.
     * <p>
     * If the task list is empty, an appropriate message is displayed.
     *
     * @param tasks The task list containing the user's tasks.
     * @param fs    The file storage handler (not used in this command).
     * @throws TaskInputException This command does not throw exceptions.
     */
    @Override
    public void execute(TaskList tasks, FileSave fs) throws TaskInputException {
        if (tasks.getSize() == 0) {
            UIManager.printBorders("Your task list is empty.");
            return;
        }

        StringBuilder output = new StringBuilder("Here are the tasks in your list:");
        for (int i = 0; i < tasks.getSize(); i++) {
            Task task = tasks.getTask(i);
            output.append("\n").append(i + 1).append(". ").append(task.toString());
        }

        UIManager.printBorders(output.toString());
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

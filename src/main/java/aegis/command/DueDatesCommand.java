package aegis.command;

import aegis.exception.TaskInputException;
import aegis.storage.FileSave;
import aegis.task.Task;
import aegis.task.TaskList;
import aegis.task.Todo;
import aegis.ui.UIManager;

/**
 * Represents a command that lists all tasks with due dates in ascending order.
 * Tasks of type {@code Todo} are excluded as they do not have due dates.
 */
public class DueDatesCommand implements Command {

    /**
     * Executes the command to display tasks sorted by their due dates.
     * <p>
     * Tasks of type {@code Todo} are ignored since they do not have due dates.
     * The formatted list is then printed to the UI.
     *
     * @param tasks The task list containing tasks to be sorted and displayed.
     * @param fs    The file storage handler (not used in this command).
     * @throws TaskInputException If an error occurs while processing the tasks.
     */
    @Override
    public String execute(TaskList tasks, FileSave fs) throws TaskInputException {
        String output = "Here are the upcoming due dates in your list:";

        int index = 1;
        for (Task t : tasks.getSortedDueDates()) {
            if (t instanceof Todo) {
                continue;
            }
            output += ("\n" + index++ + "." + t.toString());
        }

        return UIManager.printBorders(output);
    }

    /**
     * Indicates whether this command causes the program to exit.
     *
     * @return false, since listing due dates does not terminate the program.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}

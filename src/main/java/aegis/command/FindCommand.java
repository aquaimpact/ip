package aegis.command;

import java.io.IOException;

import aegis.exception.TaskInputException;
import aegis.storage.FileSave;
import aegis.task.Task;
import aegis.task.TaskList;
import aegis.ui.UiManager;

/**
 * Represents a command to search for tasks containing a specific keyword.
 * The search is case-insensitive and matches tasks whose names include the given search string.
 */
public class FindCommand implements Command {

    private String searchStr;

    /**
     * Constructs a {@code FindCommand} with the given search string.
     *
     * @param searchStr The keyword used to search for tasks.
     */
    public FindCommand(String searchStr) {
        this.searchStr = searchStr;
    }

    /**
     * Executes the find command by searching for tasks that contain the search string.
     * Displays the matching tasks in a formatted list.
     *
     * @param tasks The task list to search through.
     * @param fs The file storage system (not used in this command).
     * @throws TaskInputException If an invalid task operation occurs.
     * @throws IOException If an error occurs while accessing storage.
     */
    @Override
    public String execute(TaskList tasks, FileSave fs) throws TaskInputException, IOException {
        TaskList searchResults = tasks.searchByName(searchStr);
        String output = "Here are the matching tasks in your list:";
        for (int i = 1; i <= searchResults.getSize(); i++) {
            Task task = searchResults.getTask(i - 1);
            output += ("\n" + i + "." + task.toString());
        }
        output += "\n" + searchResults.getSize() + " results returned.";
        return UiManager.printBorders(output);
    }

    /**
     * Indicates whether this command causes the program to exit.
     *
     * @return {@code false} since the find command does not terminate the program.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}

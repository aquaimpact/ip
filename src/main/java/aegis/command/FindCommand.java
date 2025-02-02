package aegis.command;

import java.io.IOException;

import aegis.exception.TaskInputException;
import aegis.storage.FileSave;
import aegis.task.Task;
import aegis.task.TaskList;
import aegis.ui.UIManager;

public class FindCommand implements Command{

    private String searchStr;

    public FindCommand(String searchStr) {
        this.searchStr = searchStr;
    }

    /**
     * @param tasks
     * @param fs
     * @throws TaskInputException
     * @throws IOException
     */
    @Override
    public void execute(TaskList tasks, FileSave fs) throws TaskInputException, IOException {
        TaskList searchResults = tasks.searchByName(searchStr);
        String output = "Here are the matching tasks in your list:";
        for (int i = 1; i <= searchResults.getSize(); i++) {
            Task task = searchResults.getTask(i - 1);
            output += ("\n" + i + "." + task.toString());
        }
        output += "\n" + searchResults.getSize() + " results returned.";
        UIManager.printBorders(output);
    }

    /**
     * @return
     */
    @Override
    public boolean isExit() {
        return false;
    }
}

package aegis.command;

import aegis.exception.TaskInputException;
import aegis.storage.FileSave;
import aegis.task.Task;
import aegis.task.TaskList;
import aegis.task.Todo;
import aegis.ui.UIManager;

public class DueDatesCommand implements Command {

    /**
     * @param tasks
     * @param fs
     * @throws TaskInputException
     */
    @Override
    public void execute(TaskList tasks, FileSave fs) throws TaskInputException {
        String output = "Here are the upcoming duedates in your list:";

        int index = 1;
        for(Task t: tasks.getSortedDueDates()) {
            if(t instanceof Todo) continue;
            output += ("\n"+ index++ + "." + t.toString());
        }
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

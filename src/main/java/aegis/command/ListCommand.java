package aegis.command;

import aegis.exception.TaskInputException;

public class ListCommand implements Command {

    /**
     * @param tasks
     * @param fs
     */
    @Override
    public void execute(TaskList tasks, FileSave fs) throws TaskInputException {
        String output = "Here are the tasks in your list:";
        for (int i = 1; i <= tasks.getSize(); i++) {
            Task task = tasks.getTask(i - 1);
            output += ("\n"+ i + "." + task.toString());
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

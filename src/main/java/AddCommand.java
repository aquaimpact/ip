import java.io.IOException;

public class AddCommand implements Command{

    private Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * @param tasks
     * @param fs
     * @throws TaskInputException
     */
    @Override
    public void execute(TaskList tasks, FileSave fs) throws TaskInputException, IOException {
        tasks.addTask(task);
        fs.writeToFile(tasks);
        UIManager.printOnItemsAdd(task, tasks.getSize());
    }

    /**
     * @return
     */
    @Override
    public boolean isExit() {
        return false;
    }
}

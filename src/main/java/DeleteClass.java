public class DeleteClass implements Command{

    private int index;

    public DeleteClass(int index) {
        this.index = index;
    }

    /**
     * @param tasks
     * @param fs
     * @throws TaskInputException
     */
    @Override
    public void execute(TaskList tasks, FileSave fs) throws TaskInputException {
        // Error Handling
        if(tasks.getSize() == 1) throw new TaskInputException("You did not specify which task to delete!");

        // Deleting the task
        Task t = tasks.removeTask(index);
        UIManager.printBorders("Noted. I've removed this task:\n" + t + "\nNow you have " + tasks.getSize() + " tasks in the list.");
    }

    /**
     * @return
     */
    @Override
    public boolean isExit() {
        return false;
    }
}

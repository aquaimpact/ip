package aegis.command;

import aegis.exception.TaskInputException;

import java.io.IOException;

public class MarkOrUnmarkCommand implements Command {

    private boolean isMark;
    private int index;

    public MarkOrUnmarkCommand(boolean isMark, int index) {
        this.isMark = isMark;
        this.index = index;
    }

    /**
     * @param tasks
     * @param fs
     */
    @Override
    public void execute(TaskList tasks, FileSave fs) throws TaskInputException, IOException {
        if(this.isMark){
            // Error Handling
            if(tasks.getSize() == 1) throw new TaskInputException("You did not specify which task to mark done!");

            //Marking the task
            Task t = tasks.markTaskAsDone(index);
            UIManager.printBorders("Nice! I've marked this task as done:\n" + t);
        } else {
            // Error Handling
            if(tasks.getSize() == 1) throw new TaskInputException("You did not specify which task to unmark!");

            // Unmarking the task
            Task t = tasks.markTaskAsUndone(index);
            UIManager.printBorders("OK, I've marked this task as not done yet:\n" + t);
        }
        fs.writeToFile(tasks);
    }

    /**
     * @return
     */
    @Override
    public boolean isExit() {
        return false;
    }
}

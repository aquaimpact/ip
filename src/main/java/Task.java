public class Task {
    private String taskName;
    private boolean isComplete;
    public Task(String taskName){
        this.taskName = taskName;
        this.isComplete = false;
    }

    public String getTaskName() {
        return taskName;
    }
    public String getStatusIcon() {
        return isComplete ? "X" : " ";
    }
    public void markAsDone() {
        isComplete = true;
    }
    public void markAsUndone() {
        isComplete = false;
    }

    @Override
    public String toString() {
        return "[" + getStatusIcon() + "] " + taskName;
    }
}

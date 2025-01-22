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
    public String isDone() {
        return isComplete ? "X" : "";
    }
    public void setComplete(boolean complete) {
        isComplete = complete;
    }
}

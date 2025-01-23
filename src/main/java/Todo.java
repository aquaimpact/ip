public class Todo extends Task{

    public Todo(String taskName) throws TaskInputException{
        super(taskName);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}

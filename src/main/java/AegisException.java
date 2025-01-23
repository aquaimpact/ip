public class AegisException extends Exception {
    public AegisException(String message) {
        super(message);
    }
}

class CommandException extends AegisException {
    public CommandException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "____________________________________________________________\n"
                + "Invalid Command: " + super.getMessage()
                + "\n____________________________________________________________\n";
    }
}

class TaskInputException extends AegisException {
    public TaskInputException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "____________________________________________________________\n"
                + "Invalid Task Inputs: " + super.getMessage()
                + "\n____________________________________________________________\n";
    }
}
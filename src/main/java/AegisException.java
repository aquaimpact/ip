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
        return "Invalid Command: " + super.getMessage();
    }
}

class TaskInputException extends AegisException {
    public TaskInputException(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "Invalid Task Inputs: " + super.getMessage();
    }
}
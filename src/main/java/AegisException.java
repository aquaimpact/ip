/**
 * A base exception class for the Aegis chatbot.
 * All custom exceptions in the Aegis chatbot extend this class.
 */
public class AegisException extends Exception {
    /**
     * Constructs an AegisException with the specified error message.
     *
     * @param message The error message.
     */
    public AegisException(String message) {
        super(message);
    }
}

/**
 * An exception class for handling invalid commands in the Aegis chatbot.
 * This is a specific type of {@link AegisException}.
 */
class CommandException extends AegisException {
    /**
     * Constructs a CommandException with the specified error message.
     *
     * @param message The error message.
     */
    public CommandException(String message) {
        super(message);
    }

    /**
     * Provides a user-friendly error message for invalid commands.
     *
     * @return A string representation of the exception with additional context.
     */
    @Override
    public String toString() {
        return "Invalid Command: " + super.getMessage();
    }
}

/**
 * An exception class for handling invalid or incomplete task inputs in the Aegis chatbot.
 * This is a specific type of {@link AegisException}.
 */
class TaskInputException extends AegisException {
    /**
     * Constructs a TaskInputException with the specified error message.
     *
     * @param message The error message.
     */
    public TaskInputException(String message) {
        super(message);
    }

    /**
     * Provides a user-friendly error message for invalid task inputs.
     *
     * @return A string representation of the exception with additional context.
     */
    @Override
    public String toString() {
        return "Invalid Task Inputs: " + super.getMessage();
    }
}
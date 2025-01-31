package aegis.exception;

/**
 * An exception class for handling invalid or incomplete task inputs in the aegis.Aegis chatbot.
 * This is a specific type of {@link AegisException}.
 */
public class TaskInputException extends AegisException {
    /**
     * Constructs a aegis.exceptions.TaskInputException with the specified error message.
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
        return "Invalid aegis.task.Task Inputs: " + super.getMessage();
    }
}

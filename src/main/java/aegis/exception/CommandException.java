package aegis.exception;

/**
 * An exception class for handling invalid commands in the Aegis chatbot.
 * This is a specific type of {@link AegisException}.
 */
public class CommandException extends AegisException {
    /**
     * Constructs a {@code CommandException} with the specified error message.
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

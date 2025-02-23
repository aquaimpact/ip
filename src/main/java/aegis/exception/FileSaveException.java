package aegis.exception;

/**
 * Represents an exception that occurs during file-saving operations.
 */
public class FileSaveException extends AegisException {

    /**
     * Constructs a new {@code FileSaveException} with the specified error message.
     *
     * @param message The detail message explaining the cause of the error.
     */
    public FileSaveException(String message) {
        super(message);
    }

    /**
     * Returns a string representation of this exception.
     *
     * @return A formatted error message indicating a file-saving issue.
     */
    @Override
    public String toString() {
        return "Error Saving File: " + getMessage();
    }
}

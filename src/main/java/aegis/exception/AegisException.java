package aegis.exception;

/**
 * A base exception class for the Aegis chatbot.
 * All custom exceptions in the Aegis chatbot extend this class.
 */
public class AegisException extends Exception {
    /**
     * Constructs an {@code AegisException} with the specified error message.
     *
     * @param message The error message.
     */
    public AegisException(String message) {
        super(message);
    }
}


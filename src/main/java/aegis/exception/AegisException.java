package aegis.exception;

/**
 * A base exception class for the aegis.Aegis chatbot.
 * All custom exceptions in the aegis.Aegis chatbot extend this class.
 */
public class AegisException extends Exception {
    /**
     * Constructs an aegis.exceptions.AegisException with the specified error message.
     *
     * @param message The error message.
     */
    public AegisException(String message) {
        super(message);
    }
}


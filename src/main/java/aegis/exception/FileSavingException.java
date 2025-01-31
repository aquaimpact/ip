package aegis.exceptions;

public class FileSavingException extends AegisException {
    public FileSavingException(String message) {super(message);}

    @Override
    public String toString() {
        return "Error Saving File: " + super.getMessage();
    }
}

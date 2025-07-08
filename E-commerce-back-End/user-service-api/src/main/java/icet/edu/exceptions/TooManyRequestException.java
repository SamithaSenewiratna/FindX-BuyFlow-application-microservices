package icet.edu.exceptions;

public class TooManyRequestException extends RuntimeException {
    public TooManyRequestException(String message) {
        super(message);
    }
}

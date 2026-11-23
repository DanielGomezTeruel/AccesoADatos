package domain.exceptions;

public class ForeignKeyException extends RuntimeException {
    public ForeignKeyException(String message) {
        super(message);
    }
}

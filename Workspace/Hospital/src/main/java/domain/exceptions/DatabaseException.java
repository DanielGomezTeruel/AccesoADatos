package domain.exceptions;

import common.Constants;

public class DatabaseException extends RuntimeException {
    public DatabaseException() {
        super(Constants.DATABASE_ERROR);
    }
    public DatabaseException(String message) {
        super(message);
    }
}

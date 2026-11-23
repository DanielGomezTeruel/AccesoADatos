package domain.exceptions;

import common.Constants;

public class PlayerDbException extends DatabaseException {
    public PlayerDbException(){super(Constants.PATIENT_DB_ERROR);}
    public PlayerDbException(String message) {
        super(message);
    }
}

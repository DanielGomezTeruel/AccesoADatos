package domain.exceptions;

import common.Constants;

public class CredentialsDBException extends DatabaseException {
    public CredentialsDBException() {
        super(Constants.CREDENTIAL_DB_ERROR);
    }
    public CredentialsDBException(String message) {super(message);}
}

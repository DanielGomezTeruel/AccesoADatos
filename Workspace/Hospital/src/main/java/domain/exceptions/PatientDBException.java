package domain.exceptions;

import common.Constants;

public class PatientDBException extends DatabaseException {
    public PatientDBException(){super(Constants.PATIENT_DB_ERROR);}
    public PatientDBException(String message) {
        super(message);
    }
}

package domain.exceptions;

import common.Constants;

public class DoctorDBException extends RuntimeException {
    public DoctorDBException(){super(Constants.DOCTOR_DB_ERROR);}
    public DoctorDBException(String message) {
        super(message);
    }
}

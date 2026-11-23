package domain.exceptions;

import common.Constants;

public class RecordBException extends RuntimeException {
    public RecordBException(){super(Constants.RECORD_DB_ERROR);}
    public RecordBException(String message) {
        super(message);
    }
}

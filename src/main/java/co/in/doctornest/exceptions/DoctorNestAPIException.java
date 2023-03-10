package co.in.doctornest.exceptions;

import org.springframework.http.HttpStatus;

public class DoctorNestAPIException extends RuntimeException {
    private final HttpStatus httpStatus;
    private final String message;

    public DoctorNestAPIException(HttpStatus httpStatus, String message) {
        super(message);
        this.httpStatus = httpStatus;
        this.message = message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    @Override
    public String getMessage() {
        return message;
    }
}

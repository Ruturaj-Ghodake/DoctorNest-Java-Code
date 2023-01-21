package co.in.doctornest.exceptions;

import co.in.doctornest.payloads.ErrorDetails;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.nio.file.AccessDeniedException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler  extends ResponseEntityExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    private ResponseEntity<ErrorDetails> handleResourceNotFoundException(ResourceNotFoundException exception,
                                                                         WebRequest webRequest) {
        ErrorDetails errorDetails = new ErrorDetails();
        errorDetails.setDateTime(LocalDateTime.now());
        errorDetails.setErrorMessage(exception.getFieldName() + " " + exception.getResourceName() + " " + exception.getFieldValue());
        errorDetails.setURL(webRequest.getDescription(false));

        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(DoctorNestAPIException.class)
    public ResponseEntity<ErrorDetails> handleBlogAPIException(DoctorNestAPIException exception, WebRequest webRequest) {
        ErrorDetails errorDetails = new ErrorDetails();
        errorDetails.setDateTime(LocalDateTime.now());
        errorDetails.setErrorMessage(exception.getMessage());
        errorDetails.setURL(webRequest.getDescription(true));

        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<ErrorDetails> handleforAccessDeniedException(AccessDeniedException exception, WebRequest webRequest) {
        ErrorDetails errorDetails = new ErrorDetails();
        errorDetails.setDateTime(LocalDateTime.now());
        errorDetails.setErrorMessage(exception.getMessage());
        errorDetails.setURL(webRequest.getDescription(false));

        return new ResponseEntity<>(errorDetails, HttpStatus.UNAUTHORIZED);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        Map<String, Map<String , Object>> errors = new HashMap<>();
        List<ObjectError> objectErrors = ex.getBindingResult().getAllErrors();
        for (ObjectError o : objectErrors) {
            String filedName = ((FieldError) o).getField();
            String message = o.getDefaultMessage();
            Object passedValue = ((FieldError) o).getRejectedValue();

            errors.put(filedName, Map.of(message,passedValue));
        }
        return new ResponseEntity<>(errors,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorDetails> handleforDataIntegrityViolationException(DataIntegrityViolationException exception, WebRequest webRequest){
        ErrorDetails errorDetails = new ErrorDetails();
        errorDetails.setDateTime(LocalDateTime.now());
        errorDetails.setErrorMessage("Email id or Mobile number already present.");
        errorDetails.setURL(webRequest.getDescription(true));
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_GATEWAY);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorDetails> handleforException(Exception exception, WebRequest webRequest) {
        ErrorDetails errorDetails = new ErrorDetails();
        errorDetails.setDateTime(LocalDateTime.now());
        errorDetails.setErrorMessage(exception.getMessage());
        errorDetails.setURL(webRequest.getDescription(true));

        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

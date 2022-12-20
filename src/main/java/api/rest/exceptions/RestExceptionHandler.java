package api.rest.exceptions;

import api.service.exceptions.DatabaseException;
import api.service.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;
import java.time.Instant;
import java.util.stream.Collectors;

@ControllerAdvice
public class RestExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StandardError> entityNotFound(ResourceNotFoundException e, HttpServletRequest request){
        HttpStatus status = HttpStatus.NOT_FOUND;
        StandardError err = new StandardError();
        err.setTimestamp(Instant.now());
        err.setStatus(status.value());
        err.setError("Resource not found");
        err.setMessage(e.getMessage());
        err.setPath(request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

    @ExceptionHandler(DatabaseException.class)
    public ResponseEntity<StandardError> database(DatabaseException e, HttpServletRequest request){
        HttpStatus status = HttpStatus.BAD_REQUEST;
        StandardError err = new StandardError();
        err.setTimestamp(Instant.now());
        err.setStatus(status.value());
        err.setError("Database exception");
        err.setMessage(e.getMessage());
        err.setPath(request.getRequestURI());
        return ResponseEntity.status(status).body(err);
    }

//    @ExceptionHandler(MethodArgumentNotValidException.class)
//    public ResponseEntity<StandardError> validException(MethodArgumentNotValidException e, HttpServletRequest request){
//        HttpStatus status = HttpStatus.BAD_REQUEST;
//        e.getBindingResult().getAllErrors().stream().map( err -> err.getDefaultMessage());
//        StandardError err = new StandardError();
//        err.setTimestamp(Instant.now());
//        err.setStatus(status.value());
//        err.setError("Not valid exception");
//        err.setMessage(e.getMessage());
//        err.setPath(request.getRequestURI());
//        return ResponseEntity.status(status).body(err);
//    }
}

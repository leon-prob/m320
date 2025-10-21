package ch.noseryoung.blj.exceptions;

import ch.noseryoung.blj.exceptions.loanExceptions.LoanErrorException;
import ch.noseryoung.blj.exceptions.objectNotFound.ObjectNotFoundException;
import io.swagger.v3.oas.annotations.Hidden;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
@Hidden
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<String> handleNotFoundException(ObjectNotFoundException objectNotFoundException){
        return  ResponseEntity.status(HttpStatus.NOT_FOUND).body(objectNotFoundException.getMessage());
    }

    @ExceptionHandler(LoanErrorException.class)
    public ResponseEntity<String> handleLoanErrorException(LoanErrorException loanErrorException){
        return  ResponseEntity.status(HttpStatus.FORBIDDEN).body(loanErrorException.getMessage());
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleException(Exception exception){
        return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exception.getMessage());
    }
}

package az.izzat.crm.handler;

import java.time.LocalDateTime;
import javax.servlet.http.HttpServletRequest;

import az.izzat.crm.dto.ErrorResponse;
import az.izzat.crm.exception.BillingNegativeException;
import az.izzat.crm.exception.RecordNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(RecordNotFoundException.class)
    protected ResponseEntity<ErrorResponse> handleRecordNotFoundException(RecordNotFoundException ex, HttpStatus status,
                                                                          HttpServletRequest request) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .status(status.value())
                .message(status.getReasonPhrase())
                .url(request.getContextPath())
                .detail(ex.getMessage())
                .date(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BillingNegativeException.class)
    protected ResponseEntity<ErrorResponse> handleBillingException(BillingNegativeException ex, HttpStatus status,
                                                                   HttpServletRequest request) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .status(status.value())
                .message(status.getReasonPhrase())
                .url(request.getContextPath())
                .detail(ex.getMessage())
                .date(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
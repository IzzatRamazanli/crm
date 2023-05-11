package az.izzat.crm.handler;

import java.time.LocalDateTime;
import javax.servlet.http.HttpServletRequest;

import az.izzat.crm.dto.ErrorResponse;
import az.izzat.crm.exception.BillingNegativeException;
import az.izzat.crm.exception.OtpCodeException;
import az.izzat.crm.exception.RecordNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
@RequiredArgsConstructor
public class CustomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(RecordNotFoundException.class)
    protected ResponseEntity<ErrorResponse> handleRecordNotFoundException(RecordNotFoundException ex,
                                                                          HttpServletRequest request) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .status(HttpStatus.NOT_FOUND.value())
                .message(HttpStatus.NOT_FOUND.getReasonPhrase())
                .url(request.getContextPath())
                .detail(ex.getMessage())
                .date(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BillingNegativeException.class)
    protected ResponseEntity<ErrorResponse> handleBillingException(BillingNegativeException ex,
                                                                   HttpServletRequest request) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .message(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .url(request.getContextPath())
                .detail(ex.getMessage())
                .date(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(OtpCodeException.class)
    protected ResponseEntity<ErrorResponse> handleBillingException(OtpCodeException ex,
                                                                   HttpServletRequest request) {
        ErrorResponse errorResponse = ErrorResponse.builder()
                .status(HttpStatus.BAD_REQUEST.value())
                .message(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .url(request.getContextPath())
                .detail(ex.getMessage())
                .date(LocalDateTime.now())
                .build();
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
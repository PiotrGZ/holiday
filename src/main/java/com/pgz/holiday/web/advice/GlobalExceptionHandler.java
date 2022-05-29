package com.pgz.holiday.web.advice;

import com.pgz.holiday.domian.exception.HolidayServiceException;
import com.pgz.holiday.domian.http.response.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private static final String FAILED = "failed";

    @ExceptionHandler(value = HolidayServiceException.class)
    public ResponseEntity<ErrorResponse> handleHolidayServiceException(HolidayServiceException exception, WebRequest webRequest) {
        return new ResponseEntity<>(ErrorResponse.builder().status(FAILED).message(exception.getMessage()).build(),
                                    exception.getHttpStatus());
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ErrorResponse> handleHolidayServiceException(Exception exception, WebRequest webRequest) {
        return new ResponseEntity<>(ErrorResponse.builder().status(FAILED).message(exception.getMessage()).build(),
                                    HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

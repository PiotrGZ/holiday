package com.pgz.holiday.domian.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

public class HolidayServiceException extends RuntimeException {

    @Getter
    private HttpStatus httpStatus;

    public HolidayServiceException(String message, HttpStatus httpStatus) {
        super(message);
        this.httpStatus = httpStatus;
    }
}

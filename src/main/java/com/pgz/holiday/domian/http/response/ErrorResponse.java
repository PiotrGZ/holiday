package com.pgz.holiday.domian.http.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorResponse {

    private String status;
    private String message;
}

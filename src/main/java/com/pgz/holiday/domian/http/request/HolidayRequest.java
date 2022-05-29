package com.pgz.holiday.domian.http.request;

import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class HolidayRequest {

    @NotNull
    private String date;
    @NotNull
    private String firstCode;
    @NotNull
    private String secondCode;
}

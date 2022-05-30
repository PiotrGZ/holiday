package com.pgz.holiday.domian.http.request;

import javax.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class HolidayRequest {

    @NotNull
    private String date;
    @NotNull
    private String firstCode;
    @NotNull
    private String secondCode;
}

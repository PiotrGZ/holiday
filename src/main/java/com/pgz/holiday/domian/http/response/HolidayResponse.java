package com.pgz.holiday.domian.http.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class HolidayResponse {

    private String date;
    private String name1;
    private String name2;
}

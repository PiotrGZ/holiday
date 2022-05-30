package com.pgz.holiday.domian.model;

import java.time.LocalDate;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Holiday {

    private final String name;
    private final LocalDate date;
}

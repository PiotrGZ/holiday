package com.pgz.holiday.domian.model;

import java.time.LocalDate;
import lombok.Data;

@Data
public class Holiday {

    private final String name;
    private final LocalDate date;
}

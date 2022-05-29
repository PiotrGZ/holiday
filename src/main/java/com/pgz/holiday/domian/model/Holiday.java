package com.pgz.holiday.domian.model;

import java.time.LocalDate;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.EqualsAndHashCode.Exclude;

@Data
@Builder
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Holiday {

    @Exclude
    private final String name;
    private final LocalDate date;
}

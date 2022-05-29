package com.pgz.holiday.business.service;

import com.pgz.holiday.domian.http.response.HolidayResponse;
import com.pgz.holiday.domian.model.Holiday;
import java.time.LocalDate;
import java.util.List;

public interface HolidayComparatorService {

    HolidayResponse compare(List<Holiday> firstHolidays, List<Holiday> secondHolidays, LocalDate date);
}

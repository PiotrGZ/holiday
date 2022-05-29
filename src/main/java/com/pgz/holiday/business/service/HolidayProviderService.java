package com.pgz.holiday.business.service;

import com.pgz.holiday.domian.model.Holiday;
import java.time.LocalDate;
import java.util.List;

public interface HolidayProviderService {

    List<Holiday> getHolidays(String countryCode, LocalDate localDate);
}

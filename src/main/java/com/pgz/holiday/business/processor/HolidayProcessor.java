package com.pgz.holiday.business.processor;

import com.pgz.holiday.business.service.HolidayComparatorService;
import com.pgz.holiday.business.service.HolidayProviderService;
import com.pgz.holiday.domian.http.request.HolidayRequest;
import com.pgz.holiday.domian.http.response.HolidayResponse;
import com.pgz.holiday.domian.model.Holiday;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class HolidayProcessor {

    private final HolidayProviderService holidayProviderService;
    private final HolidayComparatorService holidayComparatorService;

    public HolidayResponse process(HolidayRequest holidayRequest) {
        LocalDate date = LocalDate.parse(holidayRequest.getDate());
        List<Holiday> firstHolidays = holidayProviderService.getHolidays(holidayRequest.getFirstCode(), date);
        List<Holiday> secondHolidays = holidayProviderService.getHolidays(holidayRequest.getSecondCode(), date);
        return holidayComparatorService.compare(firstHolidays, secondHolidays, date);
    }
}

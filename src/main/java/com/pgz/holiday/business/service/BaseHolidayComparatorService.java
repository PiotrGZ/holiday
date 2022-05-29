package com.pgz.holiday.business.service;

import com.pgz.holiday.domian.exception.HolidayServiceException;
import com.pgz.holiday.domian.http.response.HolidayResponse;
import com.pgz.holiday.domian.model.Holiday;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class BaseHolidayComparatorService implements HolidayComparatorService {

    @Override
    public HolidayResponse compare(List<Holiday> firstHolidays, List<Holiday> secondHolidays, LocalDate date) {
        List<LocalDate> first = firstHolidays.stream().map(Holiday::getDate).collect(Collectors.toList());
        List<LocalDate> second = secondHolidays.stream().map(Holiday::getDate).collect(Collectors.toList());
        first.retainAll(second);

        LocalDate closestDate = first.stream()
                                     .filter(localDate -> date.isEqual(localDate) || date.isBefore(localDate))
                                     .sorted()
                                     .findFirst()
                                     .orElseThrow(() -> new HolidayServiceException("No common holidays found",
                                                                                    HttpStatus.NOT_FOUND));
        Optional<Holiday> firstHoliday = firstHolidays.stream()
                                                      .filter(holiday -> holiday.getDate().equals(closestDate))
                                                      .findFirst();
        Optional<Holiday> secondHoliday = secondHolidays.stream()
                                                        .filter(holiday -> holiday.getDate().equals(closestDate))
                                                        .findFirst();

        if (firstHoliday.isPresent() && secondHoliday.isPresent()) {
            return HolidayResponse.builder()
                                  .name1(firstHoliday.get().getName())
                                  .name2(secondHoliday.get().getName())
                                  .date(closestDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                                  .build();
        } else {
            throw new HolidayServiceException("Unknown error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

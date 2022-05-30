package com.pgz.holiday.business.service;

import static org.testng.Assert.assertEquals;

import com.pgz.holiday.domian.http.response.HolidayResponse;
import com.pgz.holiday.domian.model.Holiday;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import org.testng.annotations.Test;

public class BaseHolidayComparatorServiceTest {

    @Test
    public void testCompare() {
        //given
        LocalDate date = LocalDate.parse("2021-04-03");
        Holiday firstHoliday = new Holiday("first name", date);
        Holiday secondHoliday = new Holiday("second name", date);
        List<Holiday> first = Arrays.asList(firstHoliday);
        List<Holiday> second = Arrays.asList(secondHoliday);
        BaseHolidayComparatorService comparatorService = new BaseHolidayComparatorService();
        HolidayResponse response = HolidayResponse.builder()
                                                  .date(date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")))
                                                  .name1(firstHoliday.getName())
                                                  .name2(secondHoliday.getName())
                                                  .build();

        //when
        HolidayResponse result = comparatorService.compare(first, second, date);

        //then
        assertEquals(result, response);
    }
}
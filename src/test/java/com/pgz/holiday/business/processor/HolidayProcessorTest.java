package com.pgz.holiday.business.processor;

import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;

import com.pgz.holiday.business.service.HolidayComparatorService;
import com.pgz.holiday.business.service.HolidayProviderService;
import com.pgz.holiday.domian.http.request.HolidayRequest;
import com.pgz.holiday.domian.http.response.HolidayResponse;
import com.pgz.holiday.domian.model.Holiday;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.mockito.Mockito;
import org.testng.annotations.Test;

public class HolidayProcessorTest {

    @Test
    public void testProcess() {
        //given
        HolidayComparatorService comparatorService = Mockito.mock(HolidayComparatorService.class);
        HolidayProviderService providerService = Mockito.mock(HolidayProviderService.class);
        HolidayProcessor processor = new HolidayProcessor(providerService, comparatorService);
        List<Holiday> first = new ArrayList<>();
        List<Holiday> second = new ArrayList<>();
        String firstCode = "PL";
        String secondCode = "DE";
        String date = "2021-04-03";

        HolidayRequest request = HolidayRequest.builder().firstCode(firstCode).secondCode(secondCode).date(date).build();
        HolidayResponse response = HolidayResponse.builder().build();
        //when
        when(providerService.getHolidays(firstCode, LocalDate.parse(date))).thenReturn(first);
        when(providerService.getHolidays(secondCode, LocalDate.parse(date))).thenReturn(second);
        when(comparatorService.compare(first, second, LocalDate.parse(date))).thenReturn(response);

        HolidayResponse result = processor.process(request);
        //then
        assertEquals(result, response);
    }
}
package com.pgz.holiday.web.controller;

import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;

import com.pgz.holiday.business.processor.HolidayProcessor;
import com.pgz.holiday.domian.http.request.HolidayRequest;
import com.pgz.holiday.domian.http.response.HolidayResponse;
import org.mockito.Mockito;
import org.testng.annotations.Test;

public class HolidayControllerTest {

    @Test
    public void testCompare() {
        //given
        HolidayProcessor processor = Mockito.mock(HolidayProcessor.class);
        HolidayController holidayController = new HolidayController(processor);
        HolidayRequest request = HolidayRequest.builder().build();
        HolidayResponse response = HolidayResponse.builder().build();
        //when
        when(processor.process(request)).thenReturn(response);
        HolidayResponse result = holidayController.compare(request);
        //then
        assertEquals(result, response);
    }
}
package com.pgz.holiday.client.holidayapi;

import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;

import com.pgz.holiday.domian.model.Countries;
import com.pgz.holiday.domian.model.Country;
import com.pgz.holiday.domian.model.Holiday;
import com.pgz.holiday.domian.model.Holidays;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import org.mockito.Mockito;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.client.RestTemplate;
import org.testng.annotations.Test;

public class HolidayAPIClientTest {

    @Test
    public void testGetHolidays() {
        //given
        String apiKey = "key";
        String holidaysURI = "holidays";
        String countriesURI = "countries";
        String countryCode = "PL";
        LocalDate date = LocalDate.now();
        Holiday holiday = Mockito.mock(Holiday.class);
        List<Holiday> holidayList = Arrays.asList(holiday);
        Holidays holidays = new Holidays();
        holidays.setHolidays(holidayList);
        RestTemplateBuilder restTemplateBuilder = Mockito.mock(RestTemplateBuilder.class);
        RestTemplate restTemplate = Mockito.mock(RestTemplate.class);
        Country country = new Country();
        country.setCode(countryCode);
        List<Country> countryList = Arrays.asList(country);
        Countries countries = new Countries();
        countries.setCountries(countryList);
        //when
        when(restTemplateBuilder.build()).thenReturn(restTemplate);
        when(restTemplate.getForObject(countriesURI, Countries.class, apiKey)).thenReturn(countries);
        when(restTemplate.getForObject(holidaysURI, Holidays.class, apiKey, countryCode, date.getYear(), true)).thenReturn(
            holidays);
        HolidayAPIClient holidayAPIClient = new HolidayAPIClient(apiKey, holidaysURI, countriesURI, restTemplateBuilder);
        List<Holiday> result = holidayAPIClient.getHolidays(countryCode, date);

        //then
        assertEquals(holidayList, result);
    }
}
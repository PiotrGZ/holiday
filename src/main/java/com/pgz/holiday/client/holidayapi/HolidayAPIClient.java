package com.pgz.holiday.client.holidayapi;

import com.pgz.holiday.business.service.HolidayProviderService;
import com.pgz.holiday.domian.exception.HolidayServiceException;
import com.pgz.holiday.domian.model.Countries;
import com.pgz.holiday.domian.model.Country;
import com.pgz.holiday.domian.model.Holiday;
import com.pgz.holiday.domian.model.Holidays;
import java.time.LocalDate;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Slf4j
@Service
public class HolidayAPIClient implements HolidayProviderService {

    private final String apiKey;
    private final String holidayAPIHolidaysURI;
    private final Countries supportedCountries;
    private final RestTemplate restTemplate;

    public HolidayAPIClient(@NonNull @Value("${external.client.holiday-api.key}") String apiKey,
                            @NonNull @Value("${external.client.holiday-api.url.holidays}") String holidayAPIHolidaysURI,
                            @NonNull @Value("${external.client.holiday-api.url.countries}") String holidayAPICountriesURI,
                            RestTemplateBuilder restTemplateBuilder) {
        this.apiKey = apiKey;
        this.holidayAPIHolidaysURI = holidayAPIHolidaysURI;
        this.restTemplate = restTemplateBuilder.build();
        this.supportedCountries = restTemplate.getForObject(holidayAPICountriesURI, Countries.class, apiKey);
    }

    @Override
    public List<Holiday> getHolidays(String countryCode, LocalDate localDate) {
        supportedCountries.getCountries()
                          .stream()
                          .map(Country::getCode)
                          .filter(countryCode::equals)
                          .findAny()
                          .orElseThrow(() -> new HolidayServiceException("Unsupported country code", HttpStatus.BAD_REQUEST));
        Holidays holidays = restTemplate.getForObject(holidayAPIHolidaysURI,
                                                      Holidays.class,
                                                      apiKey,
                                                      countryCode,
                                                      localDate.getYear(),
                                                      true);
        return holidays.getHolidays();
    }
}

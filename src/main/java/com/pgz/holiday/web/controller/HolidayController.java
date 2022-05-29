package com.pgz.holiday.web.controller;

import com.pgz.holiday.business.processor.HolidayProcessor;
import com.pgz.holiday.domian.http.request.HolidayRequest;
import com.pgz.holiday.domian.http.response.HolidayResponse;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class HolidayController {

    private final HolidayProcessor holidayProcessor;

    @GetMapping("/compare")
    public HolidayResponse compare(@Valid @RequestBody HolidayRequest holidayRequest) {
        return holidayProcessor.process(holidayRequest);
    }
}

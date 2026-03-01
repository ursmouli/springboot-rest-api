package com.app.restapi.dto;

import java.util.HashMap;
import java.util.Map;

public class TimetableDayRowDto {
    private String dayOfWeek;
    private String sectionName;
    // Map period number to the details of that period
    private Map<Integer, PeriodDetailsDto> periods = new HashMap<>();

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }

    public String getSectionName() {
        return sectionName;
    }

    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    public Map<Integer, PeriodDetailsDto> getPeriods() {
        return periods;
    }

    public void setPeriods(Map<Integer, PeriodDetailsDto> periods) {
        this.periods = periods;
    }
}

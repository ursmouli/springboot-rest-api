package com.app.restapi.dto;

public class LocationDto {
    private Long id;
    private String name;
    private String code;
    // unique id of country, state or district
    private Long refId;

    public LocationDto() {}

    public Long getId() {
        return id;
    }

    public LocationDto setId(Long id) {
        this.id = id;
        return this;
    }

    public String getName() {
        return name;
    }

    public LocationDto setName(String name) {
        this.name = name;
        return this;
    }

    public String getCode() {
        return code;
    }

    public LocationDto setCode(String code) {
        this.code = code;
        return this;
    }

    public Long getRefId() {
        return refId;
    }

    public LocationDto setRefId(Long refId) {
        this.refId = refId;
        return this;
    }
}

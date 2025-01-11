package com.app.restapi.model;

import org.springframework.data.domain.Sort;

public enum SortDirection {
    ASC,
    DESC;

    public static Sort.Direction toSpringSortDirection(SortDirection direction) {
        return direction == ASC ? Sort.Direction.ASC : Sort.Direction.DESC;
    }
}

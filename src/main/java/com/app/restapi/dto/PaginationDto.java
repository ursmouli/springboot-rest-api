package com.app.restapi.dto;

import com.app.restapi.model.SortDirection;

import javax.validation.constraints.Min;

public class PaginationDto {

    @Min(0)
    private int page = 0;

    @Min(1)
    private int size = 10;
    private String sortField = "id";
    private SortDirection sortDirection = SortDirection.ASC;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getSortField() {
        return sortField;
    }

    public void setSortField(String sortField) {
        this.sortField = sortField;
    }

    public SortDirection getSortDirection() {
        return sortDirection;
    }

    public void setSortDirection(SortDirection sortDirection) {
        this.sortDirection = sortDirection;
    }
}

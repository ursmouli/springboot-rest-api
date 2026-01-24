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

    private String searchText;

    public int getPage() {
        return page;
    }

    public PaginationDto setPage(int page) {
        this.page = page;
        return this;
    }

    public int getSize() {
        return size;
    }

    public PaginationDto setSize(int size) {
        this.size = size;
        return this;
    }

    public String getSortField() {
        return sortField;
    }

    public PaginationDto setSortField(String sortField) {
        this.sortField = sortField;
        return this;
    }

    public SortDirection getSortDirection() {
        return sortDirection;
    }

    public PaginationDto setSortDirection(SortDirection sortDirection) {
        this.sortDirection = sortDirection;
        return this;
    }

    public String getSearchText() {
        return searchText;
    }

    public PaginationDto setSearchText(String searchText) {
        this.searchText = searchText;
        return this;
    }

    @Override
    public String toString() {
        return "PaginationDto{" +
                "page=" + page +
                ", size=" + size +
                ", sortField='" + sortField + '\'' +
                ", sortDirection=" + sortDirection +
                ", searchText='" + searchText + '\'' +
                '}';
    }
}

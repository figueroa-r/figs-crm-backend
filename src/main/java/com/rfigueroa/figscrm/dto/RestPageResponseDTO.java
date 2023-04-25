package com.rfigueroa.figscrm.dto;


public class RestPageResponseDTO<T> {

    private Iterable<T> data;

    private PageDTO page;

    // constructor

    public RestPageResponseDTO(Iterable<T> data, PageDTO page) {
        this.data = data;
        this.page = page;
    }

    // getters and setters

    public Iterable<T> getData() {
        return data;
    }

    public void setData(Iterable<T> data) {
        this.data = data;
    }

    public PageDTO getPage() {
        return page;
    }

    public void setPage(PageDTO page) {
        this.page = page;
    }
}

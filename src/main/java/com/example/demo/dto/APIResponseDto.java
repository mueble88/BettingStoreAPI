package com.example.demo.dto;

public class APIResponseDto<T> {

    private int recordCount;
    private T response;

    public APIResponseDto(int recordCount, T response) {
        this.recordCount = recordCount;
        this.response = response;
    }

    public int getRecordCount() {
        return recordCount;
    }

    public void setRecordCount(int recordCount) {
        this.recordCount = recordCount;
    }

    public T getResponse() {
        return response;
    }

    public void setResponse(T response) {
        this.response = response;
    }
}

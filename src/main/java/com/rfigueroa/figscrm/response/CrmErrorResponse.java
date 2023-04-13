package com.rfigueroa.figscrm.response;

import java.util.Date;

public class CrmErrorResponse {

    private int status;
    private String message;
    private Date timeStamp;

    // no args constructor
    public CrmErrorResponse() {

    }

    public CrmErrorResponse(int status, String message, Date timeStamp) {
        this.status = status;
        this.message = message;
        this.timeStamp = timeStamp;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(Date timeStamp) {
        this.timeStamp = timeStamp;
    }
}

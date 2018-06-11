package com.app.exceptions;

import java.time.LocalDate;

public class MyException extends RuntimeException {
    private String message;
    private LocalDate dateTime;

    public MyException(String message, LocalDate dateTime) {
        this.message = message;
        this.dateTime = dateTime;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDate getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDate dateTime) {
        this.dateTime = dateTime;
    }
}

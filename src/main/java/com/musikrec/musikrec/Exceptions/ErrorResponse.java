package com.musikrec.musikrec.Exceptions;

import lombok.Data;
import org.springframework.web.context.request.WebRequest;

@Data
public class ErrorResponse {
    private String message;
    private int status;
    private long timestamp;
    private String path;

    public ErrorResponse(String message, int status, String path) {
        this.message = message;
        this.status = status;
        this.timestamp = System.currentTimeMillis();
        this.path = path;
    }

}

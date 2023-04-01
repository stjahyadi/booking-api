package com.hackerrank.sample.interceptor;

import java.sql.Timestamp;
import java.time.Instant;

public class Problem {
    private int status;
    private String message;
    private Instant timestamp;

    public Problem(int status, String message, Instant timestamp) {
        this.status = status;
        this.message = message;
        this.timestamp = timestamp;
    }
}

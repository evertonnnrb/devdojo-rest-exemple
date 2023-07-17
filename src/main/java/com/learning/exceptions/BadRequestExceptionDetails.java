package com.learning.exceptions;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class BadRequestExceptionDetails {
    private String title;
    private int status;
    private String detail;
    private String developerMessage;
    private LocalDateTime timeStamp;
}

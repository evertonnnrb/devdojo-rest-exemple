package com.learning.exceptions;

import lombok.Data;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
@Data
@SuperBuilder
public class ExcpetionDetails {
    protected String title;
    protected int status;
    protected String detail;
    protected String developerMessage;
    protected LocalDateTime timeStamp;
}

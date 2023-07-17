package com.learning.exceptions;

import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class ValidationExceptionDetails extends ExcpetionDetails{

    private final String fields;
    private final String fieldsMessage;

}

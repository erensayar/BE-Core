package com.erensayar.core.error.model;

import java.util.Map;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ValidationErrorResponse {
    private String errorType;
    private String errorCode;
    private Map<String, String> errorMessage;
}

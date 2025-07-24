package com.atipera.model;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

import static java.util.Objects.requireNonNull;

@Data
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class ErrorResponse {

    private final int status;
    private final String message;

    public static ErrorResponse of(int status, String message) {
        requireNonNull(message, "Message cannot be null");
        return new ErrorResponse(status, message);
    }
}

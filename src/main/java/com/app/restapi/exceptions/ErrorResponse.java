package com.app.restapi.exceptions;

import java.time.LocalDateTime;

public record ErrorResponse(String message, LocalDateTime timestamp) {
}

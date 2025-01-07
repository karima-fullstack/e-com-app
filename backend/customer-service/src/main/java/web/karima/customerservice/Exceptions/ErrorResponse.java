package web.karima.customerservice.Exceptions;

import java.util.Map;

public record ErrorResponse(
        Map<String, String> errors
) {
}

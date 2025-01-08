package web.karima.productservice.Exceptions;

import java.util.Map;

public record ErrorResponse(
        Map<String, String> errors
) {
}

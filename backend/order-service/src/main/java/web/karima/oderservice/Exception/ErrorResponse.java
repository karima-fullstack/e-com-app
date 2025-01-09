package web.karima.oderservice.Exception;

import java.util.Map;

public record ErrorResponse(
        Map<String, String> errors
) {
}

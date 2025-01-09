package web.karima.oderservice.Records;

import java.util.SplittableRandom;

public record CustomerResponse(
        String id,
        String firstname,
        String lastname,
        String email
) {
}

package web.karima.oderservice.Records;

import com.fasterxml.jackson.annotation.JsonInclude;
import web.karima.oderservice.Entities.PaymentMethod;

import java.math.BigDecimal;

@JsonInclude(JsonInclude.Include.NON_EMPTY)
public record OrderResponse(
        Integer id,
        String reference,
        BigDecimal amount,
        PaymentMethod paymentMethod,
        String customerId
) {

}
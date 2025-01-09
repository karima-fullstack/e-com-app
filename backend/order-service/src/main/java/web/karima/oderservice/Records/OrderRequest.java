package web.karima.oderservice.Records;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import web.karima.oderservice.Entities.PaymentMethod;

import java.math.BigDecimal;
import java.util.List;

public record OrderRequest(
    Integer id,
    @NotNull(message = "Order reference should not be empty")
    String reference,
    @NotNull(message = "Order amount should not be empty") @Positive(message = "amount should be positive")
    BigDecimal amount,
    @NotNull(message = "Order payment method should not be empty")
    PaymentMethod paymentMethod,
    @NotNull(message = "Order reference should not be empty")
    String customerId,
    @NotEmpty(message = "Order reference should not be empty")
    List<ProductPurchaseRequest> products
) {
}

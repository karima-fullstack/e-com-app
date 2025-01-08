package web.karima.productservice.Records;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record ProductPurchaseRequest(
        @NotNull(message = "Product id is mandatory")
        Integer productId,
        @Positive(message = "Quantity of product is mandatory")
        double quantity
) {
}

package web.karima.productservice.Records;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record ProductRequest(
        Integer id,
        @NotNull(message = "Name of product should not be empty")
        String name,
        @NotNull(message = "Description of product should not be empty")
        String description,
        @NotNull(message = "Quantity of product should not be empty")
        double availableQuantity,
        @NotNull(message = "name of product should not be empty")
        BigDecimal price,
        @NotNull(message = "Category id of product should not be empty")
        Integer categoryId
) {
}

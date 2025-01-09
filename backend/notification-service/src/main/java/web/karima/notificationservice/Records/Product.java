package web.karima.notificationservice.Records;

import java.math.BigDecimal;

public record Product(
         Integer id,
         String name,
         String description,
         Double availableQuantity,
         BigDecimal price
) {
}

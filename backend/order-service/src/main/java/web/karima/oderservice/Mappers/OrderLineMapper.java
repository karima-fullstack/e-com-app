package web.karima.oderservice.Mappers;

import org.springframework.stereotype.Service;
import web.karima.oderservice.Entities.Order;
import web.karima.oderservice.Entities.OrderLine;
import web.karima.oderservice.Records.OrderLineRequest;
import web.karima.oderservice.Records.OrderLineResponse;

@Service
public class OrderLineMapper {
    public OrderLine toOrderLine(OrderLineRequest request) {
        return OrderLine.builder()
                .id(request.orderId())
                .productId(request.productId())
                .order(
                        Order.builder()
                                .id(request.orderId())
                                .build()
                )
                .quantity(request.quantity())
                .build();
    }

    public OrderLineResponse toOrderLineResponse(OrderLine orderLine) {
        return new OrderLineResponse(
                orderLine.getId(),
                orderLine.getQuantity()
        );
    }
}

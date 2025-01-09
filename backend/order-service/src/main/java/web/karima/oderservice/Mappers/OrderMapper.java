package web.karima.oderservice.Mappers;

import org.springframework.stereotype.Service;
import web.karima.oderservice.Entities.Order;
import web.karima.oderservice.Records.OrderRequest;
import web.karima.oderservice.Records.OrderResponse;

@Service
public class OrderMapper {
    public Order toOrder(OrderRequest request){
        return Order.builder()
                .id(request.id())
                .reference(request.reference())
                .totalAmount(request.amount())
                .paymentMethod(request.paymentMethod())
                .customerId(request.customerId())
                .build();
    }
    public OrderResponse fromOrder(Order order) {
        return new OrderResponse(
                order.getId(),
                order.getReference(),
                order.getTotalAmount(),
                order.getPaymentMethod(),
                order.getCustomerId()
        );
    }
}

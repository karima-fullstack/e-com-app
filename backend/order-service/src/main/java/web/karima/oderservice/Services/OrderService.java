package web.karima.oderservice.Services;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import web.karima.oderservice.Clients.CustomerClient;
import web.karima.oderservice.Clients.PaymentClient;
import web.karima.oderservice.Clients.ProductClient;
import web.karima.oderservice.Kafka.OrderProducer;
import web.karima.oderservice.Mappers.OrderMapper;
import web.karima.oderservice.Records.*;
import web.karima.oderservice.Repositories.OrderRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final CustomerClient customerClient;
    private final ProductClient productClient;
    private final OrderProducer orderProducer;
    private final OrderLineService orderLineService;
    private final OrderMapper orderMapper;
    private final PaymentClient paymentClient;

    public Integer createOrder(OrderRequest request) {
       // check customer
        var customer = this.customerClient.findCustomerById(request.customerId())
                .orElseThrow(() -> new RuntimeException("Cannot create order:: No customer exists with the provided ID"));

       // persist the products
        var purchasedProducts = productClient.purchaseProducts(request.products());

        //persist order
        var order = orderRepository.save(orderMapper.toOrder(request));

        //persist orderLines
        for (ProductPurchaseRequest purchaseRequest : request.products()) {
            orderLineService.saveOrderLine(
                    new OrderLineRequest(
                            null,
                            order.getId(),
                            purchaseRequest.productId(),
                            purchaseRequest.quantity()
                    )
            );
        }
       // start payment process
        var paymentRequest = new PaymentRequest(
                request.amount(),
                request.paymentMethod(),
                order.getId(),
                order.getReference(),
                customer
        );
        paymentClient.requestOrderPayment(paymentRequest);

       //send the order confirmation --> using notification-service
        orderProducer.sendOrderConfirmation(
                new OrderConfirmation(
                        request.reference(),
                        request.amount(),
                        request.paymentMethod(),
                        customer,
                        purchasedProducts
                )
        );

        return order.getId();

    }

    public List<OrderResponse> findAllOrders() {
        return orderRepository.findAll()
                .stream()
                .map(orderMapper::fromOrder)
                .collect(Collectors.toList());
    }

    public OrderResponse findById(Integer id) {
        return orderRepository.findById(id)
                .map(orderMapper::fromOrder)
                .orElseThrow(() -> new EntityNotFoundException(String.format("No order found with the provided ID: %d", id)));
    }
}

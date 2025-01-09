package web.karima.paymentservice.Mappers;

import org.springframework.stereotype.Service;
import web.karima.paymentservice.Entities.Payment;
import web.karima.paymentservice.Records.PaymentRequest;

@Service
public class PaymentMapper {

    public Payment toPayment(PaymentRequest request) {
        if (request == null) {
            return null;
        }
        return Payment.builder()
                .id(request.id())
                .paymentMethod(request.paymentMethod())
                .amount(request.amount())
                .orderId(request.orderId())
                .build();
    }
}
package web.karima.paymentservice.Services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import web.karima.paymentservice.Kafka.NotificationProducer;
import web.karima.paymentservice.Mappers.PaymentMapper;
import web.karima.paymentservice.Records.PaymentNotificationRequest;
import web.karima.paymentservice.Records.PaymentRequest;
import web.karima.paymentservice.Repositories.PaymentRepository;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;
    private final NotificationProducer notificationProducer;

    public Integer createPayment(PaymentRequest request) {
        var payment = paymentRepository.save(paymentMapper.toPayment(request));

        this.notificationProducer.sendNotification(
                new PaymentNotificationRequest(
                        request.orderReference(),
                        request.amount(),
                        request.paymentMethod(),
                        request.customer().firstname(),
                        request.customer().lastname(),
                        request.customer().email()
                )
        );
        return payment.getId();
    }
}

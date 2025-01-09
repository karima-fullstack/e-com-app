package web.karima.paymentservice.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import web.karima.paymentservice.Entities.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Integer> {
}

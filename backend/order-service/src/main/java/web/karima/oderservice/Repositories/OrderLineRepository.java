package web.karima.oderservice.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import web.karima.oderservice.Entities.OrderLine;

import java.util.Collection;
import java.util.List;

public interface OrderLineRepository extends JpaRepository<OrderLine, Integer> {
    List<OrderLine> findAllByOrderId(java.lang.Integer orderId);
}

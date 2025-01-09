package web.karima.oderservice.Repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import web.karima.oderservice.Entities.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}

package web.karima.oderservice.Clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import web.karima.oderservice.Records.CustomerResponse;

import java.util.Optional;

@FeignClient(
 name = "customer-service",
 url = "${application.config.customer-url}"
)
public interface CustomerClient {
    @GetMapping("/{id}")
    Optional<CustomerResponse> findCustomerById(@PathVariable("id") String customerId);

}

package web.karima.oderservice.Clients;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import web.karima.oderservice.Records.ProductPurchaseRequest;
import web.karima.oderservice.Records.ProductPurchaseResponse;

import java.util.List;

@FeignClient(name = "product-service", url = "${application.config.product-url}")
public interface ProductClient {

    @PostMapping("/purchase")
    List<ProductPurchaseResponse> purchaseProducts(@RequestBody List<ProductPurchaseRequest> requestBody);
}
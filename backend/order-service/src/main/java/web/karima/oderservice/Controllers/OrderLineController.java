package web.karima.oderservice.Controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import web.karima.oderservice.Records.OrderLineResponse;
import web.karima.oderservice.Services.OrderLineService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/order-lines")
@RequiredArgsConstructor
public class OrderLineController {

    private final OrderLineService service;

    @GetMapping("/order/{id}")
    public ResponseEntity<List<OrderLineResponse>> findByOrderId(@PathVariable("id") Integer orderId) {

        return ResponseEntity.ok(service.findAllByOrderId(orderId));

    }

}
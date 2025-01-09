package web.karima.oderservice.Services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import web.karima.oderservice.Mappers.OrderLineMapper;
import web.karima.oderservice.Records.OrderLineRequest;
import web.karima.oderservice.Records.OrderLineResponse;
import web.karima.oderservice.Repositories.OrderLineRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderLineService {

    private final OrderLineRepository orderLineRepository;
    private final OrderLineMapper orderLineMapper;

    public Integer saveOrderLine(OrderLineRequest request) {
        var order = orderLineMapper.toOrderLine(request);
        return orderLineRepository.save(order).getId();
    }
    public List<OrderLineResponse> findAllByOrderId(Integer orderId) {
        return orderLineRepository.findAllByOrderId(orderId)
                .stream()
                .map(orderLineMapper::toOrderLineResponse)
                .collect(Collectors.toList());
    }
}

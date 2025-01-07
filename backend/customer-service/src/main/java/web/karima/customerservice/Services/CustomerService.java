package web.karima.customerservice.Services;

import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import web.karima.customerservice.Entities.Customer;
import web.karima.customerservice.Exceptions.CustomerNotFoundException;
import web.karima.customerservice.Mappers.CustomerMapper;
import web.karima.customerservice.Records.CustomerRequest;
import web.karima.customerservice.Records.CustomerResponse;
import web.karima.customerservice.Repositories.CustomerRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    public String createCustomer(CustomerRequest request){
        Customer customer = customerRepository.save(customerMapper.toCustomer(request));
        return customer.getId();
    }

    public void updateCustomer(CustomerRequest request){
        var customer = customerRepository.findById(request.id())
                .orElseThrow(()->
                        new CustomerNotFoundException(String.format(
                          "Can't find customer with ID: %s", request.id()
                        ))
                );
        if (StringUtils.isNotBlank(request.firstname())) customer.setFirstname(request.firstname());
        if (StringUtils.isNotBlank(request.lastname()))customer.setLastname(request.lastname());
        if (StringUtils.isNotBlank(request.email()))customer.setEmail(request.email());
        if (request.address() != null)customer.setAddress(request.address());

        customerRepository.save(customer);
    }

    public List<CustomerResponse> findAllCustomers() {
        return customerRepository.findAll()
                .stream()
                .map(customerMapper::fromCustomer)
                .collect(Collectors.toList());
    }

    public CustomerResponse findById(String id) {
        return customerRepository.findById(id)
                .map(customerMapper::fromCustomer)
                .orElseThrow(() -> new CustomerNotFoundException(String.format("Can't find customer with ID: %s", id)));
    }

    public void deleteCustomer(String id) {
        customerRepository.deleteById(id);
    }


}

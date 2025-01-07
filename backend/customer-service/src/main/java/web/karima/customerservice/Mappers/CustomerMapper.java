package web.karima.customerservice.Mappers;

import org.springframework.stereotype.Service;
import web.karima.customerservice.Entities.Customer;
import web.karima.customerservice.Records.CustomerRequest;
import web.karima.customerservice.Records.CustomerResponse;

@Service
public class CustomerMapper {

    public Customer toCustomer(CustomerRequest request){
        if(request == null){
            return null;
        }
        return Customer.builder()
                .id(request.id())
                .firstname(request.firstname())
                .lastname(request.lastname())
                .email(request.email())
                .address(request.address())
                .build();
    }

    public CustomerResponse fromCustomer(Customer customer){
        if (customer == null){
            return null;
        }
        return new CustomerResponse(
                customer.getId(),
                customer.getFirstname(),
                customer.getLastname(),
                customer.getEmail(),
                customer.getAddress()
        );
    }
}

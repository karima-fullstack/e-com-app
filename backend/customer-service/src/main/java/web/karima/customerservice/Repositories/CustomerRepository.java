package web.karima.customerservice.Repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import web.karima.customerservice.Entities.Customer;

@Repository
public interface CustomerRepository extends MongoRepository<Customer, String> {
}

package web.karima.customerservice.Records;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import web.karima.customerservice.Entities.Address;

public record CustomerRequest(
        String id,
        @NotNull(message = "Firstname is required")
        String firstname,
        @NotNull(message = "Lastname is required")
        String lastname,
        @NotNull(message = "Email is required")
        @Email(message = "Email format is invalid")
        String email,
        @NotNull(message = "Address is required")
        Address address
) {
}

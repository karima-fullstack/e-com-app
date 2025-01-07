package web.karima.customerservice.Entities;

import lombok.*;
import org.springframework.validation.annotation.Validated;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Validated
public class Address {
     private String street;
     private String houseNumber;
     private String zipCode;
}

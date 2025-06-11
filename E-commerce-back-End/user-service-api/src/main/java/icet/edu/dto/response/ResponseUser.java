package icet.edu.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ResponseUser {
    private String userName;
    private String firstName;
    private String lastName;
    private Boolean activeStatus;
    private ResponseUserAvater avater;// if an avater is set, it will be returned in the response
    private ResponseShippingAddress shippingAddress; // if value exist ? value : null
    private ResponseBillingAddress billingAddress;  // if value exist ? value : null
}

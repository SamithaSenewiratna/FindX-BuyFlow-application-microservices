package icet.edu.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerOrderRequest {

    private Date orderDate;
    private Double totalAmount;
    private String userId;
    private ArrayList<OrderDetailRequest> orderDetails;

}

package icet.edu.dto.response;

import lombok.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CustomerOrderResponse {

    private String orderId;
    private Date orderDate;
    private BigDecimal totalAmount;
    private String userId;
    private String remark;
    private String status;
    private List<OrderDetailResponse> orderDetails;

}

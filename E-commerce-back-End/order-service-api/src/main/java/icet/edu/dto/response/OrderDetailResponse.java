package icet.edu.dto.response;

import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDetailResponse {

    private String detailId;
    private String productId;
    private int qty;
    private BigDecimal unitPrice;
    private BigDecimal  discount;

}

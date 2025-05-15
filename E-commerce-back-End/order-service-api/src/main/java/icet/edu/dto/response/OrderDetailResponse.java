package icet.edu.dto.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDetailResponse {

    private String detailId;
    private String productId;
    private int qty;
    private double unitPrice;
    private double discount;

}

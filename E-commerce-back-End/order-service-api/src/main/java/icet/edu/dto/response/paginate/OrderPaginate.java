package icet.edu.dto.response.paginate;

import icet.edu.dto.response.CustomerOrderResponse;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderPaginate {
    private long count;
    private List<CustomerOrderResponse> dataList;
}

package icet.edu.service;

import icet.edu.dto.request.CustomerOrderRequest;
import icet.edu.dto.response.CustomerOrderResponse;
import icet.edu.dto.response.paginate.OrderPaginate;

public interface CustomerOrderService {
    public void createOrder(CustomerOrderRequest request);
    public CustomerOrderResponse findOrderById(String orderId);
    public void updateOrder(CustomerOrderRequest request,String orderId);
    public void deleteById(String orderId);
    public OrderPaginate searchAll(String searchText ,int page, int size);
    public void manageRemark(String remark,String orderId);
    public void manageStatus(String status,String orderId);

}

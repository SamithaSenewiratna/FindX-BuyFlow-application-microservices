package icet.edu.service;

import icet.edu.dto.request.CustomerOrderRequest;

public interface CustomerOrderService {
    public void createOrder(CustomerOrderRequest request);
}

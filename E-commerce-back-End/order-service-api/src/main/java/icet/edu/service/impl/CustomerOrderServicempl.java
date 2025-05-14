package icet.edu.service.impl;

import icet.edu.dto.request.CustomerOrderRequest;
import icet.edu.dto.request.OrderDetailRequest;
import icet.edu.entity.OrderDetailEntity;
import icet.edu.entity.OrderEntity;
import icet.edu.entity.OrderStatusEntity;
import icet.edu.repository.CustomerOrderRepository;
import icet.edu.repository.CustomerOrderStatusRepository;
import icet.edu.service.CustomerOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerOrderServicempl implements CustomerOrderService {

    private final CustomerOrderRepository customerOrderRepository;
    private final CustomerOrderStatusRepository customerOrderStatusRepository;

    @Override
    public void createOrder(CustomerOrderRequest request) {

        OrderStatusEntity orderStatus =  customerOrderStatusRepository.findByStatus("PENDING").orElseThrow(()->new RuntimeException("Order Status Not Found......."));

        OrderEntity customerOrder = new OrderEntity();
         customerOrder.setOrderId(UUID.randomUUID().toString());
         customerOrder.setOrderDate(request.getOrderDate());
         customerOrder.setRemark("");
         customerOrder.setTotalAmount(request.getTotalAmount());
         customerOrder.setOrderId(request.getUserId());

         customerOrder.setOrderStatus(orderStatus);

         customerOrder.setProducts(
                 request.getOrderDetails().stream().map(e->createOrderDetail(e,customerOrder)).collect(Collectors.toSet())
         );
        customerOrderRepository.save(customerOrder);
    }


    private OrderDetailEntity createOrderDetail(OrderDetailRequest orderDetailRequest ,OrderEntity order){
        if (orderDetailRequest == null) {
            return null;
        }
        return OrderDetailEntity.builder()
                .detailId(UUID.randomUUID().toString())
                .unitPrice(orderDetailRequest.getUnitPrice())
                .discount(orderDetailRequest.getDiscount())
                .qty(orderDetailRequest.getQty())
                .customerOrder(order)
                .build();
    }

}

package icet.edu.service.impl;

import icet.edu.dto.request.CustomerOrderRequest;
import icet.edu.dto.request.OrderDetailRequest;
import icet.edu.dto.response.CustomerOrderResponse;
import icet.edu.dto.response.OrderDetailResponse;
import icet.edu.dto.response.paginate.OrderPaginate;
import icet.edu.entity.OrderDetailEntity;
import icet.edu.entity.OrderEntity;
import icet.edu.entity.OrderStatusEntity;
import icet.edu.exception.EntryNotFoundException;
import icet.edu.repository.CustomerOrderRepository;
import icet.edu.repository.CustomerOrderStatusRepository;
import icet.edu.service.CustomerOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
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
         customerOrder.setTotalAmount(BigDecimal.valueOf(request.getTotalAmount()));
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
                .unitPrice(BigDecimal.valueOf(orderDetailRequest.getUnitPrice()))
                .discount(BigDecimal.valueOf(orderDetailRequest.getDiscount()))
                .qty(orderDetailRequest.getQty())
                .customerOrder(order)
                .build();
    }


    @Override
    public CustomerOrderResponse findOrderById(String orderId) {
     OrderEntity customerOrder  =  customerOrderRepository.findById(orderId).orElseThrow(()->new EntryNotFoundException(String.format("order status not found..",orderId)));
       return tocustomerOrderResponse(customerOrder);

    }


    private CustomerOrderResponse tocustomerOrderResponse(OrderEntity customerOrder){
        if (customerOrder == null) {
              return null;
        }
        return   CustomerOrderResponse.builder()
                    .orderId(customerOrder.getOrderId())
                    .orderDate(customerOrder.getOrderDate())
                    .userId(customerOrder.getUserId())
                    .totalAmount(customerOrder.getTotalAmount())
                    .orderDetails(
                            customerOrder.getProducts().stream().map(this::toOrderDetailResponse).collect(Collectors.toList())
                    )
                    .remark(customerOrder.getRemark())
                    .status(customerOrder.getOrderStatus().getStatus())
                    .build();
    }

    private OrderDetailResponse toOrderDetailResponse (OrderDetailEntity orderDetail){
        if (orderDetail == null) {
            return null;

        }
        return OrderDetailResponse.builder()
                .productId(orderDetail.getProductId())
                .detailId(orderDetail.getDetailId())
                .discount(orderDetail.getDiscount())
                .qty(orderDetail.getQty())
                .unitPrice(orderDetail.getUnitPrice())
                .build();
    }

    @Override
    public void deleteById(String orderId) {
        OrderEntity customerOrder  =  customerOrderRepository.findById(orderId).orElseThrow(()->new EntryNotFoundException(String.format("order not found..",orderId)));
        customerOrderRepository.delete(customerOrder);
    }

    @Override
    public OrderPaginate searchAll(String searchText ,int page, int size) {
        return OrderPaginate.builder()
                .count(
                        customerOrderRepository.searchCount(searchText)
                )
                .dataList(
                        customerOrderRepository.searchAll(searchText, PageRequest.of(page,size))
                                .stream().map(this::tocustomerOrderResponse).collect(Collectors.toList())
                )
                .build();
    }


    @Override
    public void updateOrder(CustomerOrderRequest request, String orderId) {

        OrderEntity customerOrder  =  customerOrderRepository.findById(orderId).orElseThrow(()->new EntryNotFoundException(String.format("order not found..",orderId)));
        customerOrder.setOrderDate(request.getOrderDate());
        customerOrder.setTotalAmount(BigDecimal.valueOf(request.getTotalAmount()));

        customerOrderRepository.save(customerOrder);
    }


    @Override
    public void manageRemark(String remark, String orderId) {

        OrderEntity customerOrder  =  customerOrderRepository.findById(orderId).orElseThrow(()->new EntryNotFoundException(String.format("order not found..",orderId)));
        customerOrder.setRemark(remark);
        customerOrderRepository.save(customerOrder);

    }

    @Override
    public void manageStatus(String status, String orderId) {

        OrderEntity customerOrder  =  customerOrderRepository.findById(orderId).orElseThrow(()->new EntryNotFoundException(String.format("order not found..",orderId)));
        OrderStatusEntity orderStatus =  customerOrderStatusRepository.findByStatus(status).orElseThrow(()->new EntryNotFoundException ("Order Status Not Found......."));
        customerOrder.setOrderStatus(orderStatus);
        customerOrderRepository.save(customerOrder);
    }





}

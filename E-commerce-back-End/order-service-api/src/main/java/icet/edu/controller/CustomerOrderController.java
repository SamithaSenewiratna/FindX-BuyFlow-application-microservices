package icet.edu.controller;

import icet.edu.dto.request.CustomerOrderRequest;
import icet.edu.service.CustomerOrderService;
import icet.edu.util.StandardResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/customer-orders")
@RequiredArgsConstructor
public class CustomerOrderController {
   private final CustomerOrderService customerOrderService;

   @PostMapping("/busness/create-order")
   public ResponseEntity<StandardResponse> createOrder(@RequestBody CustomerOrderRequest request){
       customerOrderService.createOrder(request);
      return new ResponseEntity<>(
              new StandardResponse(
                     201,"order created...",null
              ), HttpStatus.CREATED
      );
   }

    @GetMapping("/visitors/find-by-id/{id}")
    public ResponseEntity<StandardResponse> findOrderById(@PathVariable  String id){

        return new ResponseEntity<>(
                new StandardResponse(
                        200,"order details",  customerOrderService.findOrderById(id)
                ), HttpStatus.OK
        );
    }

    @PutMapping("/busness/update-order/{id}")
    public ResponseEntity<StandardResponse> updateOrder(@RequestBody CustomerOrderRequest request,@PathVariable String id){
        customerOrderService.updateOrder(request,id);
        return new ResponseEntity<>(
                new StandardResponse(
                        200,"order updated...",null
                ), HttpStatus.OK
        );
    }

    @PutMapping("/busness/update-remark/{id}")
    public ResponseEntity<StandardResponse> manageRemark(@RequestParam String remark,@PathVariable String id){
        customerOrderService.manageRemark(remark,id);
        return new ResponseEntity<>(
                new StandardResponse(
                        200,"remark updated...",null
                ), HttpStatus.OK
        );
    }

    @PutMapping("/busness/update-status/{id}")
    public ResponseEntity<StandardResponse> manageStatus(@RequestParam String status,@PathVariable String id){
        customerOrderService.manageStatus(status,id);
        return new ResponseEntity<>(
                new StandardResponse(
                        200,"status updated...",null
                ), HttpStatus.OK
        );
    }

    @DeleteMapping("/busness/deleteById/{id}")
    public ResponseEntity<StandardResponse> deleteById(@PathVariable String id){
        customerOrderService.deleteById(id);
        return new ResponseEntity<>(
                new StandardResponse(
                        204,"order deleted...",null
                ), HttpStatus.NO_CONTENT
        );
    }

    @DeleteMapping("/visitors/search-All")
    public ResponseEntity<StandardResponse> searchAll(@RequestParam String searchText,@RequestParam int page,@RequestParam int size ){

        return new ResponseEntity<>(
                new StandardResponse(
                        200,"order list",customerOrderService.searchAll(searchText,page,size)
                ), HttpStatus.OK
        );
    }
}

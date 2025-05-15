package icet.edu.controller;

import icet.edu.dto.request.CustomerOrderRequest;
import icet.edu.service.CustomerOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/customer-orders")
@RequiredArgsConstructor
public class CustomerOrderController {
   private final CustomerOrderService customerOrderService;

   @PostMapping("/busness/create-order")
   public String createOrder(@RequestBody CustomerOrderRequest request){
       customerOrderService.createOrder(request);
       return "";
   }

    @GetMapping("/visitors/find-by-id/{id}")
    public String findOrderById(@PathVariable  String id){
        customerOrderService.findOrderById(id);
        return "";
    }

    @PutMapping("/busness/update-order/{id}")
    public String updateOrder(@RequestBody CustomerOrderRequest request,@PathVariable String id){
        customerOrderService.updateOrder(request,id);
        return "";
    }

    @PutMapping("/busness/update-remark/{id}")
    public String manageRemark(@RequestParam String remark,@PathVariable String id){
        customerOrderService.manageRemark(remark,id);
        return "";
    }

    @PutMapping("/busness/update-status/{id}")
    public String manageStatus(@RequestParam String status,@PathVariable String id){
        customerOrderService.manageStatus(status,id);
        return "";
    }

    @DeleteMapping("/busness/deleteById/{id}")
    public String deleteById(@PathVariable String id){
        customerOrderService.deleteById(id);
        return "";
    }

    @DeleteMapping("/visitors/search-All")
    public String searchAll(@RequestParam String searchText,@RequestParam int page,@RequestParam int size ){
        customerOrderService.searchAll(searchText,page,size);
        return "";
    }
}

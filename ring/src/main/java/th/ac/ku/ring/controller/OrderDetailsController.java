package th.ac.ku.ring.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import th.ac.ku.ring.model.OrderDetails;
import th.ac.ku.ring.model.RingsOrder;
import th.ac.ku.ring.service.OrderDetailsService;
import th.ac.ku.ring.service.OrderService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/order-details")
public class OrderDetailsController {
    @Autowired
    private OrderDetailsService service;

    @GetMapping
    public List<OrderDetails> getAll(){
        return service.getAll();
    }

    @PostMapping
    public OrderDetails create(@RequestBody OrderDetails order){
        return  service.create(order);
    }

    @GetMapping("/{id}")
    public OrderDetails getOrder(@PathVariable UUID id){
        return service.getOrder(id);
    }

    @PutMapping("/{id}")
    public OrderDetails update(@PathVariable UUID id ,@RequestBody OrderDetails order){
        return service.update(id, order);
    }

    @DeleteMapping("/{id}")
    public OrderDetails delete(@PathVariable UUID id){
        return service.delete(id);
    }
}

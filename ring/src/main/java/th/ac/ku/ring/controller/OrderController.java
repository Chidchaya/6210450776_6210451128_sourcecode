package th.ac.ku.ring.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import th.ac.ku.ring.model.RingsOrder;
import th.ac.ku.ring.service.OrderService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService service;

    @GetMapping
    public List<RingsOrder> getAll(){
        return service.getAll();
    }

    @PostMapping
    public RingsOrder create(@RequestBody RingsOrder order){
        return  service.create(order);
    }

    @GetMapping("/{id}")
    public RingsOrder getOrder(@PathVariable UUID id){
        return service.getOrder(id);
    }

    @PutMapping("/{id}")
    public RingsOrder update(@PathVariable UUID id ,@RequestBody RingsOrder order){
        return service.update(id, order);
    }

    @DeleteMapping("/{id}")
    public RingsOrder delete(@PathVariable UUID id){
        return service.delete(id);
    }


}

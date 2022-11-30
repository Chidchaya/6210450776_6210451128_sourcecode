package th.ac.ku.ring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import th.ac.ku.ring.model.OrderDetails;
import th.ac.ku.ring.model.RingsOrder;
import th.ac.ku.ring.repository.OrderDetailsRepository;

import java.util.List;
import java.util.UUID;

@Service
public class OrderDetailsService {

    @Autowired
    private OrderDetailsRepository repository;

    public List<OrderDetails> getAll(){
        return repository.findAll();
    }

    public OrderDetails create (OrderDetails order){
        repository.save(order);
        return order;
    }

    public OrderDetails getOrder(UUID id) {
        return repository.findById(id).get();
    }

    public OrderDetails update(UUID id, OrderDetails requestBody){

        OrderDetails record = repository.findById(id).get();
        //record.setUsername(requestBody.getUsername());
        record.setRingId(requestBody.getRingId());
        record.setAmount(requestBody.getAmount());
        record.setPrice(requestBody.getPrice());
        record.setOrderId(requestBody.getOrderId());

        repository.save(record);
        return record;
    }

    public OrderDetails delete(UUID id){
        OrderDetails record = repository.findById(id).get();
        repository.deleteById(id);
        return record;
    }


}

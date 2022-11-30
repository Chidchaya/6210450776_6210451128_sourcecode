package th.ac.ku.ring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import th.ac.ku.ring.model.RingsOrder;
import th.ac.ku.ring.repository.OrderRepository;

import java.util.List;
import java.util.UUID;

@Service
public class OrderService {

    @Autowired
    private OrderRepository repository;

    public List<RingsOrder> getAll(){
        return repository.findAll();
    }

    public RingsOrder create (RingsOrder order){
        repository.save(order);
        return order;
    }

    public RingsOrder getOrder(UUID id) {
        return repository.findById(id).get();
    }

    public RingsOrder update(UUID id, RingsOrder requestBody){

        RingsOrder record = repository.findById(id).get();
        record.setDate(requestBody.getDate());
        record.setName(requestBody.getName());
        record.setMobile(requestBody.getMobile());
        record.setAddress(requestBody.getAddress());
        record.setRings(requestBody.getRings());
        //record.setAmount(requestBody.getAmount());
        record.setUsername(requestBody.getUsername());
        record.setPayment(requestBody.getPayment());
        //record.setPrice(requestBody.getPrice());
        record.setStatus(requestBody.getStatus());

        repository.save(record);
        return record;
    }

    public RingsOrder delete(UUID id){
        RingsOrder record = repository.findById(id).get();
        repository.deleteById(id);
        return record;
    }



}

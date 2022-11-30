package th.ac.ku.ringsRunner.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import th.ac.ku.ringsRunner.model.OrderDetails;

import java.util.UUID;

@Service
public class OrderDetailsService {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private CartService cartService;
    @Autowired
    private UserService userService;

    private RingsService ringsService;

    public OrderDetails getOneById(UUID id) {
        String url = "http://localhost:8090/order-details/" + id;
        ResponseEntity<OrderDetails> response =
                restTemplate.getForEntity(url, OrderDetails.class);
        OrderDetails rings = response.getBody();
        return rings;
    }


    public void update(OrderDetails rings) {
        String url = "http://localhost:8090/order-details/" + rings.getDetailsId();

        restTemplate.put(url, rings, OrderDetails.class);
    }


    public void delete(OrderDetails rings) {
        String url = "http://localhost:8090/order-details/" + rings.getDetailsId();
        restTemplate.delete(url, rings, OrderDetails.class);
    }
}

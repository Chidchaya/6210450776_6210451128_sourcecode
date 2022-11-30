package th.ac.ku.ringsRunner.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import th.ac.ku.ringsRunner.model.Order;
import th.ac.ku.ringsRunner.model.Cart;
import th.ac.ku.ringsRunner.model.OrderDetails;
import th.ac.ku.ringsRunner.model.Rings;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static java.lang.Integer.parseInt;

@Service
public class OrderService {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private CartService cartService;
    @Autowired
    private UserService userService;

    private List<Order> cart = new ArrayList<>();

    public List<Order> getAll(){
        String url = "http://localhost:8090/order";
        ResponseEntity<Order[]> response = restTemplate.getForEntity(url, Order[].class);
        Order[] rings = response.getBody();
        return Arrays.asList(rings);
    }

    public void addOrder(Order ring, OrderDetails ring2){
        String url = "http://localhost:8090/order";
        String url2 = "http://localhost:8090/order-details";
        Order ringsOrder = new Order();
        OrderDetails orderDetails = new OrderDetails();
        orderDetails = ring2;
        ringsOrder = ring;
        ringsOrder.setUsername(userService.getUser().getUsername()); //FK
        ringsOrder.setRings(cartService.getCart().toString());
        ringsOrder.setStatus("Underpayment");
        //ringsOrder.setPrice(cartService.priceCalculate());
        //ringsOrder.setAmount(cartService.getCart().size());
        //orderDetails.setUsername(userService.getUser().getUsername()); //FK
        orderDetails.setRingId(cartService.getCart().toString());
        orderDetails.setPrice(cartService.priceCalculate());
        orderDetails.setAmount(cartService.getCart().size());
        orderDetails.setOrderId(String.valueOf(ring.getOrderId()));

        restTemplate.postForObject(url, ringsOrder, Order.class);
        restTemplate.postForObject(url2, orderDetails, OrderDetails.class);
    }

    public void OrderConfig(){
        cart = this.getAll();
        for(int i = 0; i < this.getAll().size(); i++){
            String hee = new String("");
            hee = this.getAll().get(i).getRings();
            hee = hee.replace("[", "").replace("]","");
            hee = hee.replace("{","[").replace("}","]");
            hee = hee.replace("[","").replace("]","");
            String[] split = hee.split(",");
            List<String> list = Arrays.asList(split);
            for (int j = 0;j < list.size();j++){
                String hee2 =list.get(j);
                split = list.get(j).trim().split("->");
                List<String> list1 = Arrays.asList(split);
                for (int k =0;k<list1.size();k++){
//                    System.out.println(list1.get(k).trim());
                }
                Rings rings = new Rings(UUID.fromString(list1.get(0)),list1.get(1),parseInt(list1.get(2)),parseInt(list1.get(3)),parseInt(list1.get(4)));
                cart.get(i).add(new Cart(rings,parseInt(list1.get(5))));
            }
        }
    }
    public List<Order> getDummy(String name){ //getall
        this.OrderConfig();
        List<Order> cart2 = new ArrayList<>();
        if (name.equals("admin")){
            return cart;
        }
        else {
            for (int i =0; i<cart.size();i++){
                if (name.equals(cart.get(i).getUsername())){
                    cart2.add(cart.get(i));
                }
            }
        }
        return cart2;
    }
    public Order getDummyByID(UUID id){
        Order ringsOrder = new Order();
        for (int i=0;i < cart.size();i++){
            if (cart.get(i).getOrderId().equals(id)){
                ringsOrder = cart.get(i);
                return ringsOrder;
            }
        }
        return ringsOrder;
    }

    public Order getOneById(UUID id) {
        String url = "http://localhost:8090/order/" + id;
        ResponseEntity<Order> response =
                restTemplate.getForEntity(url, Order.class);
        Order rings = response.getBody();
        return rings;
    }
    public void update(Order rings) {
        String url = "http://localhost:8090/order/" + rings.getOrderId();
        restTemplate.put(url, rings, Order.class);
    }
    public void delete(Order rings) {
        String url = "http://localhost:8090/order/" + rings.getOrderId();
        restTemplate.delete(url, rings, Order.class);
    }
}

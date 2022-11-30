package th.ac.ku.ringsRunner.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import th.ac.ku.ringsRunner.model.Cart;
import th.ac.ku.ringsRunner.model.Rings;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
public class RingsService {

    @Autowired
    private RestTemplate restTemplate;

    public List<Rings> getAll(){
        String url = "http://localhost:8090/rings";
        ResponseEntity<Rings[]> response = restTemplate.getForEntity(url, Rings[].class);
        Rings[] rings = response.getBody();
        return Arrays.asList(rings);
    }

    public List<Cart> getOrder(){
        String url = "http://localhost:8090/rings";
        ResponseEntity<Rings[]> response = restTemplate.getForEntity(url, Rings[].class);
        Rings[] rings = response.getBody();
        ArrayList orders = new ArrayList();
        for(int i = 0 ; i < rings.length; i++){
            orders.add(new Cart(rings[i], 0));
        }
        return orders;
    }

    public void addRings(Rings rings){
        String url = "http://localhost:8090/rings";
        restTemplate.postForObject(url, rings, Rings.class );
    }

    public Rings getOneById(UUID id){
        String url = "http://localhost:8090/rings/" + id;
        ResponseEntity<Rings> response =
                restTemplate.getForEntity(url, Rings.class);
        Rings rings = response.getBody();
        return rings;
    }
    public void update(Rings rings){
        String url = "http://localhost:8090/rings/" + rings.getId();
        restTemplate.put(url, rings, Rings.class);
    }

    public void updateCart(List<Cart> cart){
        for (int i = 0 ; i < cart.size(); i++){
            Rings update = this.getOneById(cart.get(i).getRings().getId());
            update.setAmount(update.getAmount()-cart.get(i).getQuantity());
            String url = "http://localhost:8090/rings/" + update.getId();
            if(update.getAmount() != 0){
                restTemplate.put(url, update, Rings.class);
            }
            else {
                restTemplate.delete(url,update,Rings.class);
            }
        }
    }
}

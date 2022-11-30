package th.ac.ku.ringsRunner.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import th.ac.ku.ringsRunner.model.Cart;
import th.ac.ku.ringsRunner.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


@Service
public class CartService {
    @Autowired
    private RingsService ringsService;

    private List<Cart> cart = new ArrayList<>();

    public void addCart(UUID id,Integer num){
        cart.add(new Cart(ringsService.getOneById(id), num));
    }
    public List<Cart> getCart() {
        return cart;
    }


    public void removeCart(UUID id){
        for(int i = 0 ; i < cart.size(); i++){
            if (id.equals(cart.get(i).getRings().getId())){
                cart.remove(i);
            }

        }
    }

    public int priceCalculate(){
        int total = 0;
        for (int i = 0 ; i< cart.size(); i++){
            total += (cart.get(i).getQuantity() * cart.get(i).getRings().getPrice());
        }
        return total;
    }
    public void removeall(){
        cart = new ArrayList<>();
    }

}

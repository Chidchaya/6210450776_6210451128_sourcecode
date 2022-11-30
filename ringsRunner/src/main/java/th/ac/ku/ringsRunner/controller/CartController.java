package th.ac.ku.ringsRunner.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import th.ac.ku.ringsRunner.model.Cart;
import th.ac.ku.ringsRunner.service.CartService;
import th.ac.ku.ringsRunner.service.RingsService;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;
    @Autowired
    private RingsService ringsService;

    int ringCount;

    @GetMapping("")
    public String index(Model model) {
        int total = cartService.priceCalculate();
        model.addAttribute("priceCal", total);
        model.addAttribute("carts",cartService.getCart());
        return "carts";
    }

    @PostMapping("")
    public String addCart(@ModelAttribute("num") int number, @ModelAttribute("name") UUID id, Model model, RedirectAttributes redirectAttrs) {
        if (number != 0 ) {
            if (!exists(id, cartService.getCart(), number)) {
                cartService.addCart(id,number);
            }
            else {
                if (ringCount == 1){
                    redirectAttrs.addFlashAttribute("error","ห้ามเพิ่มสินค้าลงตะกร้ามากกว่าจำนวนที่มีอยู่");
                }
            }
        }
        model.addAttribute("carts", cartService.getCart());
        return "redirect:/rings";
    }
    @GetMapping("/remove/{id}")
    public String remove(@PathVariable UUID id, Model model) {
        cartService.removeCart(id);
        model.addAttribute("carts",cartService.getCart());
        return "redirect:/cart";
    }
    private boolean exists(UUID id, List<Cart> cart, int number) {

        for (int i = 0; i < cart.size(); i++) {
            if (cart.get(i).getRings().getId().equals(id)) {
                //System.out.println(cart.get(i).getQuantity());
                if (cart.get(i).getQuantity()+number<=(ringsService.getOneById(id).getAmount())){
                    cart.get(i).setQuantity(cart.get(i).getQuantity()+number);
                }
                else if (cart.get(i).getQuantity()+number > (ringsService.getOneById(id).getAmount())){
                    cart.get(i).setQuantity(ringsService.getOneById(id).getAmount());
                    ringCount = 1;
                }
                return true;
            }
        }
        return false;
    }
}

package th.ac.ku.ringsRunner.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import th.ac.ku.ringsRunner.model.Order;
import th.ac.ku.ringsRunner.model.OrderDetails;
import th.ac.ku.ringsRunner.model.User;
import th.ac.ku.ringsRunner.service.CartService;
import th.ac.ku.ringsRunner.service.OrderService;
import th.ac.ku.ringsRunner.service.RingsService;

import java.util.Calendar;

@Controller
@RequestMapping("/order")
public class CheckListController {
    @Autowired
    private OrderService service;
    private int total;
    private Order order;
    @Autowired
    private CartService cartService;
    @Autowired
    private RingsService ringsService;

    @GetMapping("/list")
    public String getOrders(Model model)
    {
        total = cartService.priceCalculate();
        model.addAttribute("priceCal", total);
        model.addAttribute("amount", cartService.getCart().size());
        model.addAttribute("carts",cartService.getCart());
        return "checklist";
    }

    @GetMapping("/address")
    public String getAddress(Model model){
        int total = cartService.priceCalculate();
        model.addAttribute("priceCal", total);
        return "address";
    }

    @PostMapping("/address")
    public String addOrder(Model model, @ModelAttribute Order order, @ModelAttribute User user, RedirectAttributes redirectAttrs, @ModelAttribute OrderDetails orderDetails){
        if (checkAddress(order)){
            redirectAttrs.addFlashAttribute("error","Please correct all information");
            return "redirect:/order/address";
        }
        else {
            total = cartService.priceCalculate();
            model.addAttribute("priceCal", total);
            Calendar calndr = Calendar.getInstance();
            order.setDate(calndr.getTime());
            service.addOrder(order,orderDetails);
            cartService.removeall();
            return "redirect:/order";
        }

    }
    public boolean checkAddress(Order order){
        if ((order.getName().equals("")) || (order.getSurname().equals("")) || (order.getAddress().equals("")) || (order.getMobile().equals(""))) {
            return true;
        }return false;
    }
}

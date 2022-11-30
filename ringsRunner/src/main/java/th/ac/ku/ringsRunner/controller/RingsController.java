package th.ac.ku.ringsRunner.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import th.ac.ku.ringsRunner.model.Rings;
import th.ac.ku.ringsRunner.service.RingsService;
import th.ac.ku.ringsRunner.service.UserService;

import java.util.UUID;

@Controller
@RequestMapping("/rings")
public class RingsController {

    @Autowired
    private RingsService ringsService;
    @Autowired
    private UserService userServices;


    @GetMapping("/edit/{id}")
    public String getEditForm(@PathVariable UUID id, Model model) {
        Rings rings = ringsService.getOneById(id);
        model.addAttribute("rings", rings);
        return "rings-edit";
    }

    @PostMapping("/edit")
    public String edit(@ModelAttribute Rings rings, Model model,RedirectAttributes redirectAttrs) {
        if(checkAddRings(rings.getSize(), rings.getPrice(), rings.getAmount())){
            ringsService.update(rings);
            return "redirect:/rings";
        }
        else{
            redirectAttrs.addFlashAttribute("error","ห้ามใส่เลขติดลบหรือเท่ากับ 0");
            return "redirect:/rings";
        }
    }

    @GetMapping
    public String getRings(Model model, Authentication authentication)
    {
        userServices.setLoginUser(authentication.getName());
        model.addAttribute("rings", ringsService.getAll());
        return "rings";
    }

    @GetMapping("/add")
    public String getAddForm(){
        // return rings-add.html
        return "rings-add";
    }

    @PostMapping("/add")
    public String addRings(@ModelAttribute Rings rings, Model model,
                           RedirectAttributes redirectAttrs) {
        if(checkAddRings(rings.getSize(), rings.getPrice(), rings.getAmount())){
            ringsService.addRings(rings);
            return "redirect:/rings";
        }
        else{
            redirectAttrs.addFlashAttribute("error","ห้ามใส่เลขติดลบหรือเท่ากับ 0");
            return  "redirect:/rings/add";
        }

    }

    public boolean checkAddRings(int size, int price, int amount){
        if (size > 0 && price > 0 && amount > 0){
            return true;
        }
        return false;
    }

}

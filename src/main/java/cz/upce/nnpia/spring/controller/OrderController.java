package cz.upce.nnpia.spring.controller;

import cz.upce.nnpia.spring.service.ShoppingCartService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class OrderController {

    private final ShoppingCartService shoppingCartService;

    public OrderController(ShoppingCartService shoppingCartService) {
        this.shoppingCartService = shoppingCartService;
    }

    @GetMapping("/checkout")
    public String checkout(Model model){
        shoppingCartService.checkout();
        return "checkout";
    }
}

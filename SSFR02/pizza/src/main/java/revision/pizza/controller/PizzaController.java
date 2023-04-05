package revision.pizza.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import revision.pizza.model.Delivery;
import revision.pizza.model.Order;
import revision.pizza.model.Pizza;
import revision.pizza.service.OrderSvc;

@Controller
public class PizzaController {

    @Autowired
    private OrderSvc orderSvc;

    @GetMapping(path={"/", "/index.html"})
    public String view0(Model model0, HttpSession session){
        session.invalidate();
        model0.addAttribute("pizza2",new Pizza());
        return "index"; //view0 = index
    }
    
    @PostMapping("/pizza")
    public String view1(Model model1, @ModelAttribute("pizza2") @Valid Pizza pizza2, BindingResult binding, HttpSession session){
        if(binding.hasErrors()) return "index";
        //pizza2 to test error binding to object
        // model1.addAttribute("pizza2",pizza2);
        System.out.println(pizza2);
        session.setAttribute("pizza",pizza2);
        model1.addAttribute("delivery", new Delivery());
        return "pizza"; //view1 = pizza
    }

    @PostMapping("/pizza/order")
    public String view2(Model model2, @ModelAttribute @Valid Delivery delivery,BindingResult binding, HttpSession session){
        if(binding.hasErrors()) return "pizza";
        Pizza pizza = (Pizza)session.getAttribute("pizza");
        Order order = orderSvc.createOrder(pizza,delivery);
        model2.addAttribute("order",order);
        return "order";
    }

    @GetMapping("/order/{id}")
    public @ResponseBody String view3( @PathVariable(name="id") String id, HttpSession session){
        Order order = orderSvc.getOrder(id);
        return order.toString();
    }

}





//view2 = order
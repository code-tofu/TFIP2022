package revision.ecomm.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import revision.ecomm.model.Delivery;
import revision.ecomm.model.Item;
import revision.ecomm.model.Order;
import revision.ecomm.model.Quotation;
import revision.ecomm.service.QuotationService;

@Controller
@RequestMapping(path={"/","index.html"})
public class PurchaseOrderController {

    @Autowired
    private QuotationService quoteSvc;

    private static final List<String> stockList = Arrays.asList("apple", "orange", "bread", "cheese", "chicken",
    "mineral_water", "instant_noodles");
    public static List<String> getStocklist() {
        return stockList;
    }
    @GetMapping
    public String view1get(Model model,HttpSession session){
        Order order = (Order)session.getAttribute("order");
        if(null == order){
            order = new Order();
            System.out.println(">> New Order Created");
            session.setAttribute("order", order);
        }

        model.addAttribute("orderList", order.getOrderList());
        model.addAttribute("stockList", stockList);
        model.addAttribute("item",new Item());
        return "view1";
    }

    @PostMapping
    public String view1post(Model model, @ModelAttribute("item") @Valid Item item, BindingResult binding, HttpSession session){
        Order order = (Order)session.getAttribute("order");
        if(null == order){
            order = new Order();
            System.out.println(">> New Order Created");
            session.setAttribute("order", order);
        }
        model.addAttribute("item",item);
        model.addAttribute("orderList", order.getOrderList());
        model.addAttribute("stockList", stockList);
        if(binding.hasErrors()){
            return "view1";
        }
        order.addToOrder(item);
        return "view1";
    }

    @GetMapping("/clear")
    public String view1clear(HttpSession session){
        session.invalidate();
        return "redirect:/";
    }

    @GetMapping("/shippingaddress")
    public String view2(Model model, HttpSession session){
        Order order = (Order)session.getAttribute("order");
        if(null == order){
            order = new Order();
            System.out.println(">> New Order Created");
            session.setAttribute("order", order);
        }

        //TODO: add if cart is null
        if(order.getOrderList().isEmpty()){
            // session.setAttribute("EmptyError", new ObjectError("empty","Cart must not be empty"));
            System.out.println(">>EMPTY CART");
            return "redirect:/";
        }

        model.addAttribute("delivery", new Delivery());
        return "view2";
    }

    @PostMapping("/invoice")
    public @ResponseBody String view3(@Valid Delivery delivery, BindingResult binding, Model model, HttpSession session) throws Exception{
        model.addAttribute("delivery", delivery);
        if(binding.hasErrors()){
            return "view2";
        }

        Order order = (Order)session.getAttribute("order");
        if(null == order){
            return "redirect:/";
        }
        System.out.println(">>GET QUOTE");

        String newQuote = quoteSvc.getQuotationTest(order.getOrderItemList());
        return newQuote;    


        // TODO-add optional and return to view2 if quotation error
        // Quotation newQuote = quoteSvc.getQuotations(order.getOrderItemList());
        // order.generateOrder(newQuote);
        // model.addAttribute("invoice", order);
        // session.invalidate();
        // return "view3";

    }   
}

package revision.pizza.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import revision.pizza.model.Delivery;
import revision.pizza.model.Order;
import revision.pizza.model.Pizza;
import revision.pizza.repo.PizzaRepo;
import revision.pizza.util.PizzaUtil;

@Service
public class OrderSvc {

    @Autowired
    private PizzaRepo pizzaRepo;

    public  Order createOrder(Pizza pizza, Delivery delivery){
        Order order = new Order();
        order.setPizza(pizza);

        order.setOrderId(PizzaUtil.getUUID());
        order.setName(delivery.getName());
        order.setAddress(delivery.getAddress());
        order.setPhone(delivery.getPhone());
        order.setRush(delivery.isRush());
        order.setComments(delivery.getComments());
        order.setTotal(calcTotal(pizza,delivery.isRush()));

        System.out.print(this.saveOrder(order));
        System.out.println(this.getOrder(order.getOrderId()));

        return order;
    }

    public static double calcTotal(Pizza pizza, boolean isRush){
        double total = 0;
        switch (pizza.getName()){
            case "bella":
            case "marinara":
            case "pianatacalabrese":
                total += 30;
                break;
            case "magherita":
                total += 22;
                break;
            case "trioformaggio":
                total += 25;
                break;
            default: //do we need default?
                total = 0;
        }
        switch (pizza.getSize()){
            case "md":
                total *= 1.2;
                break;
            case "lg":
                total *= 1.5;
                break;
            default:
                total *= 1;
        }
        total *= pizza.getQuantity();
        if(isRush) total+=2;
        return total;
    }
    
    public boolean saveOrder(Order order){
        return pizzaRepo.saveOrderDB(order);
    }

    public Order getOrder(String orderID){
        return pizzaRepo.getOrderDB(orderID);
    }
    
}

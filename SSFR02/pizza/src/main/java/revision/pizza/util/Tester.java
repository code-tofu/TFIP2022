package revision.pizza.util;

import jakarta.json.JsonObject;
import revision.pizza.model.Order;
import revision.pizza.model.Pizza;

public class Tester {

    public static void jsonTester(){
        Order testOrder = new Order();
        testOrder.setOrderId("88888888");
        testOrder.setName("Test Customer");
        testOrder.setAddress("10 HMK Terrace");
        testOrder.setPhone("98765432");
        testOrder.setRush(true);
        testOrder.setComments("no comments");

        // need to autowrire or pass in a pizza reference
        Pizza testPizza = new Pizza();
        testOrder.setPizza(testPizza);
        testOrder.getPizza().setName("pepperoni");
        testOrder.getPizza().setSize("lg");
        testOrder.getPizza().setQuantity(10);
        testOrder.setTotal(999.99);
       
        JsonObject testJson = testOrder.toJSON().build();
        System.out.println(testJson);
        System.out.println(Order.fromJson(testJson));

        String testString = testJson.toString();
        System.out.println(testString);
        System.out.println(Order.fromJSONString(testString));
    }
    
}

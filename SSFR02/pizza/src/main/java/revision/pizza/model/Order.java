package revision.pizza.model;

import java.io.StringReader;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonObjectBuilder;

public class Order {

//FIELDS AND VALIDATION
    private String orderId;
    private String name;
    private String address;
    private String phone;
    private boolean rush;
    private String comments;
    private Pizza pizza;
    private double total;

// GETTERS AND SETTERS
    public String getOrderId() {
        return orderId;
    }
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getAddress() {
        return address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public boolean isRush() {
        return rush;
    }
    public void setRush(boolean rush) {
        this.rush = rush;
    }
    public String getComments() {
        return comments;
    }
    public void setComments(String comments) {
        this.comments = comments;
    }
    public Pizza getPizza() {
        return pizza;
    }
    public void setPizza(Pizza pizza) {
        this.pizza = pizza;
    }
    public double getTotal() {
        return total;
    }
    public void setTotal(double total) {
        this.total = total;
    }

// JSON
    // toJSON will call build on object builder to get json object
    public JsonObjectBuilder toJSON(){
        return Json.createObjectBuilder().add("orderId",this.getOrderId())
                                        .add("name",this.getName())
                                        .add("address",getAddress())
                                        .add("phone",getPhone())
                                        .add("rush",isRush())
                                        .add("comments",getComments())
                                        .add("pizza",getPizza().getName())
                                        .add("size",getPizza().getSize())
                                        .add("quantity",getPizza().getQuantity())
                                        .add("total",getTotal());
    }

    public static Order fromJSONString(String jsonStr){
        StringReader s = new StringReader(jsonStr);
        return Order.fromJson(Json.createReader(s).readObject());
    }

    public static Order fromJson(JsonObject orderJson){
        Order order = new Order();
        order.setOrderId(orderJson.getString("orderId"));
        order.setName(orderJson.getString("name"));
        order.setAddress(orderJson.getString("address"));
        order.setPhone(orderJson.getString("phone"));
        order.setRush(orderJson.getBoolean("rush"));
        order.setComments(orderJson.getString("comments"));
        Pizza newPizza = new Pizza();
        order.setPizza(newPizza);
        //if nesting is more than one level i need to pass the subjson object to the subobject create?
        order.getPizza().setName(orderJson.getString("pizza"));
        order.getPizza().setSize(orderJson.getString("size"));
        order.getPizza().setQuantity(orderJson.getInt("quantity"));
        order.setTotal(orderJson.getJsonNumber("total").doubleValue());
        return order;
    }

    @Override
    public String toString() {
        return "Order [orderId=" + orderId + ", name=" + name + ", address=" + address + ", phone=" + phone + ", rush="
                + rush + ", comments=" + comments + ", pizza=" + pizza + ", total=" + total + "]";
    }

    
}

/*
 * JSON Format:
{
"orderId": <order id, string>,
"name": <name, string, view 1>,
"address": <address, string, view 1>,
"phone": <phone, string, view 1>,
"rush": <true or false, boolean, view 1>,
"comments": <comments, string, view 1>,
"pizza": <pizza name, string, view 0>,
"size": <pizza size, string, view 0>,
"quantity": <quantity, number, view 0>
"total": <total cost, number>
}
 */
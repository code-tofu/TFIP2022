package revision.pizza.model;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class Delivery {

    @NotNull(message="Please key in your name")
    @Size(min=3, message="Your name cannot be less than 3 characters")
    private String name;
    @NotNull(message="Please key in your name")
    @Size(min=3, message="Your address cannot be less than 5 characters")
    private String address;
    @NotNull(message="Please key in  your phone number")
    @Size(min=8,max=8, message="Your number must be 8 digits")
    private String phone;
    private boolean rush;
    private String comments;

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
    @Override
    public String toString() {
        return "Delivery [name=" + name + ", address=" + address + ", phone=" + phone + ", rush=" + rush + ", comments="
                + comments + "]";
    }
    
    
}

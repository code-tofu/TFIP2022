package revision.pizza.model;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class Pizza {
    @NotNull(message="Please select a pizza")
    private String name;
    private String size;

    @NotNull(message="Please input a number")
    @Min(value=1,message="Please order at least 1 pizza")
    @Max(value=10,message="Maximum of 10 pizzas allowed")
    private int quantity;
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getSize() {
        return size;
    }
    public void setSize(String size) {
        this.size = size;
    }
    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "Pizza [name=" + name + ", size=" + size + ", quantity=" + quantity + "]";
    }


}

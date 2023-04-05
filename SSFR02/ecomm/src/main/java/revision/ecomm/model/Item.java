package revision.ecomm.model;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

public class Item {
    //TODO: check for in stock
    @NotBlank(message="You must select an item")
    private String name;

    @Min(value=1, message="You must add at least 1 item")
    private int qty;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public int getQty() {
        return qty;
    }
    public void setQty(int qty) {
        this.qty = qty;
    }   
}

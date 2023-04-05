package revision.ecomm.model;

import jakarta.validation.constraints.Size;

public class Delivery {

    @Size(min=3,message="Please input an name more than 3 letters")
    private String name;

    @Size(min=3,message="Please input an address more than 3 letters")
    private String address;

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

    
    
}

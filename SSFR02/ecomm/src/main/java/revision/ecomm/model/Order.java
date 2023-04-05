package revision.ecomm.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import revision.ecomm.service.QuotationService;
import revision.ecomm.util.EcommUtil;

import java.util.ArrayList;

public class Order {

    private Map<String,Integer> orderList = new HashMap<>();    
    private Delivery delivery;
    private double total;
    private String invoiceId;

    public void addToOrder(Item item){
        if(this.orderList.containsKey(item.getName())){
            this.orderList.put(item.getName(),this.orderList.get(item.getName()) + item.getQty());
        } else {
            this.orderList.put(item.getName(),item.getQty());
        }
    }

    public List<String> getOrderItemList(){
        List<String> itemList =  new ArrayList(this.getOrderList().keySet());
        return itemList;
    }

    public Map<String, Integer> getOrderList() {
        return orderList;
    }

    public void setOrderList(Map<String, Integer> orderList) {
        this.orderList = orderList;
    }

    public Delivery getDelivery() {
        return delivery;
    }

    public void setDelivery(Delivery delivery) {
        this.delivery = delivery;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(String invoiceId) {
        this.invoiceId = invoiceId;
    }
    
    public void generateOrder(Quotation myQuote){
        this.setTotal(QuotationService.calculateTotal(this,myQuote));
        this.setInvoiceId(EcommUtil.generateUUID(6));
    }
    
    
}

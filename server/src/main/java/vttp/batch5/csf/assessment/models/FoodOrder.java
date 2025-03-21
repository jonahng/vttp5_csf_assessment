package vttp.batch5.csf.assessment.models;

import jakarta.json.JsonArray;

public class FoodOrder {
    private String username;
    private String password;
    private JsonArray items;
    private String order_id;
    private double totalOrderPrice;


    


    public FoodOrder() {
    }


    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public JsonArray getItems() {
        return items;
    }
    public void setItems(JsonArray items) {
        this.items = items;
    }

    public String getOrder_id() {
        return order_id;
    }
    public void setOrder_id(String order_id) {
        this.order_id = order_id;
    }

    public double getTotalOrderPrice() {
        return totalOrderPrice;
    }


    public void setTotalOrderPrice(double totalOrderPrice) {
        this.totalOrderPrice = totalOrderPrice;
    }


    
    
}

package vttp.batch5.csf.assessment.models;

import jakarta.json.JsonArray;

public class FoodOrder {
    private String username;
    private String password;
    private JsonArray items;


    
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


    
    
}

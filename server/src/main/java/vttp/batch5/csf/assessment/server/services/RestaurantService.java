package vttp.batch5.csf.assessment.server.services;

import java.io.StringReader;
import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import vttp.batch5.csf.assessment.models.FoodOrder;
import vttp.batch5.csf.assessment.server.ServerApplication;
import vttp.batch5.csf.assessment.server.controllers.RestaurantController;
import vttp.batch5.csf.assessment.server.repositories.OrdersRepository;
import vttp.batch5.csf.assessment.server.repositories.RestaurantRepository;

@Service
public class RestaurantService {

    private final ServerApplication serverApplication;

    private final RestaurantController restaurantController;
  @Autowired
  OrdersRepository ordersRepository;

  @Autowired
  RestaurantRepository restaurantRepository;


    RestaurantService(RestaurantController restaurantController, ServerApplication serverApplication) {
        this.restaurantController = restaurantController;
        this.serverApplication = serverApplication;
    }


  // TODO: Task 2.2
  // You may change the method's signature
  public List<Document> getMenu() {
    return ordersRepository.getMenu();
  }
  
  // TODO: Task 4
  public boolean isLoginValid(String userName, String password){
    return restaurantRepository.isLoginValid(userName, password);
  }


  public FoodOrder processFoodOrderJson(String payload){
        FoodOrder foodOrder = new FoodOrder();
    		JsonReader jsonReader = Json.createReader(new StringReader(payload));
        JsonObject jo1 = jsonReader.readObject();

        foodOrder.setUsername(jo1.getString("username"));
        foodOrder.setPassword(jo1.getString("password"));
        foodOrder.setItems(jo1.getJsonArray("items"));

			System.out.println("USERNAME RECEIVED FROM CLIENT:" + foodOrder.getUsername() + foodOrder.getItems());
			return foodOrder;
  }


}

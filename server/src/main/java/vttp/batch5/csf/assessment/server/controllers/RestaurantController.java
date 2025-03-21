package vttp.batch5.csf.assessment.server.controllers;

import java.util.List;
import java.util.UUID;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vttp.batch5.csf.assessment.models.FoodOrder;
import vttp.batch5.csf.assessment.server.ServerApplication;
import vttp.batch5.csf.assessment.server.services.RestaurantService;


@RestController
@RequestMapping("")
public class RestaurantController {

    private final ServerApplication serverApplication;
  @Autowired
  RestaurantService restaurantService;

    RestaurantController(ServerApplication serverApplication) {
        this.serverApplication = serverApplication;
    }

  // TODO: Task 2.2
  // You may change the method's signature
  @GetMapping("/api/menu")
  public ResponseEntity<List<Document>> getMenus() {
    System.out.println("RESTAURANT SERVICE:" + restaurantService.getMenu().toString());
    return ResponseEntity.ok(restaurantService.getMenu());
  }

  // TODO: Task 4
  // Do not change the method's signature
  @PostMapping("/api/food_order")
  public ResponseEntity<String> postFoodOrder(@RequestBody String payload) {
    FoodOrder foodOrder = new FoodOrder();
    foodOrder = restaurantService.processFoodOrderJson(payload);

    //check if login credentials are valid
    if(!restaurantService.isLoginValid(foodOrder.getUsername(), foodOrder.getPassword())){
      //if not valid...
      return ResponseEntity.status(401).body("INVALID USERNAME AND/OR PASSWORD");
    }
    //CREATING 8 digit unique id
    foodOrder.setOrder_id(UUID.randomUUID().toString().replace("-", "").substring(0, 8));

    //Make payment to pamyment service
    foodOrder.setTotalOrderPrice(100); //for testing
    restaurantService.payToGateway(foodOrder);


    return ResponseEntity.ok("{}");
  }
}

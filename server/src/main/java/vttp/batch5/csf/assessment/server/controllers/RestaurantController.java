package vttp.batch5.csf.assessment.server.controllers;

import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import vttp.batch5.csf.assessment.server.services.RestaurantService;


@RestController
@RequestMapping("")
public class RestaurantController {
  @Autowired
  RestaurantService restaurantService;

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
    

    return ResponseEntity.ok("{}");
  }
}

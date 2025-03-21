package vttp.batch5.csf.assessment.server.services;

import java.io.StringReader;
import java.util.Date;
import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import jakarta.json.Json;
import jakarta.json.JsonObject;
import jakarta.json.JsonReader;
import vttp.batch5.csf.assessment.models.FoodOrder;
import vttp.batch5.csf.assessment.models.PaymentDetails;
import vttp.batch5.csf.assessment.models.PaymentResponse;
import vttp.batch5.csf.assessment.server.repositories.OrdersRepository;
import vttp.batch5.csf.assessment.server.repositories.RestaurantRepository;

@Service
public class RestaurantService {

  @Autowired
  OrdersRepository ordersRepository;

  @Autowired
  RestaurantRepository restaurantRepository;





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


  public String payToGateway(FoodOrder foodOrder){
    RestTemplate restTemplate = new RestTemplate();

    //creating payment details object
    PaymentDetails paymentDetails = new PaymentDetails();
    paymentDetails.setPayer(foodOrder.getUsername());
    paymentDetails.setOrder_id(foodOrder.getOrder_id());
    paymentDetails.setPayee("NG YONG HAN JONAH");
    paymentDetails.setPayment(foodOrder.getTotalOrderPrice());


    //sending post to the gateway
    RequestEntity<PaymentDetails> request = RequestEntity.post("https://payment-service-production-a75a.up.railway.app/")
    .contentType(MediaType.APPLICATION_JSON)
    .header("X-Authenticate", paymentDetails.getPayer()) //setting payer as the x authenticate header
		.accept(MediaType.APPLICATION_JSON)
		.body(paymentDetails);

    ResponseEntity<String> GatewayResponse = restTemplate.exchange(request, String.class);
    //READING RESPONSE FROM SERVER
    JsonReader jsonReader = Json.createReader(new StringReader(GatewayResponse.getBody()));
    JsonObject GatewayResponseJson = jsonReader.readObject();

    int epochDate = GatewayResponseJson.getInt("timestamp");
    Date timestampDate = new Date(epochDate);

    try {

    //adding order details to mysql place_orders
    restaurantRepository.insertOrderAndPayment(foodOrder.getOrder_id(), GatewayResponseJson.getString("payment_id") ,
    timestampDate , foodOrder.getTotalOrderPrice(), foodOrder.getUsername());
    } catch (Exception e) {
      System.out.println("ERROR WRITING TO MYSQL");
    }

    try {
          //add order details to mongodb
    ordersRepository.addOrders(foodOrder.getOrder_id(), foodOrder.getOrder_id(), GatewayResponseJson.getString("payment_id")
    , foodOrder.getUsername(),foodOrder.getTotalOrderPrice() , timestampDate, foodOrder.getItems());
    
    } catch (Exception e) {
      System.out.println("ERROR WRITING TO MONGO");
    }

/*     PaymentResponse paymentResponse = new PaymentResponse();
    paymentResponse.setOrder_id(foodOrder.getOrder_id());
    paymentResponse.setPayment_id(GatewayResponseJson.getString("payment_id"));
    paymentResponse.setTimestamp(epochDate);
    paymentResponse.setTotal(foodOrder.getTotalOrderPrice()); */

    JsonObject receiptObject = Json.createObjectBuilder()
    .add("orderId", foodOrder.getOrder_id())
    .add("paymentId", GatewayResponseJson.getString("payment_id"))
    .add("total", foodOrder.getTotalOrderPrice())
    .add("timestamp", epochDate)
    .build();

    String paymentResponse = receiptObject.toString();

    //RETURNS THE PAYMENT RESPONSE BACK TO CONTROLLER TO SEND BACK TO FRONT END
    return paymentResponse;
  }


}

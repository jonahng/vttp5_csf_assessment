package vttp.batch5.csf.assessment.server.repositories;

import java.util.Date;
import java.util.List;

import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObject;
import vttp.batch5.csf.assessment.server.controllers.RestaurantController;


@Repository
public class OrdersRepository {
  @Autowired
  MongoTemplate template;


  // TODO: Task 2.2
  // You may change the method's signature
  // Write the native MongoDB query in the comment below
  //

  //  Native MongoDB query here:
    //HERE IS THE QUERY
  //    db.menus.find().sort({name: 1})


  public List<Document> getMenu() {
    Criteria criteria = Criteria.where("_id").exists(true);
    Query query = Query.query(criteria).with(Sort.by(Direction.ASC, "name"));
    //sorts according to the name in ascending order
    return template.find(query, Document.class, "menus");

  }

  // TODO: Task 4
  // Write the native MongoDB query for your access methods in the comment below
  //
  //  Native MongoDB query here

  /* 
          Query to Insert Document, values would be next to colons :
            db.orders.insert({
            _id:,
            payment_id:,
            username:,
            total:,
            timestamp:,
            items:
            })
   */
  public void addOrders(String _id, String order_id, String payment_id, String username, double total, Date timestamp, JsonArray items){
    JsonObject jo = Json.createObjectBuilder()
    .add("_id", _id)
    .add("order_id", order_id)
    .add("payment_id", payment_id)
    .add("username", username)
    .add("total", total)
    .add("items", items)
    .build();
  }
  
}

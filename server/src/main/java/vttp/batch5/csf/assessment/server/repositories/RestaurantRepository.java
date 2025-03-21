package vttp.batch5.csf.assessment.server.repositories;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;
import vttp.batch5.csf.assessment.server.controllers.RestaurantController;
import vttp.batch5.csf.assessment.server.services.RestaurantService;

// Use the following class for MySQL database
@Repository
public class RestaurantRepository {


    @Autowired
    JdbcTemplate jdbcTemplate;






    public String getPasswordForUserName(String userName){
        //THIS retrieves the password from the database, so it can be compared against the password submitted
        String passwordFromDatabase = "";
        String SQL_GET_PASSWORD_WITH_USERNAME = " select * from customers WHERE username LIKE ?;";
        SqlRowSet rs = jdbcTemplate.queryForRowSet(SQL_GET_PASSWORD_WITH_USERNAME, userName);
        while (rs.next()) {
            passwordFromDatabase = rs.getString("password");
        }
        return passwordFromDatabase;

    }

    public boolean isLoginValid(String userName, String password){
        //this compares whether the password matches the password in the database
        //Convert password to SHA256 to compare, since that is how it is done for mysql insertion
        if(password.equals(getPasswordForUserName(userName))){
            return true;
        }
        return false;
    }


    public void insertOrderAndPayment(String order_id, String payment_id, Date order_date, double total, String username){
        String SQL_INSERT_ORDER = "insert into place_orders(order_id,payment_id,order_date,total,username) values (?,?,?,?,?)";
        try {
            int added = jdbcTemplate.update(SQL_INSERT_ORDER,order_id,payment_id,order_date,total,username);
            System.out.println("ADDED TO SQL:" + order_id);
        } catch (Exception e) {
            // TODO: handle exception
            System.out.println("ERROR ADDING TO MYSQL");
        }
        
    }

}

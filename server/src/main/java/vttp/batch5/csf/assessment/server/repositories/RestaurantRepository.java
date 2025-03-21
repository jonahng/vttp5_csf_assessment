package vttp.batch5.csf.assessment.server.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;
import vttp.batch5.csf.assessment.server.controllers.RestaurantController;

// Use the following class for MySQL database
@Repository
public class RestaurantRepository {

    private final RestaurantController restaurantController;
    @Autowired
    JdbcTemplate jdbcTemplate;


    RestaurantRepository(RestaurantController restaurantController) {
        this.restaurantController = restaurantController;
    }



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
        if(password.equals(getPasswordForUserName(userName))){
            return true;
        }
        return false;
    }

}

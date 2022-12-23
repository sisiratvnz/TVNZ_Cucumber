package nz.co.tvnz.stepdefs;

import io.cucumber.java.en.Given;
import nz.co.tvnz.dbconnection.ConnectionManager;
import nz.co.tvnz.libraries.TestContext;
import nz.co.tvnz.utilities.HelperUtility;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;
import org.junit.jupiter.api.Test;

import java.sql.*;


public class DBStepDefs extends HelperUtility{

    Logger logger = LogManager.getLogger(DBStepDefs.class);

    //Connection connection = new ConnectionManager().setConnection();

    public DBStepDefs(TestContext testContext) {
        super(testContext);
    }

    @Given("I select all from login database")
    public void testDBConnection(){
        try {
            ResultSet resultSet = connection.prepareStatement("select * from login").executeQuery();
            while (resultSet.next()){
                logger.debug(resultSet.getString("first_name"));
            }
        }
        catch (SQLException e){
            logger.debug(e.getMessage());
        }
    }

}

package nz.co.tvnz.dbconnection;

import nz.co.tvnz.configs.GlobalPropertyConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.*;

import static org.junit.jupiter.api.Assertions.fail;

public class ConnectionManager {

//    public ConnectionManager(){
//        connection = setConnection();
//    }

//    public Connection connection = null;
    static Logger logger = LogManager.getLogger(ConnectionManager.class);
//    public Connection setConnection(){
//        String userName = GlobalPropertyConfig.getGlobalProperties().getProperty("dbuser");
//        String password = GlobalPropertyConfig.getGlobalProperties().getProperty("dbpass");
//        String url = GlobalPropertyConfig.getGlobalProperties().getProperty("dburl");
//        try {
//            connection = DriverManager.getConnection(url, userName, password);
//            if (connection != null) {
//                logger.debug("Connected to the database!");
//            } else {
//                logger.debug("Failed to make connection...");
//            }
//        }
//        catch (SQLException e){
//            logger.debug(e.getMessage());
//        }
//        catch (Exception e){
//            logger.debug(e.getStackTrace());
//        }
//        return connection;
//    }

    public Connection connection;
    public Connection setConnection(){
        switch (GlobalPropertyConfig.getGlobalProperties().getProperty("database")){
            case "postgresql":
                String userName = GlobalPropertyConfig.getGlobalProperties().getProperty("dbuser");
                String password = GlobalPropertyConfig.getGlobalProperties().getProperty("dbpass");
                String url = GlobalPropertyConfig.getGlobalProperties().getProperty("dburl");
                try {
                    connection = DriverManager.getConnection(url, userName, password);
                    if (connection != null) {
                        logger.debug("Connected to the database!");
                    } else {
                        logger.debug("Failed to make connection...");
                    }
                }
                catch (SQLException e){
                    logger.debug(e.getMessage());
                }
                catch (Exception e){
                    logger.debug(e.getStackTrace());
                }
                break;
            case "mysql":
                String mUser = GlobalPropertyConfig.getGlobalProperties().getProperty("mdbuser");
                String mPassword = GlobalPropertyConfig.getGlobalProperties().getProperty("mdbpass");
                String mUrl = GlobalPropertyConfig.getGlobalProperties().getProperty("mdburl");
                try {
                    connection = DriverManager.getConnection(mUrl, mUser, mPassword);
                    if (connection != null) {
                        logger.debug("Connected to the database!");
                    } else {
                        logger.debug("Failed to make connection...");
                    }
                }
                catch (SQLException e){
                    logger.debug(e.getMessage());
                }
                catch (Exception e){
                    logger.debug(e.getStackTrace());
                }
                break;
            default:
                fail("Unknown database");
        }
        return connection;
    }
    public void closeConnection(){
        try {
            connection.close();
        }
        catch (SQLException e){
            logger.debug(e.getMessage());
        }
        catch (Exception e){
            logger.debug(e.getStackTrace());
        }
    }
}

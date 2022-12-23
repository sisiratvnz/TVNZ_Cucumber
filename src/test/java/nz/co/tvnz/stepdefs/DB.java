package nz.co.tvnz.stepdefs;

import io.cucumber.java.an.E;
import nz.co.tvnz.dbconnection.ConnectionManager;
import nz.co.tvnz.libraries.TestContext;
import nz.co.tvnz.utilities.HelperUtility;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import java.sql.*;

public class DB{
    Logger logger = LogManager.getLogger(DB.class);
    Connection connection = new ConnectionManager().setConnection();
    @Test
    public void queryDataBase(){
        String SQL_Select = "select * from login";
        try(Connection connection = DriverManager.getConnection(
                "jdbc:postgresql://127.0.0.1:5432/Users", "postgres", "test1234")){
            if(connection!=null){
                logger.debug("connected to the database!");
            }
            else {
                logger.debug("Failed to make connection...");
            }
            PreparedStatement preparedStatement = connection.prepareStatement(SQL_Select);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                logger.debug("Id : " + resultSet.getLong("id"));
                logger.debug("First Name : " + resultSet.getString("first_name"));
                logger.debug("Last Name : " + resultSet.getString("last_name"));
                logger.debug("User Name : " + resultSet.getString("user_id"));
                logger.debug("Password : " + resultSet.getString("password"));
            }
        }
        catch (SQLException e){
            logger.debug(e.getMessage());
        }
        catch (Exception e){
            logger.debug(e.getStackTrace());
        }
    }

    @Test
    public void connectDBToLogin() throws SQLException {
        String SQL_Select = "select * from login";
        PreparedStatement preparedStatement = connection.prepareStatement(SQL_Select);
        ResultSet resultSet = preparedStatement.executeQuery();
        while(resultSet.next()){
            logger.debug("Id : " + resultSet.getLong("id"));
            logger.debug("First Name : " + resultSet.getString("first_name"));
            logger.debug("Last Name : " + resultSet.getString("last_name"));
            logger.debug("User Name : " + resultSet.getString("user_id"));
            logger.debug("Password : " + resultSet.getString("password"));
        }
    }
    @Test
    public void insertARecordIntoDB() throws SQLException {
        String SQL_Inssert = "Insert into login values(3, 'Indi', 'Bandara', 'k111@grr.la', '11111111')";
        connection.prepareStatement(SQL_Inssert).executeQuery();
    }

    @Test
    public void updateRecord() throws SQLException {
        String SQL_Inssert = "UPDATE login SET user_id='k112@grr.la' WHERE id=3";
        connection.prepareStatement(SQL_Inssert).executeUpdate();
    }

    @Test
    public void deleteRecord() throws SQLException {
        String SQL_delete = "\n" +
                "DELETE FROM login WHERE id=2";
        connection.prepareStatement(SQL_delete).executeQuery();
    }

    @Test
    public void insertRecord() throws SQLException {
        String SQL_In = "insert into login(id, first_name, last_name, user_id, password)values(?,?,?,?,?);";
        connection.prepareStatement(SQL_In).setLong(1,4);
    }


    @Test
    public void dropTable(){
        try {
            String SQL_Drop = "DROP TABLE example";
            connection.prepareStatement(SQL_Drop).executeQuery();
        }catch (SQLException e){
            logger.debug(e.getMessage());
        }
    }

    @Test
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

    @Test
    public void testInnerJoin(){
        try{
            String SQL_InnerJoin = "SELECT employee.emp_name,salary.emp_salary FROM salary INNER JOIN employee ON salary.emp_id = employee.emp_id WHERE employee.emp_name LIKE 'A%';";
            ResultSet resultSet = connection.prepareStatement(SQL_InnerJoin).executeQuery();
            while (resultSet.next()){
                logger.debug(resultSet.getString("emp_name"));
                //logger.debug(resultSet.getString("emp_salary"));
            }


        }catch (SQLException e){
            logger.debug(e.getMessage());
        }
    }
}

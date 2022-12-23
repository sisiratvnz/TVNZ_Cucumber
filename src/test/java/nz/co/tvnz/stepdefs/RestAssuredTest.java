package nz.co.tvnz.stepdefs;
import com.google.gson.Gson;
import io.restassured.RestAssured;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import nz.co.tvnz.pojos.User;
import nz.co.tvnz.pojos.User1;
import nz.co.tvnz.pojos.Users;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class RestAssuredTest {
    Logger logger = LogManager.getLogger(RestAssuredTest.class);
    @Test
    public void restApiTest(){
        RestAssured.baseURI = "https://demoqa.com/BookStore/v1";
        RequestSpecification request = RestAssured.given();
        request.queryParam("ISBN","9781449325862");
        Response response = request.get( "/Book");

        Headers allHeaders = response.headers();
        Assertions.assertTrue(allHeaders.hasHeaderWithName("ETag"));
        for (Header header : allHeaders) {
            logger.debug("Key: "+ header.getName() + " Value : " +header.getValue());
        }
        logger.debug("Status received => " + response.getStatusLine());
        logger.debug("Response => " + response.prettyPrint());
        Assertions.assertEquals(200,response.getStatusCode(),"Expected status code is 200..");

        ResponseBody body = response.getBody();
        logger.debug("Response Body is: " + body.asPrettyString());

        JsonPath jsonPath = new JsonPath(body.asPrettyString());
        logger.debug("The book title is: "+ jsonPath.getString("title"));

//        RestAssured.baseURI = "https://simple-books-api.glitch.me";
//        RequestSpecification request = RestAssured.given();
//        Response response1 = request.request(Method.GET, "");
//        Assertions.assertEquals(200,response1.getStatusCode());
//        ResponseBody body = response1.getBody();
////        logger.debug("Response Body is: " + body.prettyPrint());
//        Assertions.assertTrue(body.asPrettyString().contains("Welcome to the Simple Books API."));
//        JsonPath jsonPath = response1.jsonPath();
//        logger.debug(jsonPath.get("message").toString());
//        Assertions.assertEquals("Welcome to the Simple Books API.",jsonPath.get("message").toString());



    }
    @Test
    public void postMethodTest(){
        RestAssured.baseURI = "https://reqres.in";
        RequestSpecification rs = RestAssured.given();
        JSONObject reqParams = new JSONObject();
        reqParams.put("name", "TQ123");
        reqParams.put("job", "tester");
        rs.body(reqParams.toString());

        User1 user = new User1();
        user.setName("Sisira");
        user.setJob("Tester");
        Gson gson = new Gson();
        rs.body(gson.toJson(user));

        Response response = rs.post("/api/users");
        ResponseBody body = response.getBody();
        logger.debug("Status received => "+ response.getStatusLine());
        logger.debug(body.asPrettyString());
    }

    @Test
    public void getListOfUsers(){
        RestAssured.baseURI = "https://reqres.in";
        RequestSpecification request = RestAssured.given();
        request.queryParam("page", "2");
        Response response = request.get("/api/users");
//        JSONObject jo = new JSONObject(response.getBody());
//
//        logger.debug(jo.getJSONArray("data"));



        JsonPath body = response.getBody().jsonPath();
        //ResponseBody body = response.getBody();
        logger.debug(body.prettyPrint());
        logger.debug(body.get("per_page").toString());
        logger.debug("Status received => " + response.getStatusLine());
        Assertions.assertTrue(body.prettyPrint().contains("To keep ReqRes free, contributions towards server costs are appreciated!"));
    }

    @Test
    public void register(){
        RestAssured.baseURI = "https://reqres.in";
        RequestSpecification rs = RestAssured.given();
        JSONObject reqParams = new JSONObject();
        reqParams.put("eve.holt@reqres.in", "pistol");
        //reqParams.put("job", "tester");
        rs.body(reqParams.toString());
        Response response = rs.post("/api/register");
        ResponseBody body = response.getBody();
        logger.debug("Status received => "+ response.getStatusLine());
        logger.debug(body.asPrettyString());
    }



    @Test
    public void putRequest(){
        RestAssured.baseURI = "https://reqres.in";
        RequestSpecification rs = RestAssured.given();
        JSONObject reqParams = new JSONObject();
        reqParams.put("morpheus", "zion resident");
        //reqParams.put("job", "tester");
        User1 user = new User1();
        user.setName("Sisira");
        user.setJob("Tester");
        Gson gson = new Gson();
        rs.body(gson.toJson(user));
        rs.body(JSONObject.valueToString(user));

        rs.body(reqParams.toString());
        Response response = rs.put("/api/users/2");
        JsonPath body = response.getBody().jsonPath();
        logger.debug("Status received => "+ response.getStatusLine());
        //logger.debug(body.prettyPrint());
        logger.debug(response.body().asPrettyString());
    }

    @Test
    public void deleteRequest(){
        RestAssured.baseURI = "https://reqres.in";
        RequestSpecification rs = RestAssured.given();
        JSONObject reqParams = new JSONObject();
        reqParams.put("aa", "zion resident");
        //reqParams.put("job", "tester");
        rs.body(reqParams.toString());
        Response response = rs.delete("/api/users/2");
        JsonPath body = response.getBody().jsonPath();
        logger.debug("Status received => "+ response.getStatusLine());
        //logger.debug(body.prettyPrint());
        logger.debug(response.body().asPrettyString());
    }

    @Test
    public void registerTVNZ(){
//        //String bearerToken = "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCIsImtpZCI6Ik5UYzRSRUpDTkVRNE5FRXpRalJDT1VOQ1JUZEVSVFF6UXpreVJqbEZNRVU1UlRFNU9EYzVOZyJ9.eyJpc3MiOiJodHRwczovL2F1dGgtc3RhZ2UudHZuei5jby5uei8iLCJzdWIiOiJhdXRoMHw2Mjc4OGU2YjZlNjdmYjAwNjgyMDIxY2IiLCJhdWQiOlsidHZuei1hcGlzLXN0YWdlIiwiaHR0cHM6Ly90dm56LXN0YWdlLmF1LmF1dGgwLmNvbS91c2VyaW5mbyJdLCJpYXQiOjE2Njk2NjgzNTMsImV4cCI6MTY2OTc1NDc1MywiYXpwIjoiWVMwbjZ3MlZxa0NGV3hzaGhFM3d5S2dwN3p0dlRlWEciLCJzY29wZSI6Im9wZW5pZCBwcm9maWxlIGVtYWlsIn0.R7wxp6FdtM8MWW9PDBEgsw1X28uoCY2uDi1R94-gfPYkR9stgxQ7xTTwdb0NZgmQLkH0P2QHClY_mmSNQ_KwJMRW6WwspZiAYT2FmctYMgqN6ekF3NNWh4ZW7j-9t_5vPo520ftNfZowyMUKIQsCXEj7DwGFeycoEipVqFK6A0-aVrPnM9Me3GzVIURReOFqrLKpRVsUVIe8RgTJEFfKkREXwzUUVjl1BP2pEq_qKpajwd8TyaESRQJFbzoMeDFcCna-z9hywzzCFcW8PuUhiEvytBayrxl0lHy41FTbef3w_Oft8bLfy5EGlPuKD7ZiyMgFAND3xB8XKwTWBUUByA";
//        RestAssured.baseURI = "https://apis-public-stage.tech-preprod.tvnz.co.nz";
//        //RequestSpecification request = RestAssured.given().headers(bearerToken, ContentType.JSON);
//        RequestSpecification request = RestAssured.given();
//        JSONObject reqParams = new JSONObject();
//        reqParams.put("k10@grr.la", "tvnz1234");
//        //reqParams.put("job", "tester");
//        request.body(reqParams.toString());
//        Response response = request.get("/api/v1/web/play/page");
//        ResponseBody body = response.getBody();
//        logger.debug("Status received => "+ response.getStatusLine() + "Status code is: " + response.statusCode());
//        logger.debug(body.asPrettyString());

        String bearerToken = "eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCIsImtpZCI6Ik5UYzRSRUpDTkVRNE5FRXpRalJDT1VOQ1JUZEVSVFF6UXpreVJqbEZNRVU1UlRFNU9EYzVOZyJ9.eyJpc3MiOiJodHRwczovL2F1dGgtc3RhZ2UudHZuei5jby5uei8iLCJzdWIiOiJhdXRoMHw2Mjc4OGU2YjZlNjdmYjAwNjgyMDIxY2IiLCJhdWQiOlsidHZuei1hcGlzLXN0YWdlIiwiaHR0cHM6Ly90dm56LXN0YWdlLmF1LmF1dGgwLmNvbS91c2VyaW5mbyJdLCJpYXQiOjE2Njk2NjgzNTMsImV4cCI6MTY2OTc1NDc1MywiYXpwIjoiWVMwbjZ3MlZxa0NGV3hzaGhFM3d5S2dwN3p0dlRlWEciLCJzY29wZSI6Im9wZW5pZCBwcm9maWxlIGVtYWlsIn0.R7wxp6FdtM8MWW9PDBEgsw1X28uoCY2uDi1R94-gfPYkR9stgxQ7xTTwdb0NZgmQLkH0P2QHClY_mmSNQ_KwJMRW6WwspZiAYT2FmctYMgqN6ekF3NNWh4ZW7j-9t_5vPo520ftNfZowyMUKIQsCXEj7DwGFeycoEipVqFK6A0-aVrPnM9Me3GzVIURReOFqrLKpRVsUVIe8RgTJEFfKkREXwzUUVjl1BP2pEq_qKpajwd8TyaESRQJFbzoMeDFcCna-z9hywzzCFcW8PuUhiEvytBayrxl0lHy41FTbef3w_Oft8bLfy5EGlPuKD7ZiyMgFAND3xB8XKwTWBUUByA";
        RestAssured.baseURI = "https://apis-public-stage.tech-preprod.tvnz.co.nz";
        Map<String, String> headerMap = new HashMap<>();
        headerMap.put("Content-Type", "application/json");

        RequestSpecification request = RestAssured.given().headers(headerMap);
        //RequestSpecification request = RestAssured.given();
        JSONObject reqParams = new JSONObject();
        reqParams.put("email", "k10@grr.la");
        reqParams.put("password", "11111111");
        //reqParams.put("job", "tester");
        request.body(reqParams.toString());
        Response response = request.post("/api/v1/web/consumer/login");
        ResponseBody body = response.getBody();
        logger.debug("Status received => "+ response.getStatusLine());
        logger.debug("Status code is: " + response.statusCode());
        logger.debug(body.asPrettyString());
    }


    @Test
    public void getUsers() {
        RestAssured.baseURI = "https://reqres.in";
        RequestSpecification request = RestAssured.given();
        request.queryParam("page", "2");
        Response response = request.get("/api/users");
        Users users = response.getBody().as(Users.class);
        users.getData().get(1);
        logger.debug("Example users => " + users.getData().get(1).getFirstName());
        for (User user : users.getData()) {
            System.out.println(user.getFirstName());
            Assertions.assertNotNull(user.getId());
        }
    }

    @Test
    public void tvnzSwitchProfile(){
        RestAssured.baseURI = "https://apis-public-stage.tech-preprod.tvnz.co.nz";
        Map<String, String> headerMap = new HashMap<>();
        headerMap.put("Content-Type", "application/json");
        //headerMap.put("sec-ch-ua"," Not A;Brand";v="99", "Chromium";v="96", "Google Chrome";v="96");

    }
}

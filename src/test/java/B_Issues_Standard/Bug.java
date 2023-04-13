package B_Issues_Standard;

import io.restassured.*;
import io.restassured.http.*;
import io.restassured.response.*;
import org.json.*;
import org.json.simple.parser.*;
import org.testng.*;
import org.testng.annotations.*;

import java.io.*;
import java.util.*;

import static io.restassured.RestAssured.*;

/**
 * @author Nandkumar Babar
 */
public class Bug {

    // Here we will update the values in IssueRequestBody.json file for the Bug

    public static String cookie;
    public static String issueId;

    @Test(priority = 1)

    public void generateCookie() throws Exception {
        RestAssured.baseURI = "http://localhost:8008";

        FileReader fr =new FileReader("src/main/java/inputJson/loginRequestBody.json");
        JSONParser jp =new JSONParser();
        String requestBody = jp.parse(fr).toString();

        Response response = given().body(requestBody)
                .contentType(ContentType.JSON).when().post("/rest/auth/1/session")
                .then().extract().response();

        System.out.println(response.getStatusCode()); //print the status code
        System.out.println(response.asString());     //Print the response

        Assert.assertEquals(200, response.statusCode());

        JSONObject js = new JSONObject(response.asString());
        String value = js.getJSONObject("session").get("value").toString();
        cookie = "JSESSIONID=" + value;
        System.out.println(cookie);
        System.out.println("Cookie is generated successfully....!!");
    }

    @Test(priority = 2)
    public void createBug() throws IOException, ParseException {
        RestAssured.baseURI = "http://localhost:8008";

        FileReader fr =new FileReader("src/main/java/inputJson/issueRequestBody.json");
        JSONParser jp =new JSONParser();
        String requestBody = jp.parse(fr).toString();

        JSONObject jsUpdate = new JSONObject(requestBody);
        jsUpdate.getJSONObject("fields").getJSONObject("issuetype").put("name","Bug");
        jsUpdate.getJSONObject("fields").put("summary","I am Adding the summary for bug");

        Response response = given().contentType(ContentType.JSON).header("Cookie", cookie)
                .body(jsUpdate.toString())
                .when().post("/rest/api/2/issue")
                .then().extract().response();

        System.out.println(response.getStatusCode());
        System.out.println(response.asString());

        JSONObject js =new JSONObject(response.asString());
           issueId = js.get("key").toString();
        System.out.println(issueId);
    }

    @Test(priority = 3)
    public void getBug(){
        RestAssured.baseURI = "http://localhost:8008";

        HashMap queryParameter=new HashMap();
        queryParameter.put("fields","status");
        queryParameter.put("fields","priority");
        queryParameter.put("fields","reporter");

        Response response = given().contentType(ContentType.JSON).header("Cookie", cookie)
                .queryParams(queryParameter)
                .when().get("/rest/api/2/issue/"+issueId+"").then().extract().response();

        System.out.println(response.getStatusCode());
        System.out.println(response.asString());

        Assert.assertEquals(response.statusCode(),200);
    }

    @Test(priority = 4)
    public void updateBug() throws IOException, ParseException {
        RestAssured.baseURI = "http://localhost:8008";

        FileReader fr =new FileReader("src/main/java/inputJson/issueRequestBody.json");
        JSONParser jp =new JSONParser();
        String requestBody = jp.parse(fr).toString();

        JSONObject jsUpdate = new JSONObject(requestBody);
        jsUpdate.getJSONObject("fields").getJSONObject("issuetype").put("name","Bug");
        jsUpdate.getJSONObject("fields").put("summary","I am updating the summary for bug");

        Response response = given().body(requestBody).contentType(ContentType.JSON)
                .header("cookie", cookie)
                .when().put("/rest/api/2/issue/" + issueId + "")
                .then().extract().response();

        System.out.println(response.getStatusCode());
        System.out.println(response.asString());
    }
    @Test(priority = 5)
    public void deleteBug(){
        RestAssured.baseURI = "http://localhost:8008";

        Response response = given().header("cookie", cookie).contentType(ContentType.JSON)
                .when().delete("/rest/api/2/issue/" + issueId + "")
                .then().extract().response();

        System.out.println(response.getStatusCode());
        System.out.println(response.asString());

        Assert.assertEquals(response.statusCode(),204);

    }
}

package C_Issues_Advanced;

import Utility.*;
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
public class Story {

    // Here we are using by default values from IssueRequestBody.json file for the story

    public static String issueId;

    @Test(priority = 1)

    public void createStory() throws IOException, ParseException {
        RestAssured.baseURI = ReadPropFile.readConfigFile("URL");

        String requestBody = ReadJsonFile.readJsonFile("src/main/java/inputJson/issueRequestBody.json");

        Response response = given().contentType(ContentType.JSON).header("Cookie", GenerateCookie.cookie())
                .body(requestBody)
                .when().post("/rest/api/2/issue")
                .then().extract().response();

        System.out.println(response.getStatusCode());
        System.out.println(response.asString());

        JSONObject js =new JSONObject(response.asString());

        issueId = js.get("key").toString();
        System.out.println(issueId);
    }

    @Test(priority = 2)
    public void getStory() throws IOException, ParseException {
        RestAssured.baseURI = ReadPropFile.readConfigFile("URL");

        HashMap queryParameter=new HashMap();
        queryParameter.put("fields","status");
        queryParameter.put("fields","priority");
        queryParameter.put("fields","reporter");

        Response response = given().contentType(ContentType.JSON).header("Cookie", GenerateCookie.cookie())
                .queryParams(queryParameter)
                .when().get("/rest/api/2/issue/"+issueId+"").then().extract().response();

        System.out.println(response.getStatusCode());
        System.out.println(response.asString());

        Assert.assertEquals(response.statusCode(),200);
    }

    @Test(priority = 3)
    public void updateStory() throws IOException, ParseException {
        RestAssured.baseURI = ReadPropFile.readConfigFile("URL");

        String requestBody = ReadJsonFile.readJsonFile("src/main/java/inputJson/issueRequestBody.json");

        Response response = given().body(requestBody).contentType(ContentType.JSON)
                .header("cookie", GenerateCookie.cookie())
                .when().put("/rest/api/2/issue/" + issueId + "")
                .then().extract().response();

        System.out.println(response.getStatusCode());
        System.out.println(response.asString());
    }
    @Test(priority = 4)
    public void deleteStory() throws IOException, ParseException {
        RestAssured.baseURI = ReadPropFile.readConfigFile("URL");

        Response response = given().header("cookie", GenerateCookie.cookie()).contentType(ContentType.JSON)
                .when().delete("/rest/api/2/issue/" + issueId + "")
                .then().extract().response();

        System.out.println(response.getStatusCode());
        System.out.println(response.asString());

        Assert.assertEquals(response.statusCode(),204);

    }
}

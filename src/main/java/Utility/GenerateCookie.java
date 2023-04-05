package Utility;

import io.restassured.*;
import io.restassured.http.*;
import io.restassured.response.*;
import org.json.*;
import org.json.simple.parser.*;

import java.io.*;

import static Utility.ReadPropFile.readConfigFile;
import static io.restassured.RestAssured.*;
/**
 * @author Nandkumar Babar
 */
public class GenerateCookie {

    public static String cookie() throws IOException, ParseException {
        RestAssured.baseURI = readConfigFile("URL");

        String requestBody = ReadJsonFile.readJsonFile("src/main/java/inputJson/loginRequestBody.json");

        JSONObject jsUpdate=new JSONObject(requestBody);
        jsUpdate.put("username",ReadPropFile.readConfigFile("username"));
        jsUpdate.put("password",ReadPropFile.readConfigFile("password"));

        Response response = given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when().post("/rest/auth/1/session")
                .then().extract().response();

        JSONObject js =new JSONObject(response.asString());
       String  cookie = js.getJSONObject("session").get("value").toString();
        System.out.println("Cookie is generated successfully....!!");
        return "JSESSIONID="+cookie;
    }
}

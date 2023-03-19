package Utility;

import io.restassured.*;
import io.restassured.http.*;
import io.restassured.response.*;
import org.json.*;

import java.io.*;

import static Utility.ReadPropFile.readConfigFile;
import static io.restassured.RestAssured.*;
/**
 * @author Nandkumar Babar
 */
public class LoginJIRA {

    public static String loginToJira() throws IOException {
        RestAssured.baseURI = readConfigFile("URL");

        Response response = given()
                .contentType(ContentType.JSON)
                .body("{\n" +
                        "    \"username\": \""+readConfigFile("username")+"\",\n" +
                        "    \"password\": \""+readConfigFile("password")+"\"\n" +
                        "}")

                .when().post("/rest/auth/1/session")
                .then().extract().response();

        JSONObject js =new JSONObject(response.asString().toString());
       String  cookie = js.getJSONObject("session").get("value").toString();
       return cookie;
    }
}

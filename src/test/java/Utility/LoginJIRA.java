package Utility;

import io.restassured.*;
import io.restassured.http.*;
import io.restassured.response.*;
import org.json.*;

import static io.restassured.RestAssured.*;
/**
 * @author Nandkumar Babar
 */
public class LoginJIRA {

    public static String loginToJira(){
        RestAssured.baseURI = "http://localhost:8008/";

        Response response = given()
                .contentType(ContentType.JSON)
                .body("{\n" +
                        "    \"username\": \"nandkumar\",\n" +
                        "    \"password\": \"nandkumar\"\n" +
                        "}")

                .when().post("/rest/auth/1/session")
                .then().extract().response();

        JSONObject js =new JSONObject(response.asString().toString());
       String  cookie = js.getJSONObject("session").get("value").toString();
       return cookie;
    }
}

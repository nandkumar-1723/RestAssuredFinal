package TESTS.AddAttachment;

import Utility.*;
import io.restassured.*;
import io.restassured.http.*;
import io.restassured.response.*;
import org.json.*;
import org.testng.annotations.*;

import static io.restassured.RestAssured.*;

/**
 * @author Nandkumar Babar
 */
public class A_CreateStory {

    public static String issueNumber;

    @Test()
    public void loginToJira(){
        RestAssured.baseURI = "http://localhost:8008/";

        Response response = given().contentType(ContentType.JSON)
                .header("Cookie", "JSESSIONID="+ LoginJIRA.loginToJira()+"")

                .body("{\n" +
                        "   \"fields\": {\n" +
                        "       \"project\": {\n" +
                        "           \"key\": \"AM\"\n" +
                        "       },\n" +
                        "       \"summary\": \"Login related\",\n" +
                        "       \"issuetype\": {\n" +
                        "           \"name\": \"Story\"\n" +
                        "       }\n" +
                        "}\n" +
                        "}")
                .when().post("/rest/api/2/issue").
                then().extract().response();

        System.out.println(response.getStatusCode());

        System.out.println(response.asString());

        JSONObject js=new JSONObject(response.asString());

        issueNumber = js.get("key").toString();

    }
}

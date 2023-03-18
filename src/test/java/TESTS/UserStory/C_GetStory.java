package TESTS.UserStory;

import Utility.*;
import io.restassured.*;
import io.restassured.http.*;
import io.restassured.response.*;
import org.testng.annotations.*;

import java.util.*;

import static TESTS.AddAttachment.A_CreateStory.*;
import static io.restassured.RestAssured.*;

/**
 * @author Nandkumar Babar
 */
public class C_GetStory {

    @Test()
    public void getUserStory(){

        RestAssured.baseURI = "http://localhost:8008/";

        LinkedHashMap<String, String> queryParameter = new LinkedHashMap<String,String>();
        queryParameter.put("fields", "priority");

        Response response = given().contentType(ContentType.JSON)
                .header("Cookie", "JSESSIONID="+ LoginJIRA.loginToJira()+"")
                .queryParams(queryParameter)
                .when().get("/rest/api/2/issue/"+issueNumber+"").
                then().extract().response();

        System.out.println(response.getStatusCode());

        System.out.println(response.asString());

    }
}

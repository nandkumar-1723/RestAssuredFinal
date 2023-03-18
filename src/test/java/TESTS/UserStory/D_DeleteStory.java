package TESTS.UserStory;

import Utility.*;
import io.restassured.*;
import io.restassured.http.*;
import io.restassured.response.*;
import org.testng.annotations.*;

import static TESTS.AddAttachment.A_CreateStory.*;
import static io.restassured.RestAssured.*;

/**
 * @author Nandkumar Babar
 */
public class D_DeleteStory {

    @Test()
    public void deleteUserStory(){
        RestAssured.baseURI = "http://localhost:8008/";

        Response response = given().contentType(ContentType.JSON)
                .header("Cookie", "JSESSIONID="+ LoginJIRA.loginToJira()+"")
                .when().delete("/rest/api/2/issue/"+issueNumber+"").
                then().extract().response();

        System.out.println(response.getStatusCode());

        System.out.println(response.asString());

    }
}

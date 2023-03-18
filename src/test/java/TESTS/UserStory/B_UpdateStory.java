package TESTS.UserStory;

import Utility.*;
import io.restassured.*;
import io.restassured.http.*;
import io.restassured.response.*;
import org.testng.annotations.*;

import static TESTS.UserStory.A_CreateStory.issueNumber;
import static io.restassured.RestAssured.*;

/**
 * @author Nandkumar Babar
 */
public class B_UpdateStory {

    @Test()
    public void updateUserStory(){
        RestAssured.baseURI = "http://localhost:8008/";

        Response response = given().contentType(ContentType.JSON)
                .header("Cookie", "JSESSIONID="+ LoginJIRA.loginToJira()+"")

                .body("{\n" +
                        "   \"fields\": {\n" +
                        "       \"project\": {\n" +
                        "           \"key\": \"AM\"\n" +
                        "       },\n" +
                        "       \"summary\": \"I am updating\",\n" +
                        "       \"issuetype\": {\n" +
                        "           \"name\": \"Story\"\n" +
                        "       }\n" +
                        "}\n" +
                        "}\n")

                .when().put("/rest/api/2/issue/"+issueNumber+"").
                then().extract().response();

        System.out.println(response.getStatusCode());

        System.out.println(response.asString());

    }
}

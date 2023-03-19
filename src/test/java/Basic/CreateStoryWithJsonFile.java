package Basic;

import Utility.*;
import io.restassured.*;
import io.restassured.http.*;
import io.restassured.response.*;
import org.testng.annotations.*;

import static Utility.ReadPropFile.*;
import static io.restassured.RestAssured.*;

/**
 * @author Nandkumar Babar
 */
public class CreateStoryWithJsonFile {

        @Test(priority = 1)
        public void loginToJira() throws Exception {
            RestAssured.baseURI = readConfigFile("URL");
            String body=  ReadJsonFile.readJsonFile("story");

            Response response = given().contentType(ContentType.JSON)
                    .header("Cookie", "JSESSIONID="+ LoginJIRA.loginToJira()+"")
                    .body(body)
                    .when().post("/rest/api/2/issue").
                    then().extract().response();

            System.out.println(response.getStatusCode());
            System.out.println(response.asString());

            if (response.getStatusCode()!=201){
                throw new Exception("expected status code was 201 but found "+response.getStatusCode()+"");
            }

        }

}

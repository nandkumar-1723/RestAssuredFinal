package Basic;

import io.restassured.*;
import io.restassured.http.*;
import io.restassured.response.*;
import org.json.*;
import org.testng.annotations.*;

import static Utility.ReadPropFile.readConfigFile;
import static io.restassured.RestAssured.given;

/**
 * @author Nandkumar Babar
 */
public class CreateStory {
        public static String issueNumber;

        @Test(priority = 1)
        public void loginToJira() throws Exception {
            RestAssured.baseURI = readConfigFile("URL");

            Response response = given().contentType(ContentType.JSON)
                    .header("Cookie", "JSESSIONID=")

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

            if (response.getStatusCode()!=201){
                throw new Exception("expected status code was 201 but found "+response.getStatusCode()+"");
            }
        }

}

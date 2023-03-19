package Tests;

import Utility.*;
import io.restassured.*;
import io.restassured.http.*;
import io.restassured.response.*;
import org.json.*;
import org.testng.annotations.*;

import java.util.*;

import static Utility.ReadPropFile.readConfigFile;
import static io.restassured.RestAssured.*;

/**
 * @author Nandkumar Babar
 */
public class Comment {

    public static String issueNumber;

    @Test(priority = 1)
    public static void addUserStory() throws Exception {
        RestAssured.baseURI = readConfigFile("URL");

        Response response = given().contentType(ContentType.JSON)
                .header("Cookie", "JSESSIONID="+ LoginJIRA.loginToJira()+"")

                .body("{\n" +
                        "   \"fields\": {\n" +
                        "       \"project\": {\n" +
                        "           \"key\": \"AM\"\n" +
                        "       },\n" +
                        "       \"summary\": \"Login related\",\n" +
                        "       \"issuetype\": {\n" +
                        "           \"name\": \"Bug\"\n" +
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

    @Test(priority = 2)
    public void postComment() throws Exception {
        RestAssured.baseURI = readConfigFile("URL");

        Response response = given().contentType(ContentType.JSON)
                .header("Cookie", "JSESSIONID="+ LoginJIRA.loginToJira()+"")

                .body("{\n" +
                        "   \"body\": \"adding a comment\"\n" +
                        "}\n")

                .when().post("/rest/api/2/issue/"+issueNumber+"/comment").
                then().extract().response();

        System.out.println(response.getStatusCode());
        System.out.println(response.asString());

        if (response.getStatusCode()!=201){
            throw new Exception("expected status code was 201 but found "+response.getStatusCode()+"");
        }

    }
    @Test(priority = 3)
    public void getComment() throws Exception {

        RestAssured.baseURI = readConfigFile("URL");

        LinkedHashMap<String, String> queryParameter = new LinkedHashMap<String,String>();
        queryParameter.put("fields", "comment");

        Response response = given().contentType(ContentType.JSON)
                .header("Cookie", "JSESSIONID="+ LoginJIRA.loginToJira()+"")
                .queryParams(queryParameter)
                .when().get("/rest/api/2/issue/"+issueNumber+"").
                then().extract().response();

        System.out.println(response.getStatusCode());
        System.out.println(response.asString());

        if (response.getStatusCode()!=200){
            throw new Exception("expected status code was 200 but found "+response.getStatusCode()+"");
        }

    }
}

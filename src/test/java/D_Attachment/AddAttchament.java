package D_Attachment;

import Utility.*;
import io.restassured.*;
import io.restassured.http.*;
import io.restassured.response.*;
import org.json.*;
import org.json.simple.parser.*;
import org.testng.annotations.*;

import java.io.*;
import java.util.*;

import static Utility.ReadPropFile.readConfigFile;
import static io.restassured.RestAssured.*;

/**
 * @author Nandkumar Babar
 */
public class AddAttchament {
    public static String issueId;

    @Test(priority = 1)
    public void createStory() throws IOException, ParseException {
        RestAssured.baseURI = ReadPropFile.readConfigFile("URL");

        String requestBody = ReadJsonFile.readJsonFile("src/main/java/inputJson/issueRequestBody.json");

        Response response = given().contentType(ContentType.JSON).header("Cookie", GenerateCookie.cookie())
                .body(requestBody)
                .when().post("/rest/api/2/issue")
                .then().extract().response();

        System.out.println(response.getStatusCode());
        System.out.println(response.asString());

        JSONObject js =new JSONObject(response.asString());
        issueId = js.get("key").toString();
        System.out.println(issueId);
    }

    @Test(priority = 2)
    public void addAttachment() throws Exception {
        RestAssured.baseURI = readConfigFile("URL");

        HashMap header = new HashMap();
        header.put("Cookie",GenerateCookie.cookie());
        header.put("X-Atlassian-Token","no-check");

        File file = new File("src/main/java/Documents/ariseLOGO.png");

        Response response = given().contentType(ContentType.MULTIPART)
                .headers(header).pathParam("issueId",issueId)
                .multiPart("file", file)
                .when().post("/rest/api/2/issue/{issueId}/attachments").
                then().extract().response();

        System.out.println(response.getStatusCode());
        System.out.println(response.asString());

        if (response.getStatusCode()!=200){
            throw new Exception("expected status code was 200 but found "+response.getStatusCode()+"");
        }
    }
}

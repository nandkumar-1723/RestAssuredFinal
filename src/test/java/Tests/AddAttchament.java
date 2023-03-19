package Tests;

import Utility.*;
import io.restassured.*;
import io.restassured.http.*;
import io.restassured.response.*;
import org.json.*;
import org.testng.annotations.*;

import java.io.*;
import java.util.*;

import static Utility.ReadPropFile.readConfigFile;
import static io.restassured.RestAssured.*;

/**
 * @author Nandkumar Babar
 */
public class AddAttchament {
    public static String issueNumber;

    @Test(priority = 1)
    public void loginToJira() throws Exception {
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

    @Test(priority = 2)
    public void addAttachment() throws Exception {
        RestAssured.baseURI = readConfigFile("URL");

        LinkedHashMap header = new LinkedHashMap();
        header.put("Cookie", "JSESSIONID="+ LoginJIRA.loginToJira()+"");
        header.put("X-Atlassian-Token","no-check");

        File file = new File("ariseLOGO.png");

        Response response = given().contentType(ContentType.MULTIPART)
                .headers(header)
                .multiPart("file", file)
                .when().post("/rest/api/2/issue/"+issueNumber+"/attachments").
                then().extract().response();

        System.out.println(response.getStatusCode());
        System.out.println(response.asString());

        if (response.getStatusCode()!=200){
            throw new Exception("expected status code was 200 but found "+response.getStatusCode()+"");
        }
    }
}
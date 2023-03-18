package TESTS.AddAttachment;

import Utility.*;
import io.restassured.*;
import io.restassured.http.*;
import io.restassured.response.*;
import org.testng.annotations.*;

import java.io.*;
import java.util.*;

import static TESTS.AddAttachment.A_CreateStory.issueNumber;
import static io.restassured.RestAssured.*;

/**
 * @author Nandkumar Babar
 */
public class AddAttchament {

    @Test()
    public void addAttachment(){

        RestAssured.baseURI = "http://localhost:8008/";

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
    }
}

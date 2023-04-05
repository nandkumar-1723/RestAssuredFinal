package Issues_Basic;
import io.restassured.*;
import io.restassured.http.*;
import io.restassured.response.*;
import org.json.*;
import org.testng.*;
import org.testng.annotations.*;

import static io.restassured.RestAssured.*;

public class UserStory {

    public static String cookie;
    public static String issueId;

    @Test(priority = 1)
    public void generateCookie() throws Exception {
        RestAssured.baseURI = "http://localhost:8008";

        Response response = given().body("{\n" + "     \"username\": \"Project_Manager\",\n" + " " +
                        "     \"password\": \"nandkumar\" \n" + " }")
                .contentType(ContentType.JSON).when().post("/rest/auth/1/session")
                .then().extract().response();

        System.out.println(response.getStatusCode()); //print the status code
        System.out.println(response.asString());     //Print the response

        Assert.assertEquals(200, response.statusCode());

        JSONObject js = new JSONObject(response.asString());
        String value = js.getJSONObject("session").get("value").toString();
        cookie = "JSESSIONID=" + value;
        System.out.println(cookie);

        System.out.println("Cookie is generated successfully....!!");
    }

    @Test(priority = 2)
    public void createuserStory() {
        RestAssured.baseURI = "http://localhost:8008";
        Response response = given().body("{\n" + "   \"fields\": {\n" + "   " +
                        "    \"project\": {\n" + "           \"key\": \"AM\"\n" + "       },\n" + "      " +
                        " \"summary\": \"create an user story\",\n" + "       \"issuetype\": {\n" + "      " +
                        "     \"name\": \"Story\"\n" + "       }\n" + "}\n" + "}")
                .contentType(ContentType.JSON).header("Cookie", cookie).when()
                .post("/rest/api/2/issue")
                .then().extract().response();

        System.out.println(response.getStatusCode());  // print the statuc code
        System.out.println(response.asString());       // print the response

        Assert.assertEquals(201, response.statusCode()); // Assertion

        JSONObject js = new JSONObject(response.asString());
        issueId = js.get("key").toString(); // will get the issueId
        System.out.println(issueId);
    }

    @Test(priority = 3)
    public void getUserStory(){
        RestAssured.baseURI = "http://localhost:8008";

        Response response = given().contentType(ContentType.JSON).header("Cookie", cookie)
                .queryParam("fields","status")
                .when().get("/rest/api/2/issue/"+issueId+"").then().extract().response();

        System.out.println(response.getStatusCode());
        System.out.println(response.asString());

        Assert.assertEquals(response.statusCode(),200);
    }

    @Test(priority = 4)
    public void updateUserStory(){
        RestAssured.baseURI = "http://localhost:8008";

        Response response = given().body("{\n" +
                        "   \"fields\": {\n" +
                        "       \"project\": {\n" +
                        "           \"key\": \"AM\"\n" +
                        "       },\n" +
                        "       \"summary\": \"I am updatin the user story: BASIC\",\n" +
                        "       \"issuetype\": {\n" +
                        "           \"name\": \"Story\"\n" +
                        "       }\n" +
                        "}\n" +
                        "}\n")
                .header("cookie", cookie).contentType(ContentType.JSON)
                .when().put("/rest/api/2/issue/"+issueId+"")
                .then().extract().response();

        System.out.println(response.getStatusCode());
        System.out.println(response.asString());

        Assert.assertEquals(response.statusCode(),204);
    }

    @Test(priority = 5)
    public void deleteUserStory(){

        RestAssured.baseURI = "http://localhost:8008";

        Response response = given().header("cookie", cookie).contentType(ContentType.JSON)
                .when().delete("/rest/api/2/issue/" + issueId + "")
                .then().extract().response();

        System.out.println(response.getStatusCode());
        System.out.println(response.asString());

        Assert.assertEquals(response.statusCode(),204);

    }

}
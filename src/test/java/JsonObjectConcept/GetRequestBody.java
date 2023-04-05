package JsonObjectConcept;

import org.json.*;
import org.json.simple.parser.*;

import java.io.*;

/**
 * @author Nandkumar Babar
 */
public class GetRequestBody {

    public static void main(String[] args) throws IOException, ParseException {

        FileReader fr = new FileReader("src/main/java/inputJson/practiceRequestBody.json");
        JSONParser jp = new JSONParser();
        String requestBody = jp.parse(fr).toString();

        JSONObject js =new JSONObject(requestBody);

        //Get 1st students: second mock marks
        String marks = js.getJSONArray("students").getJSONObject(0).getJSONArray("marks").getJSONObject(1).get("secondMock").toString();
        System.out.println(marks);

        //Get 2nd students: second mock marks
        String secondStudentsMarks = js.getJSONArray("students").getJSONObject(1).getJSONArray("marks").getJSONObject(1).get("secondMock").toString();
        System.out.println(secondStudentsMarks);

    }
}

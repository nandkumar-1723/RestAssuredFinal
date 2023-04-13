package F_JsonObjectConcept;

import Utility.ReadJsonFile;
import org.json.*;
import org.json.simple.parser.*;

import java.io.*;

/**
 * @author Nandkumar Babar
 */
public class updateRequestBody {
    public static void main(String[] args) throws IOException, ParseException {

         String requestBody = ReadJsonFile.readJsonFile("src/main/java/inputJson/practiceRequestBody.json");

        JSONObject js =new JSONObject(requestBody);

        //Update 1st students: second mock marks
         js.getJSONArray("students").getJSONObject(0).getJSONArray("marks").getJSONObject(1).put("secondMock",12);
        System.out.println(js);

    }
}

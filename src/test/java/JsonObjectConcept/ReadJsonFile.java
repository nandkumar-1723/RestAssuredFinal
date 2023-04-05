package JsonObjectConcept;

import org.json.*;
import org.json.simple.parser.*;

import java.io.*;

/**
 * @author Nandkumar Babar
 */
public class ReadJsonFile {

    public static void main(String[] args) throws IOException, ParseException {

        FileReader fr = new FileReader("src/main/java/com/jira/InputJson/practice.json");
        JSONParser jp = new JSONParser();
        String requestBody = jp.parse(fr).toString();

        System.out.println(requestBody);
    }
}

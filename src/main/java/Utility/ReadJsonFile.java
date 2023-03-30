package Utility;

import org.json.simple.parser.*;

import java.io.*;

/**
 * @author Nandkumar Babar
 */
public class ReadJsonFile {

public static String readJsonFile(String jsonFileName) throws IOException, ParseException {
        JSONParser parser = new JSONParser();
        FileReader file=new FileReader("src/test/resources/inputJson/"+jsonFileName+".json");

        return parser.parse(file).toString();
    }
}

package Utility;

import org.json.simple.parser.*;

import java.io.*;

/**
 * @author Nandkumar Babar
 */
public class ReadJsonFile {

public static String readJsonFile(String jsonFilePath) throws IOException, ParseException {
    FileReader file=new FileReader(jsonFilePath);
    JSONParser parser = new JSONParser();
    return parser.parse(file).toString();
    }
}

package Utility;

import java.io.*;
import java.util.*;

/**
 * @author Nandkumar Babar
 */
public class ReadFile {

    public static String readConfigFile(String value) throws IOException {

        FileReader reader=new FileReader( "src/main/resources/Global.properties");
        Properties props = new Properties();
        props.load(reader);
        return props.getProperty(value);

    }
}

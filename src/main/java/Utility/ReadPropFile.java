package Utility;

import java.io.*;
import java.util.*;

/**
 * @author Nandkumar Babar
 */
public class ReadPropFile {

    public static String readConfigFile(String value) throws IOException {

        FileReader reader=new FileReader( "src/main/java/Utility/global.properties");
        Properties props = new Properties();
        props.load(reader);
        return props.getProperty(value);

    }
}

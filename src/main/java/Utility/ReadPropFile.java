package Utility;

import java.io.*;
import java.util.*;

/**
 * @author Nandkumar Babar
 */
public class ReadPropFile {

    public static String readConfigFile(String key) throws IOException {

        FileReader fr =new FileReader( "src/main/java/Utility/global.properties");
        Properties pr = new Properties();
        pr.load(fr);
        return pr.getProperty(key);
    }
}

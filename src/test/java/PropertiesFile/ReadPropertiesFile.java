package PropertiesFile;

import java.io.*;
import java.util.*;

/**
 * @author Nandkumar Babar
 */
public class ReadPropertiesFile {

    public static void main(String[] args) throws IOException {

        FileReader fr =new FileReader("src/main/java/Utility/global.properties");
        Properties pr =new Properties();
        pr.load(fr);
        String username = pr.getProperty("username");
        System.out.println(username);
    }
}

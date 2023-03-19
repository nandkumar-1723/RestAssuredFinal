package JsonObjectConcept;

import Utility.*;
import org.testng.annotations.*;

/**
 * @author Nandkumar Babar
 */
public class JsonObject {

        @Test(priority = 1)
        public void loginToJira() throws Exception {

            String body=  ReadJsonFile.readJsonFile("practiceRequestBody");
            System.out.println(body);

        }
}

package F_JsonObjectConcept;

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

        //third student - 3 mock status
        String thirdStudentThirdMockStatus = js.getJSONArray("students").getJSONObject(2).getJSONArray("marks").getJSONObject(2).get("status").toString();
        System.out.println(thirdStudentThirdMockStatus);

        //Get the length of stundets
        int studentList = js.getJSONArray("students").length();
        System.out.println(studentList);

        // find value of student id when we dont know the index
        for (int i = 0; i <= js.getJSONArray("students").length(); i++) {
            String students = js.getJSONArray("students").getJSONObject(i).get("name").toString();
            if (students.equals("Student-2")){
                String id = js.getJSONArray("students").getJSONObject(i).get("id").toString();
                System.out.println(id);
                break;
            }
        }
    }
}

package ReadExcell;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;

import java.io.*;

/**
 * @author Nandkumar Babar
 */
public class readexcell {
    public static void main(String[] args) throws IOException {

        File file = new File("src/test/java/com/jira/ReadExcell/TestCasesNew.xls");   //creating a new file instance
        FileInputStream fis = new FileInputStream(file);   //obtaining bytes from the file

        XSSFWorkbook wb = new XSSFWorkbook(fis);
        XSSFSheet sheet = wb.getSheetAt(0);
        String row=sheet.getRow(1).getCell(2).getStringCellValue(); //returns the logical row
        System.out.println(row);

            System.out.println(sheet.getLastRowNum());

        System.out.println(sheet.getRow(1).getLastCellNum());


    }
}

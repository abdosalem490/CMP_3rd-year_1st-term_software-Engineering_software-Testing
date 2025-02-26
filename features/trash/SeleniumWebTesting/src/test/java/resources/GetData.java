package resources;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.*;

import static constants.TestConstants.BROWSER;

/**
 * this is the class that's responsible for getting the data from anywhere
 */
public class GetData {


    /**
     * this is a function to get the data from an excel file to make a testcase
     * @param filePath: this is the location where the excel file is located
     * @param excelSheetName: this is the sheetName in the excel workbook that contains the data for the test cases
     * @return List<Map<String, String>>: this is a list of testcases to be run on a specific function
     * @throws IOException: if the file isn't found
     */
    public List<Map<String, String>> getExcelData(String filePath, String excelSheetName) throws IOException {
        DataFormatter formatter = new DataFormatter();
        List<Map<String, String>> data = new ArrayList<>();
        List<String> titles = new ArrayList<>();
        FileInputStream fis = new FileInputStream(filePath);
        XSSFWorkbook excelWorkBook = new XSSFWorkbook(fis);
        XSSFSheet excelSheet = excelWorkBook.getSheet(excelSheetName);

        XSSFRow excelRow = excelSheet.getRow(0);
        int rowCount = excelSheet.getPhysicalNumberOfRows();
        int columnCount = excelRow.getLastCellNum();

        for (int i = 0; i < columnCount; i++){
            Cell cell = excelRow.getCell(i);
            titles.add(formatter.formatCellValue(cell));
        }

        for (int i = 1; i < rowCount; i++){
            excelRow = excelSheet.getRow(i);
            Map<String, String> map = new HashMap<>();
            for (int j = 0; j < columnCount; j++){
                Cell cell = excelRow.getCell(j);
                String celLContent = formatter.formatCellValue(cell);
                map.put(titles.get(j), celLContent);
            }
            data.add(map);
        }

        return data;
    }

    /**
     * this is a function that will get the name of the browser through which we will run the scripts
     * @param path: this is where the path of .properites file is defined
     * @return String: it returns the name of the browser to be used
     * @throws IOException: if the file of .properties isn't found
     */
    public String getBrowserName(String path) throws IOException {
        Properties properties = new Properties();
        FileInputStream fileInputStream = new FileInputStream(path);
        properties.load(fileInputStream);
        return System.getProperty(BROWSER) != null ? System.getProperty(BROWSER) : properties.getProperty(BROWSER);
    }

    /**
     * this function is used to insert a new row in the excel sheet which is used in tests
     * @param data: this is the data to be inserted
     * @param filePath: this is the file path of the excel sheet
     * @param excelSheetName: this is the name of the excel sheet name
     */
    public void insertRecordIntoExcelSheet(List<String> data, String filePath, String excelSheetName){
        try {

            FileInputStream fis = new FileInputStream(filePath);
            XSSFWorkbook excelWorkBook = new XSSFWorkbook(fis);
            XSSFSheet excelSheet = excelWorkBook.getSheet(excelSheetName);
            XSSFRow row;

            int rowCount = excelSheet.getLastRowNum();
            row = excelSheet.createRow(rowCount+1);

            for (int i = 0; i < data.size(); i++){
                Cell cell = row.createCell(i);
                cell.setCellValue(data.get(i));
            }
            fis.close();
            FileOutputStream fileOutputStream = new FileOutputStream(filePath);
            excelWorkBook.write(fileOutputStream);
            excelWorkBook.close();
            fileOutputStream.close();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * this function generates a radom string that can be used in many things, the first char in the string is alphabetic not numerical
     * @param length: this is the length of the generated string
     * @return String: that random generated string
     */
    public String generateRandomString(int length){
        String tempRandom = RandomStringUtils.randomAlphanumeric(length);
        if (!Character.isAlphabetic(tempRandom.charAt(0)))
            return "a" + tempRandom.substring(1);
        else
            return tempRandom;
    }
}

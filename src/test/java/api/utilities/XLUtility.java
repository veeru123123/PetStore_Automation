
package api.utilities;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class XLUtility {

    String path;
    FileInputStream fi;
    FileOutputStream fo;
    XSSFWorkbook workbook;
    XSSFSheet sheet;
    XSSFRow row;
    XSSFCell cell;

    // Constructor
    public XLUtility(String path) {
        this.path = path;
    }

    // Get row count
    public int getRowCount(String sheetName) throws IOException {

        fi = new FileInputStream(path);

        workbook = new XSSFWorkbook(fi);

        sheet = workbook.getSheet(sheetName);

        int rowCount = sheet.getLastRowNum();

        workbook.close();
        fi.close();

        return rowCount;
    }

    // Get cell count
    public int getCellCount(String sheetName, int rowNum) throws IOException {

        fi = new FileInputStream(path);

        workbook = new XSSFWorkbook(fi);

        sheet = workbook.getSheet(sheetName);

        row = sheet.getRow(rowNum);

        int cellCount = row.getLastCellNum();

        workbook.close();
        fi.close();

        return cellCount;
    }

    // Get cell data
    public String getCellData(String sheetName, int rowNum, int colNum)
            throws IOException {

        fi = new FileInputStream(path);

        workbook = new XSSFWorkbook(fi);

        sheet = workbook.getSheet(sheetName);

        row = sheet.getRow(rowNum);

        cell = row.getCell(colNum);

        DataFormatter formatter = new DataFormatter();

        String data = formatter.formatCellValue(cell);

        workbook.close();
        fi.close();

        return data;
    }

    // Set cell data
    public void setCellData(String sheetName, int rowNum, int colNum, String data)
            throws IOException {

        fi = new FileInputStream(path);

        workbook = new XSSFWorkbook(fi);

        sheet = workbook.getSheet(sheetName);

        row = sheet.getRow(rowNum);

        cell = row.createCell(colNum);

        cell.setCellValue(data);

        fo = new FileOutputStream(path);

        workbook.write(fo);

        workbook.close();

        fi.close();
        fo.close();
    }
}

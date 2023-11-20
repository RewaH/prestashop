package dataProvider;
import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import DataHandlingTests.CustomObject;
import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class XLSXDataManager {

    private String filePath;

    public XLSXDataManager(String filePath) {
        this.filePath = filePath;
    }

    private Workbook readXLSXFile() {
        try {
            FileInputStream file = new FileInputStream(new File(filePath));
            return new XSSFWorkbook(file);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    ////////////////////////////////////////////////
    //get the values and pass it to test cases
    public Object[][] readDataFromExcel(String sheetName) {
        Object[][] data = null;
        try {
            FileInputStream file = new FileInputStream(new File(filePath));
            Workbook workbook = WorkbookFactory.create(file);
            Sheet sheet = workbook.getSheet(sheetName);

            int rowCount = sheet.getLastRowNum() - sheet.getFirstRowNum();
            int colCount = sheet.getRow(0).getLastCellNum();

            data = new Object[rowCount][colCount];

            for (int i = 1; i <= rowCount; i++) {
                Row row = sheet.getRow(i);
                for (int j = 0; j < colCount; j++) {
                    data[i - 1][j] = row.getCell(j).toString();
                }
            }
            file.close();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        return data;
    }
    /////////////////////////////////////////////////////

    public void retrieveXLSXData() {
        try {
            Workbook workbook = readXLSXFile();
            if (workbook != null) {
                Sheet sheet = workbook.getSheetAt(0);
                Iterator<Row> rows = sheet.iterator();
                while (rows.hasNext()) {
                    Row row = rows.next();
                    Iterator<Cell> cells = row.iterator();
                    while (cells.hasNext()) {
                        Cell cell = cells.next();
                        System.out.print(cell.toString() + "\t");
                    }
                    System.out.println();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public String retrieveSpecificCellValue(int rowNum, int colNum) {
        try {
            XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(filePath));
            XSSFSheet sheet = workbook.getSheetAt(0); // Assuming the data is in the first sheet

            XSSFRow row = sheet.getRow(rowNum - 1); // Adjust index (starting from 0) to row number (starting from 1)
            if (row != null) {
                XSSFCell cell = row.getCell(colNum - 1); // Adjust index (starting from 0) to column number (starting from 1)
                if (cell != null) {
                    switch (cell.getCellType()) {
                        case STRING:
                            return cell.getStringCellValue();
                        case NUMERIC:
                            if (DateUtil.isCellDateFormatted(cell)) {
                                return cell.getDateCellValue().toString();
                            } else {
                                return String.valueOf(cell.getNumericCellValue());
                            }
                        case BOOLEAN:
                            return String.valueOf(cell.getBooleanCellValue());
                        case BLANK:
                            return ""; // Return an empty string for blank cells
                        default:
                            return ""; // Handle other cell types as needed
                    }
                }
            }

            workbook.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    
    
    public XSSFRow retrieveSpecificRow(int rowNum) {
        try {
            XSSFWorkbook workbook = new XSSFWorkbook(new FileInputStream(filePath));
            XSSFSheet sheet = workbook.getSheetAt(0); // Assuming the data is in the first sheet

            return sheet.getRow(rowNum - 1); // Adjust index (starting from 0) to row number (starting from 1)

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

 
    public void addXLSXData(Object[][] dataToAdd) {
        try {
            Workbook workbook = readXLSXFile();
            if (workbook != null) {
                Sheet sheet = workbook.getSheetAt(0);
                int rowNum = sheet.getLastRowNum();
                for (Object[] rowData : dataToAdd) {
                    Row row = sheet.createRow(++rowNum);
                    int colNum = 0;
                    for (Object field : rowData) {
                        Cell cell = row.createCell(colNum++);
                        if (field instanceof String) {
                            cell.setCellValue((String) field);
                        } else if (field instanceof Double) {
                            cell.setCellValue((Double) field);
                        }
                    }
                }
                FileOutputStream outputStream = new FileOutputStream(filePath);
                workbook.write(outputStream);
                workbook.close();
                outputStream.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public boolean searchXLSXData(String searchText) {
        try {
            Workbook workbook = readXLSXFile();
            if (workbook != null) {
                Sheet sheet = workbook.getSheetAt(0);
                boolean found = false;

                for (Row row : sheet) {
                    for (Cell cell : row) {
                        if (cell.getCellType() == CellType.STRING && cell.getStringCellValue().equals(searchText)) {
                           found=true;
                        	return found;
                        }
                     else if (cell.getCellType() == CellType.NUMERIC) {
                         double numericValue = cell.getNumericCellValue();
                         if (String.valueOf(numericValue).equals(searchText)) {
                             found = true;

                            return true;
                        }
                    }
                }
                }
            }
        }
         catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean appendXLSXData(int rowN, Object[] dataToAppend) {
    	int rowNum =rowN-1;
        try {
            Workbook workbook = readXLSXFile();
            if (workbook != null) {
                Sheet sheet = workbook.getSheetAt(0);
                Row row = sheet.createRow(rowNum);
                for (int i = 0; i < dataToAppend.length; i++) {
                    Cell cell = row.createCell(i);
                    if (dataToAppend[i] instanceof String) {
                        cell.setCellValue((String) dataToAppend[i]);
                    } else if (dataToAppend[i] instanceof Double) {
                        cell.setCellValue((Double) dataToAppend[i]);
                    }
                }
                FileOutputStream outputStream = new FileOutputStream(filePath);
                workbook.write(outputStream);
                workbook.close();
                outputStream.close();
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public boolean reformatXLSXData(int rowNum, int colNum, Object newData) {
        try {
            Workbook workbook = readXLSXFile();
            if (workbook != null) {
                Sheet sheet = workbook.getSheetAt(0);
                Row row = sheet.getRow(rowNum);
                Cell cell = row.getCell(colNum);
                if (cell != null) {
                    if (newData instanceof String) {
                        cell.setCellValue((String) newData);
                    } else if (newData instanceof Double) {
                        cell.setCellValue((Double) newData);
                    }
                    FileOutputStream outputStream = new FileOutputStream(filePath);
                    workbook.write(outputStream);
                    workbook.close();
                    outputStream.close();
                    return true;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    // Parsing XLSX data into CustomObject
    public CustomObject parseXLSXData(int rowNum) {
        try {
            Workbook workbook = readXLSXFile();
            if (workbook != null) {
                Sheet sheet = workbook.getSheetAt(0);
                Row row = sheet.getRow(rowNum);
                if (row != null) {
                    String name = row.getCell(0).getStringCellValue();
                    int age = (int) row.getCell(1).getNumericCellValue();
                    String city = row.getCell(2).getStringCellValue();
                    return new CustomObject(name, age, city);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public List<CustomObject> parseAllRows() {
        List<CustomObject> customObjects = new ArrayList<>();

        try {
            Workbook workbook = readXLSXFile();
            if (workbook != null) {
                Sheet sheet = workbook.getSheetAt(0); // assuming the first sheet

                for (int i = sheet.getFirstRowNum() + 1; i <= sheet.getLastRowNum(); i++) {
                    Row row = sheet.getRow(i);
                    if (row != null) {
                        String name = row.getCell(0).getStringCellValue();
                        int age = (int) row.getCell(1).getNumericCellValue();
                        String city = row.getCell(2).getStringCellValue();
                        customObjects.add(new CustomObject(name, age, city));
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return customObjects;
    }

    public void insertDataIntoNewColumn(int rowIndex, String columnHeader, String columnData) {
        try {
            FileInputStream file = new FileInputStream(new File(filePath));
            Workbook workbook = WorkbookFactory.create(file);
            Sheet sheet = workbook.getSheetAt(0);

            // Inserting data into a new column (e.g., column index 3)
            int newColumnIndex = sheet.getRow(rowIndex).getLastCellNum(); // Get the next column index

            for (Row row : sheet) {
                Cell newCell = row.createCell(newColumnIndex, CellType.STRING);

                if (row.getRowNum() == 0) { // For the header row
                    newCell.setCellValue(columnHeader);
                } else if (row.getRowNum() == rowIndex) { // For the specified row
                    newCell.setCellValue(columnData);
                }
            }

            // Write changes back to the file
            FileOutputStream outFile = new FileOutputStream(new File(filePath));
            workbook.write(outFile);

            // Close resources
            outFile.close();
            file.close();

            System.out.println("Data inserted successfully into a new column for the specified row!");
        } catch (IOException | EncryptedDocumentException ex) {
            ex.printStackTrace();
        }
    }

}

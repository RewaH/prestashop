package DataHandlingTests;

import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;

import dataProvider.XLSXDataManager;
import starter.actions.SwaggerRegisterActions;
import starter.pageObjects.SwaggerRegisterObjects;

public class ManageDataIntoXlSXFile {
	static String filePath ="Utils/excel.xlsx";

    public static void main(String[] args) {
    	
    	//retrieveDataFromXLSXFile();
    	//retrieveCellValue();
    	//retrieveRowValue();
    	//addDataFromXLSXFile();
    	//searchDataFromXLSXFile();
    	//appendDataFromXLSXFile();
    	//reformatDataFromXLSXFile();
    	//parseXLSXDataToJavaObject();
    	//parseAllRowsXLSXDataToJavaObject();
    	insertDataIntoNewCOlumn();
    }
    public static void useDataFromXLSXFile() {
//example for SwaggerRegisterActions
    String sheetName = "Sheet1"; // Change this to your sheet name

    XLSXDataManager xlsxDataManager = new XLSXDataManager("Utils/reqres.xlsx");
    Object[][] excelData = xlsxDataManager.readDataFromExcel(sheetName);


    for (Object[] row : excelData) {
        String email = row[0].toString();
        String password = row[1].toString();
		SwaggerRegisterObjects sr =new SwaggerRegisterObjects( email,password);


       // int newId = swaggerRegisterActions.givenGuestCanCreateNewAccount(email, password);
       // System.out.println("New ID created: " + newId);
    }
}
    
    
    
    ////////////////////////////////////////////////////////////////
   // retrieve all data from xlsx file
    public static void retrieveDataFromXLSXFile() {
        XLSXDataManager xlsxDataManager = new XLSXDataManager(filePath);
        xlsxDataManager.retrieveXLSXData();
      
    }
    
    ////////////////////////////////////////////////////////////////
   // retrieve all data from xlsx file
    public static void retrieveCellValue() {
        XLSXDataManager xlsxDataManager = new XLSXDataManager(filePath);
      String value=  xlsxDataManager.retrieveSpecificCellValue(1,3);
      System.out.println(value);
    }
    ////////////////////////////////////////////////////////////////
    // retrieve all data from xlsx file
     public static void retrieveRowValue() {
         XLSXDataManager xlsxDataManager = new XLSXDataManager(filePath);
         XSSFRow row = xlsxDataManager.retrieveSpecificRow(2); // Retrieves row 2 (adjust as needed)
         if (row != null) {
             // Use the retrieved row object for further processing
             System.out.println("Retrieved Row: " + row.getRowNum());
         } else {
             System.out.println("Row not found.");
         }
     }
     
    
    //////////////////////////////////////////////////////////////
    // add new data to xlsx file
    public static void addDataFromXLSXFile() {
        XLSXDataManager xlsxDataManager = new XLSXDataManager(filePath);
        Object[][] dataToAdd = {
                {"Rewaa", 30.0, "Bentonville"},
                {"Malek", 3.0, "Rogers"}
                // Add more rows as needed
        };
        xlsxDataManager.addXLSXData(dataToAdd);

        xlsxDataManager.retrieveXLSXData();
    
    }

  //////////////////////////////////////////////////////////////

    // search  data to xlsx file
    public static void searchDataFromXLSXFile() {
    XLSXDataManager xlsxDataManager = new XLSXDataManager(filePath);
    boolean found = xlsxDataManager.searchXLSXData("3.0");
   // boolean found = xlsxDataManager.searchXLSXData("Rewaa");
    System.out.println("Search result: " + found);

}

    //////////////////////////////////////////////////////////////

      // append data to xlsx file
      public static void appendDataFromXLSXFile() {
      XLSXDataManager xlsxDataManager = new XLSXDataManager(filePath);
      Object[] newData = {"Updated Data", 40.0, "London"};
      boolean appended = xlsxDataManager.appendXLSXData(3, newData);
      System.out.println("Append result: " + appended);
      xlsxDataManager.retrieveXLSXData();

      
      
      }
    
      
//////////////////////////////////////////////////////////////

      // reformat data to xlsx file

      public static void reformatDataFromXLSXFile() {
    	  XLSXDataManager xlsxDataManager = new XLSXDataManager(filePath);
    	  
    	  boolean reformatted = xlsxDataManager.reformatXLSXData(2, 2, "Reformatted City");
          System.out.println("Reformat result: " + reformatted);
          xlsxDataManager.retrieveXLSXData();  
      }
      
//////////////////////////////////////////////////////////////

// parse first row 
      public static void parseXLSXDataToJavaObject() {
    	  XLSXDataManager xlsxDataManager = new XLSXDataManager(filePath);
    	  CustomObject parsedObject = xlsxDataManager.parseXLSXData(1);
    	 
    	  if (parsedObject != null) {
    		  System.out.println("Parsed CustomObject:");
    		  System.out.println("Name: " + parsedObject.getName());
    		  System.out.println("Age: " + parsedObject.getAge());
    		  System.out.println("City: " + parsedObject.getCity());
}  
      
}

      
//////////////////////////////////////////////////////////////

//parse all data 

      public static void parseAllRowsXLSXDataToJavaObject() {
    	  XLSXDataManager xlsxDataManager = new XLSXDataManager(filePath);
          List<CustomObject> customObjects = xlsxDataManager.parseAllRows();
          if (customObjects != null && !customObjects.isEmpty()) {
              for (CustomObject customObject : customObjects) {
                  System.out.println("Name: " + customObject.getName());
                  System.out.println("Age: " + customObject.getAge());
                  System.out.println("City: " + customObject.getCity());
                  System.out.println("---------------");
              }
          } else {
              System.out.println("No data found in the Excel file.");
          }
}

//////////////////////////////////////////////////////////////

//parse all data 

public static void insertDataIntoNewCOlumn() {
	 XLSXDataManager xlsxDataManager = new XLSXDataManager(filePath);
     
     // Insert data into new column for row 1, with header "New Column" and data "Value1"
     xlsxDataManager.insertDataIntoNewColumn(1, "New Column", "Value1");
 }


}

package DataHandlingTests;
import net.serenitybdd.junit5.SerenityJUnit5Extension;
import starter.actions.PrestaShopAddressesActions;

import java.io.IOException;
import java.util.List;

import org.apache.commons.io.filefilter.CanReadFileFilter;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import dataProvider.TxtFileManager;

public class ManageDatafromtxtPropertiesFile {
    public static void main(String[] args) throws IOException {


 //   	readFile();
//    	readFileFromEnd();
//    	readFileReversed();
//    	retrieveData();
//    	searchData();
    	
 //       appendData();
   // 	reformatLines();
    //	reformatPartOfLine();
     //  createFile();
    	deleteFile();
        }
    
////////////////////////////////////////////////////////////////

    public static void readFile() {
    	  TxtFileManager txtFileManager = new TxtFileManager("Utils\\data.properties");
    	    List<String> lines = txtFileManager.readFromFile();
    	    for (String line : lines) {
    	        System.out.println(line);
    	    }
    }
    public static void readFileFromEnd() {
    	 TxtFileManager txtFileManager2 = new TxtFileManager("Utils\\file.txt");
         List<String> linesFromEnd = txtFileManager2.readFromFileFromEnd();
         for (String line2 : linesFromEnd) {
             System.out.println(line2);
    }
}
////////////////////////////////////////////////////////////////

    public static void readFileReversed() throws IOException {
  	  TxtFileManager txtFileManager = new TxtFileManager("Utils\\file.txt");
      String reversedContent = txtFileManager.readFromTheEndReversed();

      System.out.println("Reversed content: " + reversedContent);
    }
    
////////////////////////////////////////////////////////////////
 
    public static void retrieveData() {
    	  TxtFileManager txtFileManager = new TxtFileManager("Utils\\file.txt");
          String retrievedLine = txtFileManager.retrieveData(0);
          System.out.println("retrieved Line is: " + retrievedLine);

    }
////////////////////////////////////////////////////////////////
    public static void searchData() {
    	  TxtFileManager txtFileManager = new TxtFileManager("Utils\\file.txt");
          List<String> result = txtFileManager.searchData("in");
          System.out.println("retrieved Line is: " + result);
    }
    
    
////////////////////////////////////////////////////////////////
public static void appendData() {
	 TxtFileManager txtFileManager = new TxtFileManager("Utils\\file.txt");
     boolean result = txtFileManager.appendData(1, "I am writing here");
     System.out.println(result);
     String newLine=txtFileManager.retrieveData(1);
     System.out.println(newLine);
	
	
}


////////////////////////////////////////////////////////////////
public static void reformatLines() {
	TxtFileManager txtFileManager = new TxtFileManager("Utils\\file.txt");
    boolean result = txtFileManager.reformatLine(1, "I am formating here");
    System.out.println(result);
    String newLine=txtFileManager.retrieveData(1);
    System.out.println(newLine);
}


////////////////////////////////////////////////////////////////
public static void reformatPartOfLine() {
TxtFileManager txtFileManager = new TxtFileManager("Utils\\file.txt");
boolean result = txtFileManager.reformatLinePart(1, "formating","editing");
System.out.println(result);
String newLine=txtFileManager.retrieveData(1);
System.out.println(newLine);
}


////////////////////////////////////////////////////////////////
public static void createFile() {
	String newFilePath = "Utils\\new_file.txt";
    String fileContent = "This is the content of the new file.";
TxtFileManager txtFileManager = new TxtFileManager("");
if (txtFileManager.createFile(newFilePath, fileContent)) {
    System.out.println("File created successfully.");
} else {
    System.out.println("File already exists or an error occurred.");
}
}

////////////////////////////////////////////////////////////////
public static void deleteFile() {
    String filePath = "Utils\\new_file.txt";
TxtFileManager txtFileManager = new TxtFileManager("");
if(txtFileManager.deleteFile(filePath)) {
	 System.out.println("File deleted successfully.");
} else {
    System.out.println("File deletion failed.");
}
}
}

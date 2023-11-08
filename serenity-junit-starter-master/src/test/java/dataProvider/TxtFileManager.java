package dataProvider;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TxtFileManager {
	private String filePath ;

    public TxtFileManager(String filePath) {
        this.filePath = filePath;
    }
	  public List<String> readFromFile() {
	        List<String> lines = new ArrayList<>();
	        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
	            String line;
	            while ((line = reader.readLine()) != null) {
	                lines.add(line);
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        return lines;
	    }
////////////////////////////////////////////////////////////////	  
	  
	  public List<String> readFromFileFromEnd() {
	        List<String> lines = readFromFile();
	        Collections.reverse(lines); // Reverse the order of lines
	        return lines;
	    }
	  
////////////////////////////////////////////////////////////////
	  
	  public String readFromTheEndReversed()  {
		  //Reverse the content from left to right
		    StringBuilder reversedContent = new StringBuilder();

		    try (RandomAccessFile raf = new RandomAccessFile(filePath, "r")) {
		        long length = raf.length();
		        long position = length - 1;
		        raf.seek(position);

		        int b;
		        while (position >= 0) {
		            b = raf.read();
		            char character = (char) b;
		            reversedContent.append(character);

		            position--;
		            raf.seek(position);
		        }
		    } catch (IOException e) {
		        e.printStackTrace();
		    }

		    return reversedContent.toString();
		}
	  
//////////////////////////////////////////////////////////////	  
	
	  public String retrieveData(int lineNumber) {
	        try {
	            BufferedReader reader = new BufferedReader(new FileReader(filePath));
	            String line;
	            int currentLine = 0;
	            while ((line = reader.readLine()) != null) {
	                if (currentLine == lineNumber) {
	                    reader.close();
	                    return line;
	                }
	                currentLine++;
	            }
	            reader.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        return null;
	    }

	  
////////////////////////////////////////////////////////////////
	  
	  public List<String> searchData(String searchString) {
	        List<String> results = new ArrayList<>();
	        try {
	            BufferedReader reader = new BufferedReader(new FileReader(filePath));
	            String line;
	            int currentLine = 0;
	            while ((line = reader.readLine()) != null) {
	                if (line.contains(searchString)) {
	                    results.add("Line " + currentLine + ": " + line);
	                }
	                currentLine++;
	            }
	            reader.close();
	        } catch (IOException e) {
	            e.printStackTrace();
	        }
	        return results;
	    }		
	
////////////////////////////////////////////////////////////////

	  public boolean appendData(int lineNumber, String appendedData) {
	        try {
	            BufferedReader reader = new BufferedReader(new FileReader(filePath));
	            List<String> lines = new ArrayList<>();
	            String line;
	            int currentLine = 0;
	            while ((line = reader.readLine()) != null) {
	                if (currentLine == lineNumber) {
	                    line += appendedData;         //to append data at the end of the line
	                   // lines.add(appendedData);    // to append data at new line above the provided line
	                    //line = appendedData + line; // Prepend the data to the line

	                }
	                lines.add(line);
	                currentLine++;
	            }
	            reader.close();

	            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
	            for (String modifiedLine : lines) {
	                writer.write(modifiedLine + "\n");
	            }
	            writer.close();
	            return true;
	        } catch (IOException e) {
	            e.printStackTrace();
	            return false;
	        }
	    }
////////////////////////////////////////////////////////////////
	  public boolean reformatLine(int lineNumber, String newLine) {
	        try {
	            BufferedReader reader = new BufferedReader(new FileReader(filePath));
	            List<String> lines = new ArrayList<>();
	            String line;
	            int currentLine = 0;
	            while ((line = reader.readLine()) != null) {
	                if (currentLine == lineNumber) {
	                    line = newLine;
	                }
	                lines.add(line);
	                currentLine++;
	            }
	            reader.close();

	            BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
	            for (String modifiedLine : lines) {
	                writer.write(modifiedLine + "\n");
	            }
	            writer.close();
	            return true;
	        } catch (IOException e) {
	            e.printStackTrace();
	            return false;
	        }
	    }
	

////////////////////////////////////////////////////////////////
	  public boolean reformatLinePart(int lineNumber, String search, String replace) {
		    try {
		        BufferedReader reader = new BufferedReader(new FileReader(filePath));
		        List<String> lines = new ArrayList<>();
		        String line;
		        int currentLine = 0;
		        while ((line = reader.readLine()) != null) {
		            if (currentLine == lineNumber) {
		                // Replace the part of the line
		                line = line.replace(search, replace);
		            }
		            lines.add(line);
		            currentLine++;
		        }
		        reader.close();

		        BufferedWriter writer = new BufferedWriter(new FileWriter(filePath));
		        for (String modifiedLine : lines) {
		            writer.write(modifiedLine + "\n");
		        }
		        writer.close();
		        return true;
		    } catch (IOException e) {
		        e.printStackTrace();
		        return false;
		    }
		}

//////////////////////////////////////////////////////////////	  
	  public boolean createFile(String newFilePath, String fileContent) {
	        try {
	            File newFile = new File(newFilePath);
	            if (newFile.createNewFile()) {
	                FileWriter writer = new FileWriter(newFile);
	                writer.write(fileContent);
	                writer.close();
	                return true;
	            } else {
	                // The file already exists.
	                return false;
	            }
	        } catch (IOException e) {
	            e.printStackTrace();
	            return false;
	        }
	    }
//////////////////////////////////////////////////////////////

	  
	  
	  
	public void checkFileStatus() {
		File file = new File(filePath);
		boolean exists = file.exists();
		boolean isFile = file.isFile();
		boolean isDirectory = file.isDirectory();
	}
//////////////////////////////////////////////////////////////

	public boolean deleteFile(String filePath) {
        try {
            File file = new File(filePath);

            if (file.exists()) {
                if (file.delete()) {
                    return true;
                } else {
                    System.out.println("Failed to delete the file.");
                    return false;
                }
            } else {
                System.out.println("File does not exist.");
                return false;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}

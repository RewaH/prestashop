package dataProvider;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;

public class TextFileManipulation {
	public void readFromTheBeginning() throws IOException {
		BufferedReader reader = new BufferedReader(new FileReader("file.txt"));
		String line;
		while ((line = reader.readLine()) != null) {
		    // Process the line
		}
		reader.close();
		
	}
	public void readFromTheEnd() throws IOException {
		try (RandomAccessFile raf = new RandomAccessFile("file.txt", "r")) {
		    long length = raf.length();
		    long position = length - 1;
		    raf.seek(position);

		    int b;
		    while (position >= 0) {
		        b = raf.read();
		        // Process the byte (character) from the end
		        position--;
		        raf.seek(position);
		    }
		} catch (IOException e) {
		    e.printStackTrace();
		}
	
}
	
	
	public void searchingInTheMiddle() throws IOException {
		try (BufferedReader reader = new BufferedReader(new FileReader("file.txt"))) {
		    String line;
		    while ((line = reader.readLine()) != null) {
		        if (line.contains("bees")) {
		            // Process the line with the search text
		        }
		    }
		} catch (IOException e) {
		    e.printStackTrace();
		}
	
	}
	public void writingToTextFile() throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter("file.txt"));
		writer.write("Hello, World!");
		writer.newLine(); // Write a new line
		writer.close();
	}
	
	public void appendingToTextFile() throws IOException {
		BufferedWriter writer = new BufferedWriter(new FileWriter("file.txt", true));
		writer.write("Appended text");
		writer.newLine();
		writer.close();

	}
	public void checkFileStatus() {
		File file = new File("file.txt");
		boolean exists = file.exists();
		boolean isFile = file.isFile();
		boolean isDirectory = file.isDirectory();
	}

}

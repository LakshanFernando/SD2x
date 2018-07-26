package week_II.homework_VIII;/*
 * SD2x Homework #8
 * This class represents the Data Tier in the three-tier architecture.
 * Implement the appropriate methods for this tier below.
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("WeakerAccess")
public class DataTier {
	
	private String fileName; // the name of the file to read
	
	public DataTier(String inputSource) {
		fileName = inputSource;
	}

	public List<Book> getAllBooks() {
	    List<Book> books = new ArrayList<>();

	    try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
	        String line;
	        while ((line = bufferedReader.readLine()) != null) {
	            String[] tokens = line.split("\t");
	            books.add(new Book(tokens[0], tokens[1], Integer.parseInt(tokens[2])));
            }
        }catch (IOException ex) {
            System.out.printf("Can't read from %s\n", fileName);
        }

        return books;
    }
}

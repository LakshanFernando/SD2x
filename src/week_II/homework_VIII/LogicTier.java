package week_II.homework_VIII;/*
 * SD2x Homework #8
 * This class represents the Logic Tier in the three-tier architecture.
 * Implement the appropriate methods for this tier below.
 */

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@SuppressWarnings("WeakerAccess")
public class LogicTier {
	
	private DataTier dataTier; // link to the Data Tier
	
	public LogicTier(DataTier dataTier) {
		this.dataTier = dataTier;
	}

	/*
	* Given an author name, go through the books list and return the books whose author
	* 'partial' matches the given author name.
	*/
	public Set<String> findBookTitlesByAuthor(String author) {
	    Set<String> titles = new HashSet<>();
        List<Book> books = dataTier.getAllBooks();

        for (Book book : books) {
            if (book.getAuthor().toLowerCase().contains(author.toLowerCase())) titles.add(book.getTitle());
        }
	    return titles;
    }

    /*
    * Given a year, search through the books collection and return the number
    * books with publication year corresponding to given year.
    */
    public int findNumberOfBooksInYear(int year) {
	    List<Book> books = dataTier.getAllBooks();
	    int count = 0;

        for (Book book : books) {
            if (book.getPublicationYear() == year) count++;
        }
        return count;
    }
}

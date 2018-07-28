package week_II.homework_VIII;/*
 * SD2x Homework #8
 * This class represents the Presentation Tier in the three-tier architecture.
 * Implement the appropriate methods for this tier below. 
 * Also implement the start method as described in the assignment description.
 */

import java.util.Scanner;
import java.util.Set;
@SuppressWarnings("WeakerAccess")
public class PresentationTier {
	
	private LogicTier logicTier; // link to the Logic Tier
	private Scanner scanner = new Scanner(System.in);
	
	public PresentationTier(LogicTier logicTier) {
		this.logicTier = logicTier;
	}
	
	public void start() {
	    String character = "====";
        System.out.printf("%s Welcome %s\n", character, character);
        System.out.print("\tA. Find books by author name\t\t");
        System.out.print("\tB. Find number of books published in the year\n");
        System.out.println("Select an action: ");

        String action = scanner.nextLine();

        switch (action.toLowerCase()) {
            case "a":
                showBookTitlesByAuthor();
                break;
            case "b":
                showNumberOfBooksInYear();
                break;
            default:
                System.out.println("Wrong input!");
                break;
        }

        System.out.printf("%s Thank you, Bye! %s", character, character);
	}

    public void showNumberOfBooksInYear() {
        System.out.println("Enter the publication year:");
        int year = scanner.nextInt();

        int booksInYear = logicTier.findNumberOfBooksInYear(year);
        String state = "book";
        if (booksInYear > 1 || booksInYear == 0) state += "s";

        System.out.printf("%d %s were published in %d\n", booksInYear, state, year);
    }

    public void showBookTitlesByAuthor() {
        System.out.println("Enter author's name:");
        String author = scanner.nextLine();

        Set<String> books = logicTier.findBookTitlesByAuthor(author);

        if (!books.isEmpty()) {

            String state = books.size() == 1 ? "Books" : "Books";

            System.out.printf("%s by %s:\n", state, author);

            for (String title : books) {
                System.out.printf("%s\n", title);
            }
        }
        else System.out.printf("There are no books by %s in our database\n", author);
    }
}

package week_II.homework_VIII;
 /*
 * SD2x Homework #8
 * This class creates the classes in the three tiers and links them together.
 * You should not modify this code. You do not need to submit it.
 */

import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) {
		Scanner scan = new Scanner(System.in);
        System.out.println("Please enter a file:");
		DataTier dt = new DataTier(scan.nextLine());
		LogicTier lt = new LogicTier(dt);
		PresentationTier pt = new PresentationTier(lt);
		pt.start();
		
	}
}

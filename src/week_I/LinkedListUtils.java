package week_I;

import java.util.Arrays;
import java.util.LinkedList;

/*
 * SD2x Homework #1
 * Implement the methods below according to the specification in the assignment description.
 * Please be sure not to change the signature of any of the methods!
 */

public class LinkedListUtils {

    /**
     * Given a sorted LinkedList this method attempts to put the variable value in the proper position in the List so
     * that it remains sorted.
     * @param list a sorted LinkedList
     * @param value the value to be added to the list
     */
	public static void insertSorted(LinkedList<Integer> list, int value) {
        if (list == null) return;
        else if (list.size() == 0) list.add(value); // for an empty list, just add to as the first element.
        else if (value <= list.getFirst()) list.addFirst(value); // add to the front of the list if smaller than first
        else if (value >= list.getLast()) list.addLast(value); // add to the end of the list if smaller than last
        else {
            for (int i = 0; i < list.size() - 1; i++) { // from index 1 to size-1 since first and last have been checked
                if (value <= list.get(i)) {
                    list.add(i, value);
                    break;
                }
            }
        }
	}

    /**
     * Given a LinkedList this method attempts to remove all occurrence of the largest entry
     * @param list list to be mutated
     */
	private static void removeAll(LinkedList<String> list) {
	    String max = list.getFirst(); // keeping track of the largest value so far
        for (int i = 1; i < list.size(); i++) {
            if (max.compareTo(list.get(i)) < 0) max = list.get(i); // updates the max
        }

        while (list.contains(max)) list.remove(max); // removes all occurrence of max
    }


    /**
     * Attempts to remove all N largest elements from a LinkedList
     * @param list the list to be mutated
     * @param N the number of large elements to be removed
     */
	public static void removeMaximumValues(LinkedList<String> list, int N) {

        if (list == null) return;
        else {
            for (int i = 0; i < N; i++) {
                if (list.size() == 0) return;
                else removeAll(list);
            }
        }

	}

    /**
     * Given two LinkedList, this method returns true is second list is a Sub-sequence of the first
     * @param one
     * @param two
     * @return
     */
	public static boolean containsSubsequence(LinkedList<Integer> one, LinkedList<Integer> two) {

		if (one == null || one.size() == 0 || two == null || two.size() == 0) return false;//null or empty returns false
		else {
		    Integer[] first = one.toArray(new Integer[one.size()]);
		    Integer[] second = two.toArray(new Integer[two.size()]);
		    while (first.length >= second.length) {
		        if (Arrays.equals(Arrays.copyOfRange(first, 0, second.length), second)) return true;
		        else first = Arrays.copyOfRange(first,1,first.length);
            }
        }
		return false;
	}
}

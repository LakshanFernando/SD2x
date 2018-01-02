package week_I;

import java.util.Arrays;
import java.util.LinkedList;

/*
 * SD2x Homework #1
 * Implement the methods below according to the specification in the assignment description.
 * Please be sure not to change the signature of any of the methods!
 */

public class LinkedListUtils {
	
	public static void insertSorted(LinkedList<Integer> list, int value) {
        if (list == null) return;
        else if (list.size() == 0) list.add(value);
        else if (value <= list.getFirst()) list.addFirst(value);
        else if (value >= list.getLast()) list.addLast(value);
        else {
            Integer[] clone = list.toArray(new Integer[list.size()]);
            for (int i = 0; i < clone.length; i++) {
                if ((Integer)value <= clone[i]) {
                    list.add(i, value);
                    break;
                }
            }
        }
	}

	private static void removeAll(LinkedList<String> list) {
	    String max = list.getFirst();
        for (int i = 1; i < list.size(); i++) {
            if (max.compareTo(list.get(i)) < 0) max = list.get(i);
        }

        while (list.contains(max)) list.remove(max);
    }
	

	public static void removeMaximumValues(LinkedList<String> list, int N) {

        if (list == null) return;
        else {
            for (int i = 0; i < N; i++) {
                if (list.size() == 0) return;
                else removeAll(list);
            }
        }

	}
	
	public static boolean containsSubsequence(LinkedList<Integer> one, LinkedList<Integer> two) {

		if (one == null || one.size() == 0 || two == null || two.size() == 0) return false;
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

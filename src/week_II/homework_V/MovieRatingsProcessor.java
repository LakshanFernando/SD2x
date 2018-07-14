package week_II.homework_V;
/*
 * SD2x Homework #5
 * Implement the methods below according to the specification in the assignment description.
 * Please be sure not to change the method signatures!
 */

import java.util.*;
import java.util.function.Supplier;
import java.util.stream.Collectors;

@SuppressWarnings("ALL")
public class MovieRatingsProcessor {

    /*
    * Returns a list of movie titles in alphabetical order.
    *
    * Pseudo-code
    * IF inputTreeMap is empty or null
    *   Return an empty List
    * ELSE
    *   Get the String keySet of the TreeMap
    *   Convert keySet to a List
    *   Sort the List using the sort method in the Collections class.
    */
	public static List<String> getAlphabeticalMovies(TreeMap<String, PriorityQueue<Integer>> movieRatings) {

		if (movieRatings == null || movieRatings.isEmpty()) return new ArrayList<>(); // empty or null TreeMap

        // https://stackoverflow.com/questions/21912314/what-kind-of-liste-does-collectors-tolist-return
        Supplier<List<String>> supplier = ArrayList::new;
        return movieRatings.keySet().stream().sorted().collect(Collectors.toCollection(supplier));
	}

	/*
	* Given an input int rating and a TreeMap of movieRatings, return a List of movie titles in alphabetical order,
	* where all movies in the List do not have any ratings less than or equal to rating.
	*
	* Pseudo-code
	* IF movieRatings is empty or null
	*   Return an empty List
	* Create a new List
	* FOR (each title in the set of keys in the Map)
	*   IF (the head of the PriorityQueue the key maps to is greater than rating)
	*       add that title to the list
	* Sort the list using the sort method in the Collection class
	* Return the list
    */
	public static List<String> getAlphabeticalMoviesAboveRating(TreeMap<String, PriorityQueue<Integer>> movieRatings, int rating) {

		if (movieRatings == null || movieRatings.isEmpty()) return new ArrayList<>(); // empty or null TreeMap

        List<String> stringList = new ArrayList<>();

        for (String title: movieRatings.keySet()) {
            if (movieRatings.get(title).peek() > rating) {
                stringList.add(title);
            }
        }
        Collections.sort(stringList);

		return stringList;
	}

	/*
	* Given an input int rating, modify the TreeMap object that was passed as an argumentso that you remove all ratings
	* in the PriorityQueue that are below (but not equal to) rating for every movie in the Map.
	*
	* If all ratings are removed from a movie’s PriorityQueue, then remove the mapping from the TreeMap.
	* Additionally, this method should return a new TreeMap that maps a movie title to the number of ratings that were
	* removed from its corresponding PriorityQueue.
	* The TreeMap that is returned should only contain movies that had ratings removed from its PriorityQueue.
    */
	public static TreeMap<String, Integer> removeAllRatingsBelow(TreeMap<String, PriorityQueue<Integer>> movieRatings, int rating) {

		if (movieRatings == null || movieRatings.isEmpty()) return new TreeMap<>();

		TreeMap<String, Integer> removed = new TreeMap<>();

        /*
         * use Iterator to iterate the Map and the PriorityQueue of ratings
         * in order to avoid ConcurrentModificationException
         */
        Iterator<Map.Entry<String, PriorityQueue<Integer>>> mappings = movieRatings.entrySet().iterator();

        while (mappings.hasNext()) {
            Map.Entry<String, PriorityQueue<Integer>> mapping = mappings.next();
            Iterator<Integer> mappingRating = mapping.getValue().iterator();
            int count = 0;
            while (mappingRating.hasNext()) {
                if (mappingRating.next() < rating) {
                    mappingRating.remove();
                    count++;
                }
            }
            if (count > 0) removed.put(mapping.getKey(), count);
            if (mapping.getValue().isEmpty()) mappings.remove();
        }
		
		return removed;
	}
}

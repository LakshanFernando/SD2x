package week_II.homework_V;
/*
 * SD2x Homework #5
 * Implemented
 */

import java.util.List;
import java.util.PriorityQueue;
import java.util.TreeMap;

@SuppressWarnings("ALL")
public class MovieRatingsParser {

    /*
    * Take in a list of UserMovieRating objects, and returns a TreeMap of movie titles mapped to a PriorityQueue of
    * there individual ratings.
    *
    * Pseudo-code
    * Initialize an empty TreeMap call movieRatings
    * IF (inputList is null or empty)
    *   return the empty TreeMap-movieRatings
    * ELSE
    *   FOR all UserMovieRating object in the inputList
    *       IF (object is not null AND object title is not empty AND is not null AND object rating is greater than 0)
    *           IF (object title doesn't exist in movieRatings)
    *               Create an empty PriorityQueue
    *               Add object rating into the PriorityQueue
    *               Put the object title mapped to the PriorityQueue into the TreeMap
    *           ELSE
    *               Get the PriorityQueue mapped to object title
    *               Add the new object rating to the PriorityQueue
    */
	public static TreeMap<String, PriorityQueue<Integer>> parseMovieRatings(List<UserMovieRating> allUsersRatings) {

		TreeMap<String, PriorityQueue<Integer>> movieRatings = new TreeMap<>();
		if (allUsersRatings == null || allUsersRatings.isEmpty()) return movieRatings;
		else {
            for (UserMovieRating userRating: allUsersRatings) {
                if (userRating != null && (userRating.getMovie() != null && !userRating.getMovie().isEmpty())
                        && userRating.getUserRating() >= 0) {
                    String title = userRating.getMovie().toLowerCase();
                    if (!movieRatings.containsKey(title)) { // add a new movie - rating
                        PriorityQueue<Integer> rating = new PriorityQueue<>();
                        rating.add(userRating.getUserRating());
                        movieRatings.put(title, rating);
                    }
                    else { // add new rating to an existing movie
                        movieRatings.get(title).add(userRating.getUserRating());
                    }
                }
            }
        }
		
		return movieRatings;
	}

}

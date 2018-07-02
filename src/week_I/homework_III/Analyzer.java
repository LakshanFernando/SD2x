package week_I.homework_III;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

/*
 * This assignment deals with computational linguistics that seeks to understand the sentiment of a given text.
 * Numeric values are given to words based on their positivity/negativity. And this words are used to determine the
 * overall sentiment of a statement.
 */
@SuppressWarnings("ALL")
public class Analyzer {
	
	/*
	 * Implements a method for reading a text file one line at a time to create sentences object which has a sentiment score
	 * attached to it, as well as a text field. The method ignores lines in the file that are not well formatted(i.e having
	 * a score greater than 2 or less than 2).
	 * Also returns an empty list if the file can't be open or the filename is null
	 */
	public static List<Sentence> readFile(String filename) {
        List<Sentence> sentences = new ArrayList<>();
        List<String> lines;

        if (filename != null) {
            try {
                // produces an ArrayList with each entry corresponding to a line in the file.
                // https://stackoverflow.com/questions/4716503/reading-a-plain-text-file-in-java
                lines = Files.lines(Paths.get(filename)).collect(Collectors.toList());
            } catch (IOException e) {
                return new ArrayList<>();
            }

            for (String line: lines) {
                int index = line.indexOf(" ");

                try {
                    int score = Integer.parseInt(line.substring(0, index));
                    String text = line.substring(index + 1).trim();

//                if the line is properly formatted, add to the list of sentences
                    if (score <= 2 && score >= -2 && !text.isEmpty()) sentences.add(new Sentence(score, text));
                } catch (Exception ignored) {
                }
            }
        }

        return sentences;
    }
	
	/*
	 * This method tokenizes each sentence object in the list from the method above. The token object are converted to
	 * word objects and the cumulative score of each word objects are recorded. The method ignores tokens that begin with
	 * character that aren't alphabetic. Returns an empty Set when the List of sentences is null or empty.
	 */
	public static Set<Word> allWords(List<Sentence> sentences) {
		// use a temporary DT to hold the words
        List<Word> words = new ArrayList<>();

        if (!(sentences == null || sentences.isEmpty())) { // only happens when the sentence is not empty or null
            for (Sentence sentence: sentences) {
                if (sentence != null) {
                    String[] tokens = sentence.text.toLowerCase().split(" "); //convert to lowercase for uniformity
                    for (String token: tokens) {
                        if (Character.isLetter(token.charAt(0))) { // the word starts with a letter
                            Word word = new Word(token);
                            word.increaseTotal(sentence.getScore());

                            if (words.contains(word)) {
                                words.get(words.indexOf(word)).increaseTotal(word.getTotal());
                            } else {
                                words.add(word);
                            }
                        }
                    }
                }
            }
        }
        return new HashSet<>(words);
	}
	
	/*
	 * This method iterate over the input set to calculate the average sentiment of a word, placing the text and the
	 * calculatd score in a Map as a key-value pair. Ignores word objects that are null and returns an empty map if the
	 * input set is null or empty.
	 */
	public static Map<String, Double> calculateScores(Set<Word> words) {
	    Map<String, Double> wordScore = new HashMap<>();

	    if (words != null && !words.isEmpty()) {
            for (Word word: words) {
                if (word != null) {
                    wordScore.put(word.getText(), word.calculateScore());
                }
            }
        }
        return wordScore;
	}
	
	/*
	 * This method uses the key-value pair in an input Map to assign a score for a sentence. That the average of all the
	 * sentiments of all the words in the sentence.
	 * Returns a score of 0 if the input Map is null or empty.
	 */
	public static double calculateSentenceScore(Map<String, Double> wordScores, String sentence) {
        double score = 0;
        int count = 0;

        if (!(wordScores == null || wordScores.isEmpty()
                || sentence == null || sentence.isEmpty())) {
            String[] tokens = sentence.toLowerCase().split(" ");
            for (String token : tokens) {
                if (Character.isLetter(token.charAt(0))) { // token starts with a letter
                    if (wordScores.containsKey(token)) score += wordScores.get(token);
                    count++;
                }
            }
        }

        return count == 0 ? 0 : score / count;
    }
	

	public static void main(String[] args) {
//		if (args.length == 0) {
//			System.out.println("Please specify the name of the input file");
//			System.exit(0);
//		}
//		String filename = args[0];
		System.out.print("Please enter a sentence: ");
		Scanner in = new Scanner(System.in);
		String sentence = in.nextLine();
		in.close();
		List<Sentence> sentences = Analyzer.readFile("reviews.txt");
		Set<Word> words = Analyzer.allWords(sentences);
		Map<String, Double> wordScores = Analyzer.calculateScores(words);
		double score = Analyzer.calculateSentenceScore(wordScores, sentence);
		System.out.println("The sentiment score is " + score);

	}
}

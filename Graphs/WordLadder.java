import java.io.File;
import java.io.IOException;
import java.util.Scanner;
//JKust making sure it updated when saved comment

public class WordLadder {
	private static String start;
	private static String end;
	private static StringMap T; // This map stores the dictionary of words.
	private static StringMap R; // This map keeps track of all the words that are visited during
								// breadth-first-search.
								// The key field is the word that is visited, and its value field can hold the
								// predecessor pointer.
	private static Queue Q; // A queue to perform the breadth-first-search.

	/**
	 * @param word
	 *             recusrivly prints the word ladder!
	 */
	public static void pSequence(String word) {
		// if word empty return;
		if (word.equals("")) {
			return;
		}
		// String node x will be set to if R finds word
		StringNode x = R.find(word);
		// recurses back through print
		pSequence(x.getValue());
		// prints outs string word
		System.out.println(word);
	}

	/**
	 * @param m
	 * @return
	 *         Checks wether is same word and if it is return true but if not
	 *         continue recusively until it is or if impossible return false;
	 */
	public static boolean sameword(QNode m) {
		// int distance of word
		int d = m.getDist();
		// get the actual word
		String w = m.getWord();
		// for loop with the length of the word
		for (int i = 0; i < w.length(); i++) {
			// string buffer
			StringBuffer t = new StringBuffer(w);
			// for loops with chars
			for (char z = 'a'; z <= 'z'; z++) {
				// sets new char and int
				t.setCharAt(i, z);
				// converts chars to string variable
				String p2 = t.toString();
				// sees if same word.
				if (p2.equals(end)) {
					// inserts W at the end of R
					R.insert(end, w);
					// returns true so is known word was found.
					return true;
				}
				// If R p2 is notfound and null is returned and if T finds p2 and doesnnot
				// return
				// null enqueue and instert.
				if (R.find(p2) == null && T.find(p2) != null) {
					// enqueues p2 in the next positions
					Q.enqueue(new QNode(d + 1, p2));
					// inxerts w into p2 aswell
					R.insert(p2, w);
				}
			}
		}
		// returns false if word is impossibel to recurse
		return false;
	}

	/**
	 * 
	 * @param args
	 * @throws IOException
	 *                     main method to recurse thru the dictionary
	 */
	public static void main(String[] args) throws IOException {
		// Loading the dictionary of words into the StringMap T.
		T = new StringMap();
		File file = new File("dictionary4");
		Scanner f = new Scanner(file);
		while (f.hasNext()) {
			String word = f.nextLine();
			T.insert(word, "");
		}
		f.close();
		// scanner for words
		Scanner kb = new Scanner(System.in);
		// sets start and end word
		System.out.print("Enter the start word: ");
		start = kb.nextLine();
		// end word input
		System.out.print("Enter the end word: ");
		end = kb.nextLine();
		// simple while loop to make sure word is not more or less then 4 letters!
		while (start.length() > 4 || end.length() > 4 || start.length() < 4 || end.length() < 4) {
			// Simple string lol
			System.out.println("Word has to be 4 letters long! Please reenter word!");
			// have user re input value for start word
			System.out.print("Enter the start word: ");
			start = kb.nextLine();
			// have user re input value for end word
			System.out.print("Enter the end word: ");
			end = kb.nextLine();
		}
		kb.close();
		// TODO: Solution to find the shortest set of words that transforms the start
		// word to the end word.
		Q = new Queue();
		// enquese the new queue with the start
		Q.enqueue(new QNode(0, start));
		// Starts the new R string map
		R = new StringMap();
		// inserts the start valye and a empty string
		R.insert(start, "");
		// found word boolean (set to false as a preface)
		boolean fword = false;
		// if start is equal to the end will set found word to true
		if (start.equals(end)) {
			fword = true;
		}
		// while the queue is not empty and word has not been found
		while (!Q.isEmpty() && !fword) {
			// Qnode x is set to the dequeded Queue
			QNode qdeq = Q.dequeue();
			// if same word return true set found word to true
			if (sameword(qdeq)) {
				// sets fword to true so latter will be able to print sequence for the solution
				fword = true;
			}
		}
		// if fword true print found solution and print the sequence formula to
		// solution.
		if (fword == true) {
			System.out.println("Yay! A word ladder is possible.");
			// prints the sequence
			pSequence(end);
		} else {
			// if found word is false print that sequence formula
			System.out.println("Duh! Immpossible");
		}
	}
}

import java.util.*;
import java.io.*;
import java.io.File;
import java.util.concurrent.TimeUnit;

public class Anagrams {
	public static void main(String[] args) throws IOException {
		long startTime = System.nanoTime();
		File file = new File("sample.txt");
		Scanner sc = new Scanner( file );
		HashMap<String, ArrayList<String>> map =  new HashMap<String, ArrayList<String>>();
		
		while( sc.hasNextLine() ) {
			String word = sc.nextLine();
			
			/* use it as key */
			String sortedWord = sortString(word);
			
			/* get the values list from Map if word already exist */
			ArrayList<String> anagrams = map.get( sortedWord );
			
			/* create a new arraylist if there is not already existing one */
			if( anagrams == null ) anagrams = new ArrayList<String>();

			anagrams.add(word);
			map.put(sortedWord, anagrams);
		}
		
        for (ArrayList<String> name: map.values()) {
            Collections.sort(name);
            System.out.println(String.join(" ", name));
        }

        /* To know the complete execution time */
        /* With 1100000 records execution time
         * with Printing on console : 3279 ms
         * without priniting : 1858 ms
         * Uncomment Sysout line below
         */
        long endTime = System.nanoTime();
        long elapsedTimeInMillis = TimeUnit.MILLISECONDS.convert((endTime - startTime), TimeUnit.NANOSECONDS);
        //System.out.println("Total elapsed time: " + elapsedTimeInMillis + " ms");
	}
	private static String sortString( String w ) {
		char[] ch = w.toCharArray();
		Arrays.sort(ch);
		return new String(ch);
	}
}
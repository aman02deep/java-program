import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;
import java.util.*;

public class Java8Anagrams {
    public static void main(String[] args) {
        long startTime = System.nanoTime();
        
        Path file = Paths.get("sample.txt");
        try {
            Stream<String> lines = Files.lines( file, StandardCharsets.UTF_8 );
            HashMap<String, ArrayList<String>> map =  new HashMap<String, ArrayList<String>>();
            
            for( String line : (Iterable<String>) lines::iterator ) {
				/* use it as key */
				String sortedWord = sortString(line);
				
				/* get the values list from Map if word already exist */
				ArrayList<String> anagrams = map.get( sortedWord );
				
				/* create a new arraylist if there is not already existing one */
				if( anagrams == null ) anagrams = new ArrayList<String>();

				anagrams.add(line);
				map.put(sortedWord, anagrams);
            }

            for (ArrayList<String> name: map.values()) {
	            Collections.sort(name);
	            System.out.println(String.join(" ", name));
	        }        
        } catch (IOException ioe){
            ioe.printStackTrace();
        }

        /* To know the complete execution time */
        /* With 1100000 records execution time
         * with Printing on console : 1886 ms
         * without priniting : 730 ms
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
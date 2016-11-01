import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * GrepFile is responsible for the searching of a file to find lines
 * containing a given regex pattern. 
 * @author John
 *
 */
public class GrepFile implements Callable<Found> {
	
	private String filename;
	private String pattern;
	
	/**
	 * constructor, sets the pattern and file name
	 * @param pattern regex pattern to be searched for as a String
	 * @param filename file name to be searched as a String
	 */
	public GrepFile(String pattern, String filename){
		this.filename= filename;
		this.pattern=pattern;
	}

	@Override
	public Found call() {
		//create the found to return
		Found f = new Found(this.filename);
		
		//printing filename both for confirmation and to show results display based
		//on finishing time, not order of execution
		System.out.println(filename);
		
		
		//open file, create scanner to read it
		File file = new File(filename);
		
		Scanner sc=null;
		try {
			sc = new Scanner(file);
		} catch (FileNotFoundException e) {
			System.out.println("Error opening "+this.filename);
		}
		
		//while there are unread lines
		while(sc.hasNextLine()){
			//read the line, check for pattern
			String line=sc.nextLine();
			//System.out.println(line);
			Pattern p = Pattern.compile(pattern);
			Matcher m = p.matcher(line);
			boolean b = m.lookingAt();
			//if a match is found, add it to the Found object
			if(b){
				f.addMatch(line);
			}
		}
		
		//be kind, rewind
		sc.close();
		return f;
	}

}

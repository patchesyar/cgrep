import java.util.ArrayList;


/**
 * Found is the file which is returned upon completion of a GrepFile execution
 * it contains a list of the lines that matched the pattern and the file name
 * @author John
 *
 */
public class Found {

	private ArrayList<String> matches;
	private String filename;
	
	
	/**
	 * constructor sets file name and initializes the matched line list
	 * @param filename string representing name of the searched file
	 */
	public Found(String filename){
		this.filename=filename;
		this.matches= new ArrayList<String>();
	}
	
	/**
	 * Adds a line matching the pattern to the list
	 * @param line the string read in the file to be added to the list
	 */
	public void addMatch(String line){
		this.matches.add(line);
	}
	
	/**
	 * returns the file name as a String
	 * @return String representing file name
	 */
	public String getFile(){
		return this.filename;
	}
	
	
	/**
	 * returns the list of matched lines from the file
	 * @return ArrayList representing matched lines
	 */
	public ArrayList<String> getMatches(){
		return this.matches;
	}
}
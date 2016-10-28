package cgrep;
import java.util.ArrayList;

public class Found {

	private ArrayList<String> matches;
	private String filename;
	
	public Found(String filename){
		this.filename=filename;
	}
	
	public void addMatch(String line){
		this.matches.add(line);
	}
	
}

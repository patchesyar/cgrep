import java.util.ArrayList;
import java.util.concurrent.CompletionService;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorCompletionService;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CGrep {
	
	//max number of threads available in the pool
	public static final int MAX_THREADS=3;
	//service used to run tasks on a fixed pool of threads
	public static final ExecutorService es= Executors.newFixedThreadPool(MAX_THREADS);
	//completion service managing the thread pool to execute and complete in order
	public static final CompletionService ecs= new ExecutorCompletionService(es);
	
	
	/**
	 * main runs the program to search files for a pattern line by line
	 * @param args args[0] is the pattern to be searched for, args[1..n] are the files to be searched 
	 */
	public static void main(String[] args) {
		
		//determine the pattern
		String pattern= args[0];
		//List to hold all futures (returns task execution)
		ArrayList<Future> futures= new ArrayList<Future>();
		for(int i=1; i<args.length; i++){
			//Handles task execution, contains pattern and filename
			GrepFile grep= new GrepFile(pattern, args[i]);
			//submit the grep task to be run, add it to a list for easy access
			Future<Found> f= ecs.submit(grep);
			futures.add(f);
		}
		
		//pop from ecs's queue as the tasks complete
		for (int i=0; i<futures.size(); i++){
			try {
				
				Found f= (Found) ecs.take().get();
				
				System.out.println("Matches for file " +f.getFile());
				System.out.println("--------------------");				
				
				//iterate over matches found for the given file and print each
				ArrayList<String> matches = f.getMatches();
				for(String line: matches){
					System.out.println(line);
				}
				System.out.println("End of file "+f.getFile()+"\n");
				
			} catch (InterruptedException e) {
				System.out.println("Interrupted");
				e.printStackTrace();
			} catch (ExecutionException e) {
				System.out.println("Failure during execution");
				e.printStackTrace();
			}
		}
		
		
		
		es.shutdown();
	}

}
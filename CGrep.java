package cgrep;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CGrep {
	
	public static final int MAX_THREADS=3;
	public static final ExecutorService es= Executors.newFixedThreadPool(MAX_THREADS);
	
	
	public static void main(String[] args) {
		String pattern= args[0];
		String[] files= new String[args.length-1];
		for (int i=1; i<args.length; i++){
			files[i-1]=args[i];
		}
		
		
	}

}

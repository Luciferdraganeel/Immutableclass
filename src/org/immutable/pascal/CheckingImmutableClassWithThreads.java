package org.immutable.pascal;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class CheckingImmutableClassWithThreads {

	public static void main(String[] args) {

		
		
		// fixed the number of threads and then creating the executor to perform the action :
		int numberofthreads = 10;
		ExecutorService executor = Executors.newFixedThreadPool(numberofthreads);
		
		
		// Then creating the task "
		Callable<Immutable> task = ()-> Immutable.createInstance("iconic app", 10, 4.4,List.of("zizu","messi"));
		
		
		// store the future object after the execution of the executor in a list:
		List<Future<Immutable>> storagefromexecutors =  new ArrayList<>();
		
		
		// assigning the task to the number of threads we have created:
		for(int i = 0; i < numberofthreads; i++) {
			                        Future<Immutable> submittedreturns = executor.submit(task);
			                        storagefromexecutors.add(submittedreturns);
		}
		
		// checking the first future is same others:
		
		                try {
		                	         Immutable firstref = storagefromexecutors.get(0).get();
		                	         boolean flag = true;
		                	         for(int i = 1; i< storagefromexecutors.size(); i++) {
		                	        	           Immutable immutable = storagefromexecutors.get(i).get();
		                	        	           if(immutable != firstref) {
		                	        	        	       flag = false;
		                	        	        	       break;
		                	        	           }
		                	         }
		                }
		                catch(Exception ex ) {
		                	
		                }
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
			                          

	}

}

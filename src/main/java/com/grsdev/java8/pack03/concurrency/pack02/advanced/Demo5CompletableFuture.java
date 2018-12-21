package com.grsdev.java8.pack03.concurrency.pack02.advanced;

import static java.lang.System.out;

import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.function.Supplier;

public class Demo5CompletableFuture {
	
	private static void runAsyncExample1() throws InterruptedException, ExecutionException {
		Runnable runnable = () -> {
			
			System.out.println(Thread.currentThread().getName()  +" : Sleeping ... ");
			try {
				Thread.sleep(5000);
			}catch(InterruptedException ex) {
				ex.printStackTrace();
			}finally {
				out.println(Thread.currentThread().getName() + " : returning ...");
			}
			
			
		};
		
		CompletableFuture<Void> future = CompletableFuture.runAsync(runnable);
		out.println("In Main ...");
		future.whenComplete((a,b) -> out.println("processing complete")); 
		future.get();
	}

	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		
		Supplier<String> supplier = () -> {
			try {
				Thread.sleep(5000);
			}catch(InterruptedException ex) {
				ex.printStackTrace();
			}
			return "Mango";
		};

		CompletableFuture<String> future = CompletableFuture.supplyAsync(supplier);
		out.println(">>> future.get() - "+future.get());
		
		/*Callable<Integer> callable = () -> 1;
		CompletableFuture.supplyAsync(callable);*/
	}

}

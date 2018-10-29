package com.grsdev.java8.pack03.concurrency.pack02.advanced;

import static java.lang.System.out;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Demo2ExecutorService {

	public static void runRunnables() {
		ExecutorService executor = null;

		try {
			executor = Executors.newFixedThreadPool(10);

			Runnable runnable = () -> out.println("ran from : "+ Thread.currentThread().getName());

			executor.submit(runnable);
			executor.submit(runnable);
		}finally{
			executor.shutdown();
		}
	}
	
	public static void submitCallables() throws InterruptedException, ExecutionException {
		
		ExecutorService executor = Executors.newSingleThreadExecutor();
		
		Callable<String> callable = ()-> {
			
			out.println("Java is best !!!");
			return "Java is best";
		};
		
		Future<String> future = executor.submit(callable);
		executor.shutdown();
		out.println("future >>>"+ future.get());
		
		
	}

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		
		submitCallables();
	}

}

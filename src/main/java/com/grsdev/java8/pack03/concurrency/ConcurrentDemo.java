package com.grsdev.java8.pack03.concurrency;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ConcurrentDemo {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		
		Callable<Integer> callable = ()-> (int)(Math.random()*100);
		
		ExecutorService es = Executors.newFixedThreadPool(1);
		
		Future<Integer> future = es.submit(callable);
		
		while(!future.isDone()) {
			
		}
		
		Integer num = future.get();
		System.out.println("num : "+ num);
		es.shutdown();
	}

	private static void oldThreadStyle() {
		Runnable runnable =  () -> System.out.println("I ran !!!");
		
		Thread thread = new Thread(runnable);
		
		thread.start();
		
		System.out.println("Main ended");
	}

}

package com.grsdev.java8.pack03.concurrency.pack02.advanced;

import static java.lang.System.out;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Demo6AllBasics {
	
	public static void runTaskInThread() {
		
		Runnable r = () -> {
			
			out.println("Separate thread : "+Thread.currentThread().getName());
			try {
				Thread.sleep(3000); 
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		};
		
		
		Thread t = new Thread(r, "MyThread");
		t.start();
	}
	
	public static void sleepNow(int time) {
		try {
			Thread.sleep(time); 
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	static class MyTask extends Thread{
		
		@Override
		public void run() {
			
			out.println("Running in separate thread : "+Thread.currentThread().getName());
			sleepNow(3000);
		}
	}
	
	public static void runInSeparateThreadByExtending() {
		
		Demo6AllBasics demo = new Demo6AllBasics();
		
		MyTask myTask = new MyTask();
		
		myTask.start();
		
	}
	
	public static void runRunnableTaskUsingExecutorService() {
		
		ExecutorService es = Executors.newSingleThreadExecutor();
		
		Runnable runnable = () -> {
			
			out.println(">>> Running in thread : "+ Thread.currentThread().getName());
			sleepNow(4000);
		};
		
		es.submit(runnable);
		es.shutdown();
	}
	
	public static Future<String> runCallableUsingExecutorService() {
		
		ExecutorService es = Executors.newCachedThreadPool();
		
		Callable<String> callable = () ->{
			
			out.println(">>> running in separate thread : "+ Thread.currentThread().getName());
			sleepNow(3000);
			return "Mango";
		};
		
		Future<String> future = es.submit(callable);
		return future;
	}
	
	public static void invokeCollectionOfCallable() throws InterruptedException, ExecutionException {
		
		List<Callable<Integer>> callables = new ArrayList<>();
		
		Callable<Integer> callable1 = () -> {
			int i=8; out.println("returning : "+ i ); sleepNow(3000); return i;
		};
		
		Callable<Integer> callable2 = () -> {
			int i=10; out.println("returning : "+ i ); sleepNow(3000); return i;
		};
		
		Callable<Integer> callable3 = () -> {
			int i=4; out.println("returning : "+ i );  sleepNow(1000); return i;
		};
		
		Callable<Integer> callable4 = () -> {
			int i=1; out.println("returning : "+ i ); sleepNow(3000); return i;
		};
		
		callables.addAll(Arrays.asList(callable1,callable2,callable3,callable4));
		
		ExecutorService es = Executors.newCachedThreadPool();
	
		/*List<Future<Integer>> results = es.invokeAll(callables);
		results.stream()
		   .map(t -> {
			try {
				return t.get();
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
				return null;
			}
		  }).forEach(out::println);
	*/
		
		try {
			long startTime = System.currentTimeMillis();
			Integer result = es.invokeAny(callables);
			out.println(">>> result is : "+ result +" , time taken : "+ (System.currentTimeMillis() - startTime));
		}finally {
			es.shutdown();
		}
		
		
	}
	
	public static void usingCompletableFutureRunRunnable() throws InterruptedException, ExecutionException {
		
		Runnable runnable = ()-> {
			
			out.println(Thread.currentThread().getName() + ">>> running in thread");
			sleepNow(3000);
		};
		
		CompletableFuture<Void> cf1 = CompletableFuture.runAsync(runnable);
		out.println(Thread.currentThread().getName()+" : result -" + cf1.get());
		
	}
	
	public static void main(String[] args) throws InterruptedException, ExecutionException {
		
		usingCompletableFutureRunRunnable();
		out.println(Thread.currentThread().getName() + " : in main");
	}
	

}

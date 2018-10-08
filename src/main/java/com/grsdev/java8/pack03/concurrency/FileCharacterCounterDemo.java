package com.grsdev.java8.pack03.concurrency;

import static java.lang.System.out;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.function.Function;

public class FileCharacterCounterDemo {

	private static void invokeMethod(String styleName,Function<String [],Long> function,String [] files) {
		
		Timer timer = Timer.getNew();
		timer.startTimer();
		Long totalChars = function.apply(files);
		long time = timer.getTimeElapsedInMilliSec();
		
		out.printf("%s - total chars : %d , elasped time : %d %n", styleName,totalChars,time);
		
	}
	
	
	private static long findTotalCharsInFilesUsingOnlyMainThread(String ... files){
			
			long totalChars=0;
			
			for(int i=0; i<files.length; i++) {
				
				try(BufferedReader br = new BufferedReader(new FileReader(files[i]))){
					String line=null;
					while((line=br.readLine())!=null) {
						totalChars += line.length();
					}
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				
			}
			
			return totalChars;
		}

	private static long findTotalCharsInFilesUsingManyThreads(String ...files){
		
		FutureTask [] tasks = new FutureTask[files.length];
		
		for(int i=0;i<files.length;i++) {
			
			String file = files[i];
			
			Callable<Integer> callable = () -> {
				
				try(BufferedReader br= new BufferedReader(new FileReader(file))){
					
					String line=null; int charCount=0;
					while((line=br.readLine())!=null) {
						charCount += line.length();
					}
					
					return charCount;
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				return 0;
			};
			
			FutureTask<Integer> futureTask = new FutureTask<>(callable);
			tasks[i]=futureTask;
			Thread th = new Thread(futureTask);
			th.start();
		}
		
		long totalChars=0;
		
		for(FutureTask<Integer> task : tasks) {
			try {
				totalChars += task.get();
			} catch (InterruptedException | ExecutionException e) {
				e.printStackTrace();
			}
		}
		
		return totalChars;
	}


	private static void runOldStyle100Threads() {
		
		Runnable r = () -> 	System.out.println("Running in thread : "+ Thread.currentThread().getName());

		for(int i=0; i < 100 ;i++) {
			
			Thread t = new Thread(r);
			t.start();
		}
	}
	
	public static long findTotalCharsInFilesUsingStreams(String [] files) {
		
		long total=
		Arrays.stream(files)
			  .map(Paths::get)
			  .flatMap(path-> {
				try {
					return Files.lines(path);
				} catch (IOException e) {
					e.printStackTrace();
					return new ArrayList<String>().stream();
				}
			})
			  .mapToLong(String::length)
			  .sum();
		
		return total;
	}
	
	public static long findTotalCharsInFilesUsingParallelStreams(String [] files) {
			
			long total=
			Arrays.stream(files).parallel()
				  .map(Paths::get)
				  .flatMap(path-> {
					try {
						return Files.lines(path);
					} catch (IOException e) {
						e.printStackTrace();
						return new ArrayList<String>().stream();
					}
				})
				  .mapToLong(String::length)
				  .parallel()
				  .sum();
			
			return total;
		}
	
	public static void main(String[] args) throws Exception {
		
		final String BASE="C:/Users/salvigau/Downloads/";
		
		final String file1 = BASE+"logs1.txt",file2 = BASE+"logs2.txt",file3 = BASE+"logs3.txt",file4 = BASE+"logs4.txt",file5 = BASE+"logs5.txt";
		
		String [] files = {file1,file2,file3,file4,file5};
		
//		invokeMethod("findTotalCharsInFilesUsingOnlyMainThread", (t) -> findTotalCharsInFilesUsingOnlyMainThread(t),files);
//		invokeMethod("findTotalCharsInFilesUsingManyThreads", (t) -> findTotalCharsInFilesUsingManyThreads(t),files);
//		invokeMethod("findTotalCharsInFilesUsingStreams", (t) -> findTotalCharsInFilesUsingStreams(t),files);
		invokeMethod("findTotalCharsInFilesUsingParallelStreams", (t) -> findTotalCharsInFilesUsingParallelStreams(t),files);
		
	}
	

}

class Timer {
	
	private long startTime;

	public static Timer getNew() {
		return new Timer();
	}
	
	public void startTimer() {
		startTime=System.nanoTime();
	}
	
	public long getTimeElapsedInSec() {
		
		long endTime = System.nanoTime();
		
		long timeElapsed = ((endTime-startTime)/(1000*1000*1000));
		
		return timeElapsed;
	}

	public long getTimeElapsedInMilliSec() {
		
		long endTime = System.nanoTime();
		
		long timeElapsed = (endTime-startTime)/(1000*1000);
		
		return timeElapsed;
	}
}

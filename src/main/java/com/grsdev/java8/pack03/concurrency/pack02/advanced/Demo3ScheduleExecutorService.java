package com.grsdev.java8.pack03.concurrency.pack02.advanced;

import static java.lang.System.out;

import java.time.Instant;
import java.time.LocalTime;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Demo3ScheduleExecutorService {

	public static void scheduleAtFixedTime () {

		ScheduledExecutorService ses = Executors.newSingleThreadScheduledExecutor();

		Runnable command = () -> out.println("running ...");

		ses.schedule(command, 5, TimeUnit.SECONDS);
		ses.shutdown();
	}

	public static void scheduleAtFixedRate() {

		ScheduledExecutorService ses = Executors.newSingleThreadScheduledExecutor();

		Runnable task = () -> {
			out.println("running ... : "+LocalTime.now());
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		};
		ses.scheduleAtFixedRate(task, 1, 1, TimeUnit.SECONDS);

		try {
			Thread.sleep(20000000);
		}catch(InterruptedException ex) {

		}finally {
			ses.shutdown();
		}
	}

	public static void scheduleAtFixedDelay() {
		
		final ScheduledExecutorService es = Executors.newSingleThreadScheduledExecutor();
		
		try {
			
			Runnable command = () ->{ 
				out.println(">>> running : "+Instant.now());
				try {
					Thread.sleep(5000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			};
			
			es.scheduleWithFixedDelay(command, 1, 1, TimeUnit.MILLISECONDS);
			Thread.sleep(100000);
		}catch(InterruptedException ex) {
			
		}finally {
			es.shutdown();
		}
		
	}

	public static void main(String[] args) {
		
		scheduleAtFixedDelay();
	}
}

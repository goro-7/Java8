package com.grsdev.java8.utils;

/**
 * @author gaurav salvi
 *
 */
public class TimerUtil {
	
	private static long startTime;

	public static void startTime() {
		startTime=System.nanoTime();
	}

	public static long showTimeElapsed(TimeUnit unit) {
		
		long timeElapsed=System.nanoTime() - startTime;
		
		long divisor=1; // for nano sec
		
		switch(unit){
		
			case NSEC: break;
			
			case MIN : divisor*=3600;
			
			case SEC : divisor*=1000;
		
			case MSEC: divisor*=1000000L; break;
		}
		
		long timeElapsedChanged=timeElapsed/divisor;
		
//		System.out.println("Time elpased : " + timeElapsed/divisor+" "+ unit.name().toLowerCase());
		return timeElapsedChanged;
	}
	
	public enum TimeUnit {NSEC,MSEC,SEC,MIN};

}

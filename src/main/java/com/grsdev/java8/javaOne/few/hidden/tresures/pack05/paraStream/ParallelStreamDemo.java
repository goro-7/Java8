package com.grsdev.java8.javaOne.few.hidden.tresures.pack05.paraStream;

import java.util.stream.IntStream;

public class ParallelStreamDemo {

	public static void main(String[] args) {
		
		IntStream.rangeClosed(1, Integer.MAX_VALUE)
		.parallel()
		.max()
		.ifPresent(System.out::println);
	
	}
	
	public static int doubleIt(int num) {
		return num*2;
	}

}

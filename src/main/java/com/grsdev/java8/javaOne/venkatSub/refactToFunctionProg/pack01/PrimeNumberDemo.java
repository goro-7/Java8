package com.grsdev.java8.javaOne.venkatSub.refactToFunctionProg.pack01;

import java.util.stream.IntStream;

public class PrimeNumberDemo {

	public static void main(String[] args) {
		
		for(int i=0;i<=6;i++) {
			System.out.printf("%d is prime number : %b %n", i,isPrime2(i));
		}
		
	}

	private static boolean isPrime2(int num) {
		
		return IntStream.range(2,num).noneMatch(t->num%t==0) && num>1; 
	}

	private static boolean isPrime1(int num) {
		
		boolean divisible=false;
		
		for(int i=2;i<num;i++) {
			if(num%i==0)
				divisible=true;
		}
		
		return num>1&&!divisible;
	}
	
	

}

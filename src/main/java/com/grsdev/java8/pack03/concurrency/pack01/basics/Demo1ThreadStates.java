package com.grsdev.java8.pack03.concurrency.pack01.basics;

import static java.lang.System.out;

public class Demo1ThreadStates {

	public static void main(String[] args) throws InterruptedException {
		
		Thread t = new Thread(()->out.println("running ..."));
		out.println(">>> thread created new , state: "+t.getState());
		t.start();
		out.println(">>> start invoked on thread, state : "+t.getState());
		t.join();
		
		out.println(">>> thread completed running, state : "+t.getState());
		
	}

}

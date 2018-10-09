package com.grsdev.java8.pack03.concurrency;

import static java.lang.System.out;

public class ThreadDemo {

	public static void main(String[] args) throws InterruptedException {
		
		MyThread th = new MyThread();
		th.start();
		
		
		out.println(Thread.currentThread() + " has ended");
	}

}


class MyThread extends Thread{
	
	@Override
	public void run() {
		try {
			Thread.sleep(3*1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		out.println(Thread.currentThread() .getName()+ " ran");
	}
	
}
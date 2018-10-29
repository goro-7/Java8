package com.grsdev.java8.pack03.concurrency.pack01.basics;

import static java.lang.System.out;

public class ThreadDemo {

	public static void main(String[] args) throws InterruptedException {
		
		Thread t = new Thread(new MyThread());
		t.start();
		
		out.println("Main ended");
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
package com.grsdev.java8.pack03.concurrency.pack02.advanced;

import static java.lang.System.out;

import java.util.LinkedList;
import java.util.Queue;

public class Demo1Executor {

	public static void main(String[] args) {

		Runnable r = () -> out.println("ran using thread : "+ Thread.currentThread().getName());

		MySingleThreadPool.execute(r);
		
		Runnable r1 = () -> out.println("ran using thread : "+ Thread.currentThread().getName());

		MySingleThreadPool.execute(r1);
	}

}

class MySingleThreadPool {

	private static boolean run = false;

	private final static Thread t = new Thread("jon") {

		@Override
		public void run() {

			while(true){

				while(!run)

					synchronized(runnables) {
						try {
							runnables.wait();
						} catch (InterruptedException e) {
							e.printStackTrace();
						}
					}
				Runnable r =null;
				while((r = runnables.poll())!=null) {
					r.run();
				}
				run=false;
			}
		}

	};
	
	static {
		t.start();
	}

	private final static Queue<Runnable> runnables = new LinkedList<>();


	public static void execute(Runnable runnable) {
		runnables.add(runnable);
		run = true;
		synchronized(runnables) {
			runnables.notifyAll();
		}
	}


}
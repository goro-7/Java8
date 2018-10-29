package com.grsdev.java8.pack03.concurrency.pack01.basics;

import static java.lang.System.out;

public class ThreadWaitDemo {

	public static void main(String[] args) {

		MyObject o = new MyObject();

		Runnable r = new Runnable() {

			@Override
			public void run() {

				out.println("i will wait for notify event");

				synchronized(o) {
					try {
						while(o.isWait())
							o.wait();
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
				out.println("i ran !!!");
			}
		};

		Thread t = new Thread(r);

		t.start();


		synchronized(o) {
			o.setWait(false);
			o.notifyAll();
		}

		try {
			t.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}


class MyObject {
	
	private boolean wait = true;

	public boolean isWait() {
		return wait;
	}

	public void setWait(boolean wait) {
		this.wait = wait;
	}
	
	
}
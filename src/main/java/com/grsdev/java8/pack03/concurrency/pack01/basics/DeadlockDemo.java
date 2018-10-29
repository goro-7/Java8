package com.grsdev.java8.pack03.concurrency.pack01.basics;

import static java.lang.System.out;

public class DeadlockDemo {

	public static void main(String[] args) {

		Friend jack = new Friend("jack");
		Friend tom = new Friend("tom");
		
		Runnable r1 = () -> jack.bow(tom);
		
		Runnable r2 = () -> tom.bow(jack);
		
		new Thread(r1).start();
		new Thread(r2).start();
		
	}

}

class Friend{
	
	private final String name;
	
	Friend(String name){
		this.name=name;
	}

	public String getName() {
		return name;
	}
	
	public synchronized void bow(Friend friend) {
		
		out.printf("%s is bowing to %s %n",getName(),friend.getName());
		
		try {
			Thread.sleep(5000);
		}catch(InterruptedException ex) {
			ex.printStackTrace();
		}
		
		out.printf("%s has completed bowing to %s %n",getName(),friend.getName());

		out.printf("%s is waiting for %s to bow back %n",getName(),friend.getName());
		
		friend.bow(this);
	}
	
}

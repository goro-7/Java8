package com.grsdev.java8.pack03.concurrency.pack01.basics;

import static java.lang.System.out;

import java.util.Arrays;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

public class ThreadSleepDemo {

	public static void main(String[] args) throws InterruptedException {
		
		String [] msgs = {"roses are red","violets are below","java is pass by value","java is best"};
		
		MessagePrinter mp = new MessagePrinter(msgs);
		Thread t = new Thread(mp);
		
		msgs[3]= "Eit Voila !!!";
		
		t.start(); t.interrupt();
		
	}
	
}

class MessagePrinter implements Runnable{
	
	private String [] msgs = {"mango","kiwi","strawberry"};
	
	public MessagePrinter(String [] msgs) {
		this.msgs=msgs;
	}
	
	
	@Override
	public void run() {
		
		if(msgs!=null) {
			for(int i=0; i< msgs.length; i++) {
				out.println(msgs[i]);
				try {
					Thread.sleep(1);
				} catch (InterruptedException e) {
					msgs = new String [5];
					i=0;
					msgs[i] =  "apple"; i++;
					msgs[i] =  "kiwi"; i++;
					msgs[i] =  "strawberry"; i++;
					msgs[i] =  "pomogranate"; i++;
					for(int j=0; j<msgs.length; j++) {
						out.println(msgs[j]);
					}
				}
			}
			
		}
	}
}

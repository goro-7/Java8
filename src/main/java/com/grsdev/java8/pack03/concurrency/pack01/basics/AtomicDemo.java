package com.grsdev.java8.pack03.concurrency.pack01.basics;

import static java.lang.System.out;

public class AtomicDemo {
	
	public static void main(String[] args) throws InterruptedException {
		
		Product mobile = new Product("mobile", 0);
		out.println(mobile);
		
		SimpleThread st1 = new SimpleThread(mobile);
		st1.start();
		
		SimpleThread st2 = new SimpleThread(mobile);
		st2.start();
		
		SimpleThread st3 = new SimpleThread(mobile);
		st3.start();
		
		st1.join(); st2.join(); st3.join();
		
		out.println(mobile);
		
	}
	
	static void createThreadAndRun(Runnable r) {
		new Thread(r).start();
	}

}

class Product{
	
	private final String name;
	
	private int quantity;

	public Product(String name, int quantity) {
		this.name=name;
		this.quantity=quantity;
	}

	public synchronized long getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
	public synchronized void incrementQuantity() {
		quantity++;
	}

	@Override
	public String toString() {
		return "Product [name=" + name + ", quantity=" + quantity + "]";
	}
}

class SimpleThread extends Thread{
	
	private Product p;
	
	SimpleThread(Product p){
		this.p=p;
	}
	
	@Override
	public void run() {
		
		for(int i=0;i<10;i++){
			p.incrementQuantity();
			out.println(Thread.currentThread().getName() +" - "+p.getQuantity());
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
}

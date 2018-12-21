package com.grsdev.java8.pack06.collections;

import static java.lang.System.out;

import java.util.LinkedList;
import java.util.Queue;
import java.util.stream.IntStream;

public class QueueDemo {

	public static void main(String[] args) {
		
		Queue<String> queue = new LinkedList<>();
		
		IntStream.rangeClosed(1, 1000).boxed().forEach(e->queue.offer(String.valueOf(e)));
		
		out.println(queue);
		
		String poll1 = queue.poll(); 
		out.println("poll1 : "+ poll1);
		out.println(queue);
		
		queue.remove("10");
		out.println(queue);
		out.println("contains 12 : "+queue.contains("12"));
	}

}

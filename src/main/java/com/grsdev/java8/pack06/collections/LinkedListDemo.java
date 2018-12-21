package com.grsdev.java8.pack06.collections;

import static java.lang.System.out;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.LongStream;

import com.grsdev.java8.utils.TimerUtil;
import com.grsdev.java8.utils.TimerUtil.TimeUnit;

public class LinkedListDemo {
	
	public static void main(String[] args) {
		
		List<Long> list = new LinkedList<>();
		
		fillList(list,10000000);
		
		list.remove(1);
		out.println("Time taken to remove element : "+ TimerUtil.showTimeElapsed(TimeUnit.MSEC));
	}

	private static void fillList(List<Long> list,long size) {
		
		LongStream.rangeClosed(1, size).forEach(t->list.add(t));
		out.println(">>> list filled");
	}
}

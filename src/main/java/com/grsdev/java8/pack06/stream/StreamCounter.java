package com.grsdev.java8.pack06.stream;

import static java.lang.System.out;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class StreamCounter {

	public static void main(String[] args) {
		
		List<String> fruits = Arrays.asList("apple","mango","apple","pear");
		
		out.println(fruits.stream().collect(Collectors.groupingBy(Function.identity(), LinkedHashMap::new, Collectors.counting())));
	}

}

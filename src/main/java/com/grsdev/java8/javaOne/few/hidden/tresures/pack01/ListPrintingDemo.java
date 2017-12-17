package com.grsdev.java8.javaOne.few.hidden.tresures.pack01;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ListPrintingDemo {

	public static void main(String[] args) {
		
		List<String> namesList=Arrays.asList("Tom","Jerry","Jane","Jack");
		
		String names=namesList.stream().map(String::toUpperCase).collect(Collectors.joining(","));
		
		Utils.println(names);
		
	}

}

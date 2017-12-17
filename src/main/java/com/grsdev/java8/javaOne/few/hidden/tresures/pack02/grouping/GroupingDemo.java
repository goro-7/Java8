package com.grsdev.java8.javaOne.few.hidden.tresures.pack02.grouping;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.grsdev.java8.javaOne.few.hidden.tresures.pack02.sorting.Person;

public class GroupingDemo {

	public static void main(String[] args) {
		
		Function<Person,Object> classifier=null;
		
		classifier=p->p.getAge();
		
		Map<Object, List<Person>> groups = 
	    Person.createListOfPerson()
			  .stream()
			  .collect(Collectors.groupingBy(classifier));
		
		groups.entrySet().forEach(System.out::println);
		
	}

}

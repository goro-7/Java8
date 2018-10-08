package com.grsdev.java8.javaOne.few.hidden.tresures.pack04.grouping;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.grsdev.java8.javaOne.few.hidden.tresures.pack03.sorting.Person;

public class GroupingDemo {

	public static void main(String[] args) {
		
		Function<Person,Object> classifier=null;
		
		classifier=p->p.getAge();
		
		Map<Object, List<Person>> groups = 
	    Person.createListOfPerson()
			  .stream()
			  .collect(Collectors.groupingBy(classifier));
		
		groups.entrySet().forEach(System.out::println);
		
		
		System.out.println(
				
			Person.createListOfPerson()
			.stream()
			.collect(Collectors.groupingBy(Person::getAge, Collectors.mapping(Person::getName, Collectors.toList())))
				
		);
		
		Map<Boolean,List<Person>> 
		groupByPredicate=
		Person.createListOfPerson()
		.stream()
		.collect(Collectors.partitioningBy(p->p.getAge()<50));
		
		System.out.println(">>> Person with age less 50 ");
		System.out.println(groupByPredicate.get(Boolean.TRUE));
		System.out.println(">>> Person with age greater than 50 ");
		System.out.println(groupByPredicate.get(Boolean.FALSE));

		
		Map<Integer, Long> collect = Person.createListOfPerson()
				
		.stream()
		.collect(Collectors.groupingBy(Person::getAge, Collectors.counting()));
		
		
		System.out.println(collect);
		
		IntStream.generate(()->((int)(Math.random()*100))).limit(200).forEach(System.out::println);
		
	}

}

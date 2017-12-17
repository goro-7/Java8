package com.grsdev.java8.javaOne.few.hidden.tresures.pack02.sorting;

import java.util.Comparator;
import java.util.List;

public class SortingDemo {

	public static void main(String[] args) {
		
		List<Person> persons = Person.createListOfPerson();
		
		Comparator<Person> comparator= Comparator
									   .comparingInt(Person::getAge)
									   .thenComparing(Person::getName);
		
		persons.stream().sorted().forEach(System.out::println);
		System.out.println("==========================");
		persons.forEach(System.out::println);
	}

}
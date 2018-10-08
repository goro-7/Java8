package com.grsdev.java8.javaOne.few.hidden.tresures.pack03.sorting;

import java.util.Arrays;
import java.util.List;

public class Person implements Comparable<Person>{
	String name;
	int age;
	
	public Person(String name, int age) {
		super();
		this.name = name;
		this.age = age;
	}
	

	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public int getAge() {
		return age;
	}



	public void setAge(int age) {
		this.age = age;
	}



	@Override
	public String toString() {
		return String.format("{name:%-8s, age:%-3d}",name,age);
	}
	
	public static List<Person> createListOfPerson(){
		
		return Arrays.asList(new Person("Annie",20),new Person("Bob",30),new Person("Bito",31),
							 new Person("Janu",20), new Person("Jill",56),
							 new Person("Monalisa",55), new Person("Victor",30), 
							 new Person("Mark", 66), new Person("Alisha",30) , new Person("Mark",67));
		
	}

	@Override
	public int compareTo(Person o) {
		return this.name.compareTo(o.name);
	}
}
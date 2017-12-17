package com.grsdev.java8.javaOne.few.hidden.tresures.pack02.defaultMethod;

public class DefaultMethodDemo {

	public static void main(String[] args) {
		
		FastFlying ff=new FastFlying() { public void stop() { System.out.println("class : stop");}};
		
		ff.flyWithNoise();
		
		ff.stop();
		
		
		JetFlying jf=new JetFlying() {};
		
		jf.fly();
		jf.stop();
	}

}


// default method rules :

// 1. default methods can be inherited by direct sub interface

// 2. a sub-interface can override super interface default method

// 3. default method cannot have same signature as public methods of Object class

// 4. if two super interfaces have same default method then sub interface must specify which super interface method to use

// 5. if a method is defined in any level of class hierarchy then default method is ignored


interface Flying{
	
	default void fly() {
		System.out.println("Flying >>> fly");
	}
	
	default void flyWithNoise() {
		System.out.println("Flying:flyWithNoise");
	}
	
	default void stop() {
		System.out.println("Flying : stop");
	}
	
}

interface FastFlying extends Flying,Landable{
	
	default void makeSound() {
		System.out.println("FastFlying : makeSound");
		fly();
	}
	
	@Override
	default void flyWithNoise() {
		System.out.println("FastFlying:flyWithNoise");
	}

	@Override
	default void stop() {
		Landable.super.stop();
	}
}

interface Landable{
	
	default void stop() {
		System.out.println("Landable : stop");
	}
}

interface JetFlying extends FastFlying{
	
}

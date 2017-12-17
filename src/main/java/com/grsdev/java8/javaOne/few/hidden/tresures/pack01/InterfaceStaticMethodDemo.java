package com.grsdev.java8.javaOne.few.hidden.tresures.pack01;

public class InterfaceStaticMethodDemo {

	public static void main(String[] args) {
			
		Utils.println(Utils.numOfCores());
		
		Utils utils=new UtilsImpl();
		
	}

}

class UtilsImpl implements Utils{
	
}

interface Utils {
	
	static int numOfCores() {
		return Runtime.getRuntime().availableProcessors();
	}
	
	static void println(Object o) {
		System.out.println(o);
	}
	
}

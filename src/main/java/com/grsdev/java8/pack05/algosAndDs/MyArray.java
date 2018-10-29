package com.grsdev.java8.pack05.algosAndDs;

import static java.lang.System.out;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

import com.grsdev.java8.utils.TimerUtil;
import com.grsdev.java8.utils.TimerUtil.TimeUnit;

public class MyArray {

	private int [] array;

	private int maxSize;

	private int size;

	public MyArray(int maxSize,int generateDataOfSize,int maxGeneratedNumber) {
		this.maxSize = maxSize;
		this.array = new int[maxSize];
		generateData(generateDataOfSize,maxGeneratedNumber);
	}

	private void generateData(int generateDataOfSize, int maxGeneratedNumber) {

		size = generateDataOfSize;

		for(int i=0; i<size;i++) {
			array[i] = (int)(Math.random()*maxGeneratedNumber);
		}
	}

	public void print() {

		String dashes = "-----------";

		out.println(dashes);
		for(int i=0; i<size; i++) {
			out.printf("| %2d | %2d | %n", i, array[i]);
			out.println(dashes);
		}
	}

	public void insert(int element) {

		if(size<=50) {
			array[size] = element;
			size++;
		}
	}

	public String linearSearch(int element) {

		String indexes="";

		for(int i=0; i<size; i++) {
			if(array[i]==element) {
				indexes+=i+" ";
			}
		}

		return indexes;
	}

	public void bubbleSort() {

		int count=0;

		while(true) {
			count++;
			boolean swapped = false;

			for(int i=0; i < size - 1 ; i++) {

				if(array[i]>array[i+1]) {
					swapped=true;
					swap(i,i+1);
					printArrayHori();
				}
			}

			if(!swapped) {
				break;
			}
		}
		out.println("total iterations used : "+count);

	}

	private void swap(int i, int j) {

		int temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}

	public void printArrayHori() {

		final char dash = '-';	final int dashLength = size * 4;

//		printChars(dash, dashLength);

		for(int i=0; i<size; i++) {
			out.printf("| %d ", array[i]);
		}
		out.print("|");
		out.println();

//		printChars(dash, dashLength);
	}

	private void printChars(final char dash, final int dashLength) {
		for(int i=0; i< dashLength ; i++) {
			out.print(dash);
		}
		out.println();
	}

	public static void main(String[] args) {

		MyArray myArray = new MyArray(Byte.MAX_VALUE ,Byte.MAX_VALUE ,20);
		myArray.printArrayHori();

		TimerUtil.startTime();
		myArray.bubbleSort();
		myArray.printArrayHori();
		out.println("total time used in msec : "+ TimerUtil.showTimeElapsed(TimeUnit.MSEC));
	}

}

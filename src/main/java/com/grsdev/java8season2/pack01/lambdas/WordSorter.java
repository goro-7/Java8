package com.grsdev.java8season2.pack01.lambdas;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import com.grsdev.java8season2.utils.TimerUtil;
import com.grsdev.java8season2.utils.TimerUtil.TimeUnit;

import static java.lang.System.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * @author gaurav salvi
 *
 */
public class WordSorter {

	private static List<String> words;

	static 
	{
		try {
			words=Files.readAllLines(Paths.get("C:/Users/salvigau/Desktop/server_logs/Esign/DIT1/allLines.txt"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}



	public static void main(String [] args){
		
		int iteration=1000;
		
		long [] obs=new long[iteration];
		
		for(int i=0;i<iteration;i++){
		
		out.println(">>> iteration = "+i);	
		TimerUtil.startTime();
		
//		List<String> sortedWords1=sortListStyle1(words);

		List<String> sortedWords2=sortListStyle2(words);
		
//		List<String> sortedWords3=words.stream().sorted(Comparator.comparing(String::length)).collect(Collectors.toList());
		
//		List<String> sortedWords4=words.stream().parallel().sorted(Comparator.comparing(String::length)).collect(Collectors.toList());

		obs[i]=TimerUtil.showTimeElapsed(TimeUnit.NSEC);
		
		}
		
		Double avg=Arrays.stream(obs).average().getAsDouble();
		
		out.println("--> aveage = "+avg/1000000);
		
	}

	private static List<String> sortListStyle2(List<String> words2) {
		List<String> sortedWords=new ArrayList<>(words);

		Comparator<String> comparator=new Comparator<String>() {

			@Override
			public int compare(String s1, String s2) {
				return s2.length() - s1.length();
			}
			
		};

		Collections.sort(sortedWords,comparator);

		return sortedWords;
	}


	private static List<String> sortListStyle1(List<String> words) {

		List<String> sortedWords=new ArrayList<>(words);

		WordComparator comparator=new WordComparator();

		Collections.sort(sortedWords,comparator);

		return sortedWords;
	}

}


class WordComparator implements Comparator<String>{

	@Override
	public int compare(String s1,String s2){
		int diff=s2.length() - s1.length();
		return diff;
	}
}


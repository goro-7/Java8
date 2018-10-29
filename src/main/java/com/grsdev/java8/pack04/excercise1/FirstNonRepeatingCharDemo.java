package com.grsdev.java8.pack04.excercise1;

import static java.lang.System.out;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import com.grsdev.java8.utils.TimerUtil;
import com.grsdev.java8.utils.TimerUtil.TimeUnit;

import java.util.Set;

public class FirstNonRepeatingCharDemo {
	
	
	public static char findFirstNonRepeatingCharUsingMap(String text) {
		
		char[] chars = text.toCharArray();
		
		Map<Character,Integer> map = new LinkedHashMap<>(chars.length);
		
		for(int i=0; i<chars.length; i++) {
			Integer count = map.get(chars[i]);
			if(count==null) {
				map.put(chars[i],1);
			}else {
				map.put(chars[i],++count);
			}
		}
		
		for(Entry<Character,Integer> entry :  map.entrySet()) {
			if(entry.getValue()<2) {
				return entry.getKey();
			}
		}
		
		return '-';
	}
	
	public static char findFirstNonRepeatingCharUsingSet(String string) {
		
		Set<Character> repeatSet = new HashSet<>(string.length());
		
		char[] chars = string.toCharArray();
		
		for(int i=0; i<chars.length ; i++) {
			
			char c = chars[i];
			
			if(repeatSet.contains(c)) {
				continue;
			}
			
			for(int j=i+1; j<chars.length; j++) {
				
				if(c == chars[j]) {
					repeatSet.add(c);
					break;
				}
			}
			
			if(!repeatSet.contains(c)) {
				return c;
			}
			
		}
		
		
		return '-';
	}
	
	
	public static void main(String[] args) throws IOException {
		
		String string = 
						Files.lines(Paths.get("C:/Users/salvigau/Downloads/logs1.txt")).collect(Collectors.joining()); 
						//"welcomewelcoe";
		
		TimerUtil.startTime();
		out.println(findFirstNonRepeatingCharUsingMap(string));
		out.printf("time elapsed : %,d msec%n",TimerUtil.showTimeElapsed(TimeUnit.MSEC));
		
		TimerUtil.startTime();
		out.println(findFirstNonRepeatingCharUsingSet(string));
		out.printf("time elapsed : %,d msec%n",TimerUtil.showTimeElapsed(TimeUnit.MSEC));
		
	}


	

}

package com.grsdev.java8.pack03.concurrency.pack02.advanced;

import static java.lang.System.out;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Demo4ForkJoinPool {
	
	public static void main(String[] args) {

		Integer [] salaries = createIntegerArray(100000);
		BonusCalculator bc = new BonusCalculator(salaries);
		ForkJoinPool pool = new ForkJoinPool();
		Integer[] bonus = pool.invoke(bc);
		
		out.print("salaries :");
		printArray(salaries); 
		out.print("bonus    :");
		printArray(bonus); 
		
	}

	private static void printArray(Integer[] salaries) {
		
		Arrays
		.stream(salaries)
		.forEach(t-> out.printf("|%2d",t));
		out.println("|");
	}

	private static Integer[] createIntegerArray(int i) {
		
		return
		IntStream.range(0, i)
				 .map((t->(int)(Math.random()*98)+1))
				 .boxed()
				 .collect(Collectors.toList())
				 .toArray(new Integer[i]);
	}

}

class BonusCalculator extends RecursiveTask<Integer[]>{
	
	private final int THRESHOLD = 30;
	
	private Integer [] salaries;
	
	BonusCalculator(Integer[] salaries){
		this.salaries=salaries;
	}
	
	
	@Override
	protected Integer[] compute() {
		
		if(salaries.length> THRESHOLD) {
			
			List<BonusCalculator> subTasks = createSubTasks(salaries);
			
			invokeAll(subTasks);
			
			Integer[] join1 = subTasks.get(0).join();
			Integer[] join2 = subTasks.get(1).join();
			
			List<Integer> list = new ArrayList<>(Arrays.asList(join1));
			list.addAll(Arrays.asList(join2));
			
			return list.toArray(new Integer[list.size()]);
			
		}else {
			return calculateBonusInBatch(salaries);
		}
	}


	private List<BonusCalculator> createSubTasks(Integer[] salaries) {
		
		List<BonusCalculator> subTasks =  new ArrayList<>(2);
		subTasks.add(new BonusCalculator(Arrays.copyOfRange(salaries, 0, salaries.length/2)));
		subTasks.add(new BonusCalculator(Arrays.copyOfRange(salaries, salaries.length/2, salaries.length)));
		return subTasks;
	}


	private Integer[] calculateBonusInBatch(Integer[] salaries) {
		
	return     Arrays.stream(salaries)
					 .mapToInt(t->t)
					 .map(t->(int)(t*.10))
					 .boxed()
					 .collect(Collectors.toList())
					 .toArray(new Integer[salaries.length])
					 ;
	}
	
}

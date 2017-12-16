package com.grsdev.java8season2.pack02.lambdas;

import static java.lang.System.out;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;

import com.grsdev.java8season2.pack02.lambdas.Item.Category;

/**
 * @author gaurav salvi
 *
 */
public class ShopHelper {
	
	private static List<Item> items= Arrays.asList(new Item[]{new Item("1002", "perfume",Category.COSMETICS,90.93), 
												   new Item("1202", "fidget spinner",Category.TOY, 211.93),
												   new Item("1123", "trimmer",Category.ELECTRONIS, 2199.55)});

	public static void main(String args [] ){
		out.println(">>> before applying discount");
		printItems();
		
		applyDiscountToItems(t->t.getCategory().equals(Category.ELECTRONIS),t->t.setPrice(t.getPrice()-t.getPrice()*.10));
		
		out.println(">>> after applying discount");
		printItems();
	}
	
	public static void applyDiscountToItems(Predicate<Item> itemPredicate,Consumer<Item> discounter){
		items.stream().filter(itemPredicate).forEach(discounter);
	}
	
	public static void printItems(){
		items.forEach(out::println);
	}
}

class Item{

	private String id,name;
	
	private Category category;
	
	private Double price;
	
	/**
	 * @param id
	 * @param name
	 * @param category
	 * @param price
	 */
	public Item(String id, String name, Category category, Double price) {
		super();
		this.id = id;
		this.name = name;
		this.category = category;
		this.price = price;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	enum Category{ELECTRONIS,FOOD,COSMETICS,TOY}

	@Override
	public String toString() {
		return "Item [id=" + id + ", name=" + name + ", category=" + category + ", price=" + String.format("%5.2f", price) + "]";
	};
	
	
}
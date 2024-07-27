package org.immutable.pascal;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

public class Resolver {

	public static void main(String[] args) {
		
		List<String> words = Arrays.asList("ios","amplifier");
		Immutable immutable = Immutable.createInstance("IOS SYSTEM", 34, 4.5, words);
		
		List<String> storedwords = immutable.getWords();		
		
		//storedwords.add("adding the lastest value");
		
		System.out.println("checking the modifiable collections and how they do");
		try {
			   
			   storedwords.add("iosdeveloper");
			   System.out.println(immutable);
		} 
		catch(Exception ex) {
                    System.out.println("the stored list is unmmodifiable ... ");
		}
		
	
		System.out.println("checking the immutable fileds......");
		try {
			              Field field = Immutable.class.getField("applicationname");
			              field.setAccessible(true);
			              field.set(immutable,"Instagram");
		}
		catch(Exception e) {
			System.out.println("this field is immutable .....  ");
		}
		
		
		
		
		System.out.println("checking the clone ....");
		
		try {
			          Immutable immutableobject = (Immutable) immutable.clone();
		}
		catch(Exception e) {
			System.out.println("this is object can not be clonned");
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
	
}

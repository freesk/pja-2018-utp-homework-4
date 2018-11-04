package mypackage;

import java.util.List;
import java.io.File;
import java.io.IOException;

public class Main {

	public static void main(String[] args) {
		
		List<Person> arr = null;
		List<Person> sortedByFirstName = null;
		List<Person> sortedBySurnameFirstNameAndBirthdate = null;
		List<Person> sortedByBirthdate = null;
		
		try {
			arr = InputParser.parse(new File("data.txt"));	
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		
//		for (Person p : arr)
//			System.out.println(p);
//		
//		sortedByFirstName = PersonDatabase.sortedByFirstName(arr);
//		
//		System.out.println("");
//		
//		for (Person p : sortedByFirstName)
//			System.out.println(p);
//
//		sortedByBirthdate = PersonDatabase.sortedByBirthdate(arr);
//		
//		System.out.println("");
//		
//		for (Person p : sortedByBirthdate)
//			System.out.println(p);
		
		sortedBySurnameFirstNameAndBirthdate = PersonDatabase.sortedBySurnameFirstNameAndBirthdate2(arr);
				
		System.out.println("");
				
		for (Person p : sortedBySurnameFirstNameAndBirthdate)
				System.out.println(p);
		
	}

}

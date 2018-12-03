package mypackage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import mypackage.comparators.BirthdateComparator;
import mypackage.comparators.FirstNameComparator;

public final class PersonDatabase {
	
	// assignment 8 - factory method based on deserialization
	public static Set<Person> deserialize(DataInputStream input) throws Assignment08Exception, IOException {
		Set<Person> set = new HashSet<Person>();
		while (input.available() > 0) {
			Person p = Person.deserialize(input);
//			System.out.println(p);
			set.add(p);
		}
		return set;
	}

	// assignment 8
	public static void serialize(DataOutputStream output, Set<Person> set) {
		set.stream()
		   .forEach(p -> {
			try {
				p.serialize(output);
			} catch (Assignment08Exception e) {
				e.printStackTrace();
			}
		});		
	}

	public static List<Person> sortedByFirstName(List<Person> list) {
		List<Person> res = list.stream()
				.sorted(new FirstNameComparator())
				.collect(Collectors.toList());

		return res;
	}
	
	public static List<Person> sortedBySurnameFirstNameAndBirthdate(List<Person> list) {
		Comparator <Person> comp = (p1, p2) -> {
				int r = p1.getLastName().compareTo(p2.getLastName());
			    if (r == 0) 
			    	r = p1.getFirstName().compareTo(p2.getFirstName());
			    if (r == 0) 
			        r = p1.getBirthDate().compareTo(p2.getBirthDate());
			    return r;
		};
		List<Person> res = new ArrayList<Person>(list);
		res.sort(comp);
		return res;
	}
	
	public static List<Person> sortedByBirthdate(List<Person> list) {		
		List<Person> res = list.stream()
				.sorted(new BirthdateComparator())
				.collect(Collectors.toList());

		return res;
	}
	
	// Sorry, not quite sure what you mean by this https://www.screencast.com/t/LKPrN9AQiN9
	public static List<Person> bornOnDay(Date date, List<Person> list) {
		List<Person> res = list.stream()
				.filter(p -> p.getBirthDate().compareTo(date) == 0)
				.collect(Collectors.toList());
		
		return res;
	}
}
package mypackage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.TreeSet;
import java.util.stream.Collectors;

import mypackage.comparators.BirthdateComparator;
import mypackage.comparators.FirstNameComparator;

public final class PersonDatabase {

	public static List<Person> sortedByFirstName(List<Person> list) {
		List<Person> res = list.stream()
				.sorted(new FirstNameComparator())
				.collect(Collectors.toList());

		return res;
	}
	
	public static List<Person> sortedBySurnameFirstNameAndBirthdate1(List<Person> list) {
		
		Comparator <Person> lastNameComparator = (p1, p2) -> {
			return p1.getLastName().compareTo(p2.getLastName());
		};
		
		List<Comparator<Person>> comparators = Arrays.asList (
				lastNameComparator,
				new FirstNameComparator(),
				new BirthdateComparator()
		);
		
		for (Comparator<? super Person> comparator : comparators) {
		    list.sort(comparator);
		}
		
		return list;
	}
	
	public static List<Person> sortedBySurnameFirstNameAndBirthdate2(List<Person> list) {
		Comparator <Person> comp = (p1, p2) -> {
				int r = p1.getLastName().compareTo(p2.getLastName());
			    if (r == 0) {
			    	r = p1.getFirstName().compareTo(p2.getFirstName());
			    }
			    if (r == 0) {
			        r = p1.getBirthDate().compareTo(p2.getBirthDate());
			    }
			    return r;
		};
		
		list.sort(comp);
		
		return list;
	}
	
	public static List<Person> sortedBySurnameFirstNameAndBirthdate3(List<Person> list) {
		
		Comparator <Person> comp = (p1, p2) -> {
			return p1.getLastName().compareTo(p2.getLastName());
		};

		return list.stream()
			.sorted(comp)
			.sorted(new FirstNameComparator())
			.sorted(new BirthdateComparator())
			.collect(Collectors.toList());
	}
	
	public static List<Person> sortedByBirthdate(List<Person> list) {		
		List<Person> res = list.stream()
				.sorted(new BirthdateComparator())
				.collect(Collectors.toList());

		return res;
	}
	
	public List<Person> bornOnDay(Date date, List<Person> list) {
		List<Person> res = list.stream()
				.filter(p -> p.getBirthDate().compareTo(date) == 0)
				.collect(Collectors.toList());
		
		return res;
	}
}
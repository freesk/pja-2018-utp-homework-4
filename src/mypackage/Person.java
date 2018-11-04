package mypackage;

import java.util.Date;

public class Person implements Comparable<Person> {
	
	private final String _firstName;
	private final String _surname;
	private final Date _birthdate;
	
	public Person(String firstName, String surname, Date birthdate) {
		_firstName = firstName;
		_surname = surname;
		_birthdate = birthdate;
	}
	
	public String getFirstName() {
		return _firstName;
	}
	
	public String getLastName() {
		return _surname;
	}
	
	public Date getBirthDate() {
		return _birthdate;
	}

	// compareTo appears to me redundant here
	
	@Override
	public int compareTo(Person otherPerson) {
		// TODO Auto-generated method stub
		return this.compareTo(otherPerson);
	}
	
	@Override
	public String toString() {
		return _firstName + " " + _surname + " " + _birthdate;
	}
}
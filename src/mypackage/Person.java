package mypackage;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.Objects;

public class Person implements Comparable<Person> {
	
	private String _firstName = null;
	private String _surname = null;
	private Date _birthdate = null;
	
	public Person(String firstName, String surname, Date birthdate) {
		_firstName = firstName;
		_surname = surname;
		_birthdate = birthdate;
	}
	
	public Person() {

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
	
	public void setFirstName(String firstName) {
		_firstName = firstName;
	}
	
	public void setLastName(String surname) {
		_surname = surname;
	}
	
	public void setBirthDate(Date birthdate) {
		_birthdate = birthdate;
	}
	
	// assignment 8
	public void serialize(DataOutputStream output) throws Assignment08Exception {
		// serialize birth date with getTime() method
		// encapsulate IOException in Assignment08Exception
		try {
			output.writeUTF(_firstName);
			output.writeUTF(_surname);
			output.writeLong(_birthdate.getTime());	
		} catch (IOException e) {
			throw new Assignment08Exception(e.getMessage(), e.getCause());
		}
		
	}
		
	public static Person deserialize(DataInputStream input) throws Assignment08Exception {
		
		Person p = new Person();
	
		try {
			p.setFirstName(input.readUTF());
			p.setLastName(input.readUTF());
			Date d = new Date();
			d.setTime(input.readLong());
			p.setBirthDate(d);
		} catch (IOException e) {
			throw new Assignment08Exception(e.getMessage(), e.getCause());
		}
		
		return p;
	}

	// compareTo appears to me redundant here
	
	@Override
	public int compareTo(Person otherPerson) {
		// TODO Auto-generated method stub
		return this.compareTo(otherPerson);
	}
	
	@Override 
	public int hashCode() {
		return Objects.hash(_firstName, _surname, _birthdate);
	}
	
	@Override
	public boolean equals(Object o) {
		if (o == null) return false;
        return (this.hashCode() == o.hashCode());
    }
	
	@Override
	public String toString() {
		return _firstName + " " + _surname + " " + _birthdate;
	}
}
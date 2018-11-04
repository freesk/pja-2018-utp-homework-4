package mypackage.test;

import mypackage.Person;
import mypackage.PersonDatabase;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class PersonDatabaseTest {
	
	private List<Person> list = null;
	private static Date dateOne = null;
	private static Date dateTwo = null;
	private static Date dateThree = null;
	private Person p1 = null;
	private Person p2 = null;
	private Person p3 = null;
	
	@BeforeClass
    public static void setup() {		
		Date now = new Date();
		Calendar c = Calendar.getInstance();
		
		c.setTime(now);
		c.add(Calendar.YEAR, -20);
		
		dateOne = c.getTime();
		
		c.setTime(now);
		c.add(Calendar.YEAR, -25);
		
		dateTwo = c.getTime();
		
		c.setTime(now);
		c.add(Calendar.YEAR, -30);
		
		dateThree = c.getTime();
    }
	
	@Before
	public void reset() {
		list = new ArrayList<Person>();
		p1 = null;
		p2 = null;
		p3 = null;
	}
 
	@Test
	public void sortedByFirstName() {
		list.add(new Person("C", "B", new Date()));
		list.add(new Person("B", "B", new Date()));
		list.add(new Person("A", "B", new Date()));
		
		List<Person> res = PersonDatabase.sortedByFirstName(list);
		
		Assert.assertEquals("A", res.get(0).getFirstName());
		Assert.assertEquals("B", res.get(1).getFirstName());
		Assert.assertEquals("C", res.get(2).getFirstName());
	}
	
	@Test 
	public void sortedByBirthdate() {
		list.add(new Person("C", "B", dateOne));
		list.add(new Person("B", "B", dateTwo));
		list.add(new Person("A", "B", dateThree));
		
		List<Person> res = PersonDatabase.sortedByBirthdate(list);
		
		Assert.assertEquals(dateThree, res.get(0).getBirthDate());
		Assert.assertEquals(dateTwo, res.get(1).getBirthDate());
		Assert.assertEquals(dateOne, res.get(2).getBirthDate());
	}
	
	@Test 
	public void bornOnDay() {
		list.add(new Person("C", "B", dateOne));
		list.add(new Person("B", "B", dateTwo));
		list.add(new Person("A", "B", dateThree));
		
		List<Person> res = PersonDatabase.bornOnDay(dateOne, list);
		
		Assert.assertEquals(1, res.size());
		Assert.assertEquals("C", res.get(0).getFirstName());
	}
	
	@Test
	public void sortedBySurnameFirstNameAndBirthdate() {
		
//		dateThree is before dateOne
		
		p1 = new Person("A", "B", dateOne);
		p2 = new Person("A", "B", dateTwo);
		p3 = new Person("A", "B", dateThree);
		
		list.add(p1);
		list.add(p2);
		list.add(p3);
		
		List<Person> res = PersonDatabase.sortedBySurnameFirstNameAndBirthdate(list);
		
		Assert.assertEquals(p3, res.get(0));
		Assert.assertEquals(p2, res.get(1));
		Assert.assertEquals(p1, res.get(2));
		
		p1 = new Person("A", "B", dateOne);
		p2 = new Person("A", "A", dateTwo);
		p3 = new Person("A", "A", dateThree);
		
		list = new ArrayList<Person>();
		
		list.add(p1);
		list.add(p2);
		list.add(p3);
		
		res = PersonDatabase.sortedBySurnameFirstNameAndBirthdate(list);
		
		Assert.assertEquals(p3, res.get(0));
		Assert.assertEquals(p2, res.get(1));
		Assert.assertEquals(p1, res.get(2));
		
		p1 = new Person("A", "B", dateThree);
		p2 = new Person("A", "A", dateTwo);
		p3 = new Person("A", "B", dateOne);
		
		list = new ArrayList<Person>();
		
		list.add(p1);
		list.add(p2);
		list.add(p3);
		
		res = PersonDatabase.sortedBySurnameFirstNameAndBirthdate(list);
		
		Assert.assertEquals(p2, res.get(0));
		Assert.assertEquals(p1, res.get(1));
		Assert.assertEquals(p3, res.get(2));
	}

}

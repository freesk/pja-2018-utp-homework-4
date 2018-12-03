package mypackage.test;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import junit.framework.Assert;
import mypackage.InputParser;
import mypackage.Person;
import mypackage.PersonDatabase;

public class StreamInputOutputTest {

	@Test
	public void test() {
		List<Person> arr = new ArrayList<Person>();
		DataOutputStream output;
		DataInputStream input;
		
		try {
			arr = InputParser.parse(new File("data.txt"));	
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		
		Set<Person> setOut = new HashSet<Person>(arr);
		Set<Person> setIn = new HashSet<Person>();
		
		try {
			output = new DataOutputStream(new FileOutputStream("backup"));
			PersonDatabase.serialize(output, setOut);
			output.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		try {
			input = new DataInputStream(new FileInputStream("backup"));
			setIn = PersonDatabase.deserialize(input);
			input.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		Assert.assertEquals(setIn.size(), setOut.size());
		// the method equals() in Person has been overwritten to make it true
		Assert.assertTrue(setOut.containsAll(setIn));
	}

}

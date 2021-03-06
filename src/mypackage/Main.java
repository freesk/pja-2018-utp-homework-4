package mypackage;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Main {

	public static void main(String[] args) {
		
		List<Person> arr = null;
		
		try {
			arr = InputParser.parse(new File("data.txt"));	
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		
		Set<Person> setOut = new HashSet<Person>(arr);
		Set<Person> setIn = new HashSet<Person>();
		
		
		try {
			DataOutputStream output = new DataOutputStream(new FileOutputStream("backup"));
			PersonDatabase.serialize(output, setOut);
			output.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			DataInputStream input = new DataInputStream(new FileInputStream("backup"));
			setIn = PersonDatabase.deserialize(input);
			input.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
		System.out.println(setIn.size() + " " + setOut.size());
		// the method equals() in Person has been overwritten to make it true
		System.out.println(setOut.containsAll(setIn));
		
	}

}

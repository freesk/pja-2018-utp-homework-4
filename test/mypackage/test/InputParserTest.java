package mypackage.test;

import static org.junit.Assert.*;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import mypackage.InputParser;
import mypackage.Person;

public class InputParserTest {

	@Test
	public void parse() {
		
		List<Person> list = null;
		
		try {
			list = InputParser.parse(new File("test_data.txt"));
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");
		Date parsed = null;
		try {
			parsed = format.parse("1900-10-01");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		// guess to many asserts for one test but since it's a matter of style let's leave it alone
		Assert.assertNotNull(list);
		Assert.assertEquals(1, list.size());
		Assert.assertEquals(list.get(0).getFirstName(), "Test");
		Assert.assertEquals(list.get(0).getLastName(), "Test");
		Assert.assertEquals(0, list.get(0).getBirthDate().compareTo(parsed));

	}

}

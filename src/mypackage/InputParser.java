package mypackage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

public final class InputParser {
	
	// 1. Use regular expresssions (Pattern) for validating input data
	// 2. Convert input string representing date using SimpleDateFormat "yyyy-MM-dd" 

	public static List<Person> parse(File file) throws IOException {
		
		List<Person> list = new ArrayList<Person>();
		
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
		    String line;
		    while ((line = br.readLine()) != null) {
		    	if (!isValidLine(line))
		    		continue;
		    	
		    	String[] parts = line.split(" ");
		    	
		    	String firstName = parts[0];
		    	String lastName = parts[1];
		    	Date date = null;
		    	
		    	try {
		    		date = parseDate(parts[2]);
		    	} catch (ParseException e) {
		    		System.out.println(e.getMessage());
		    		continue;
		    	}
		    	
		    	list.add(new Person(firstName, lastName, date));

		    }
		}
		
		return list;
	}
	
	private static Date parseDate(String dateString) throws ParseException {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-mm-dd");
		Date parsed = format.parse(dateString);
		return parsed;
	}
	
	// firstname lastname yyyy-mm-dd
	private static boolean isValidLine(String line) {
		String patternString = "\\w+\\s\\w+\\s[0-9]{4}\\-[0-1][0-9]\\-[0-3][0-9]";
		
	    Pattern pattern = Pattern.compile(patternString, Pattern.CASE_INSENSITIVE);
	    Matcher matcher = pattern.matcher(line);
		
		return matcher.matches();
	}
}
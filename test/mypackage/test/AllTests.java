package mypackage.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ InputParserTest.class, PersonDatabaseTest.class })

public class AllTests {

}

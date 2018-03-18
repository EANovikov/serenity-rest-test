package base;

import net.serenitybdd.rest.RestDefaultsChained;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.rest.rules.RestConfigurationAction;
import net.serenitybdd.rest.rules.RestConfigurationRule;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import utilites.ConfigReader;

import java.io.*;

public abstract class BaseTest {

    PrintStream printStream;

    @Before
    public void setUp() throws IOException {
        printStream = new PrintStream(new File("ConsoleOutput.txt"));
        System.setErr(printStream);
        System.setOut(printStream);
    }

    @After
    public void tearDown(){
        printStream.close();
    }

}

package devops.codereview;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

public class QueryFiveTest
{
    static App app;

    @BeforeAll
    static void init()
    {
        app = new App();
    }

    @Test
    void printNPopulatedContinentsTestEmpty()
    {
        ArrayList<Country> nPopulatedContinents = new ArrayList<Country>();
        app.printNPopulatedContinents(nPopulatedContinents);
    }

    //print a list with a null value
    @Test
    void printNPopulatedContinentsTestContainsNull()
    {
        ArrayList<Country> nPopulatedContinents = new ArrayList<Country>();
        nPopulatedContinents.add(null);
        app.printNPopulatedContinents(nPopulatedContinents);
    }

    @Test
    void printNPopulatedContinents()
    {
        ArrayList<Country> nPopulatedContinents = new ArrayList<Country>();
        Country npopcont = new Country();
        npopcont.setCountryName("South America");
        npopcont.setCountryPopulation("170115000");
        nPopulatedContinents.add(npopcont);
        app.printNPopulatedContinents(nPopulatedContinents);
    }
}
